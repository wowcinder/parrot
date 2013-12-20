/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.parrot.gwt.common.util;

import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.google.common.reflect.TypeToken;
import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sun.codemodel.ClassType;
import com.sun.codemodel.JClass;
import com.sun.codemodel.JClassAlreadyExistsException;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JExpr;
import com.sun.codemodel.JMethod;
import com.sun.codemodel.JMod;
import com.sun.codemodel.JType;
import com.sun.codemodel.JVar;
import com.voole.parrot.util.ClassScanner;

/**
 * @author XuehuiHe
 * @date 2013年12月20日
 */
public class PropertiesGenerator2 extends BaseJavaSourceGenerator {
	private static final String PROPERTY_SOURCE_PATH = "com.voole.parrot.gwt.common.shared.property2.";
	private static final String GRIDCOLUMN_PROPERTY_SOURCE_PATH = "com.voole.parrot.gwt.common.shared.gridcolumn2.";
	private static final String[] entity_PATHS = new String[] { "com.voole.parrot.shared.entity" };
	private JDefinedClass propertyUtils;

	public PropertiesGenerator2() throws JClassAlreadyExistsException {
		propertyUtils = getjCodeModel()._class(
				PROPERTY_SOURCE_PATH + "PropertyUtils");
	}

	public static void main(String[] args) throws Exception {
		PropertiesGenerator2 t = new PropertiesGenerator2();
		t.work();
		// t.generatePropertyClass(Account.class);
		// t.build();
	}

	public void work() throws Exception {

		ClassScanner scaner = new ClassScanner(entity_PATHS);
		for (Class<?> clazz : scaner.getClazzes()) {
			if (clazz.isAnnotationPresent(Entity.class)
					&& !Modifier.isAbstract(clazz.getModifiers())) {
				generatePropertyClass(clazz);
			}
		}
		build();
	}

	private <T> void generatePropertyClass(Class<T> clazz)
			throws JClassAlreadyExistsException, ClassNotFoundException,
			SecurityException, NoSuchFieldException {
		TypeToken<T> typeToken = TypeToken.of(clazz);

		JDefinedClass dc = getjCodeModel()._class(
				PROPERTY_SOURCE_PATH + clazz.getSimpleName() + "Property",
				ClassType.INTERFACE);
		JDefinedClass dc2 = getjCodeModel()._class(
				GRIDCOLUMN_PROPERTY_SOURCE_PATH + clazz.getSimpleName()
						+ "ColumnConfig");
		dc._extends(getjCodeModel().ref(PropertyAccess.class).narrow(
				getJType(typeToken, clazz)));
		for (Method method : clazz.getMethods()) {
			if (isDoGeneratePropertyClass(method)) {
				generatePropertyMethod(typeToken, dc, method, clazz, dc2);
			}
		}
		writeGwtInstance(dc);
	}

	protected <T> Type getRealType(TypeToken<T> typeToken, Type type) {
		return typeToken.resolveType(type).getRawType();
	}

	protected <T> JType getJType(TypeToken<T> typeToken, Type type)
			throws ClassNotFoundException {
		if (type instanceof ParameterizedType) {
			Type[] parameters = ((ParameterizedType) type)
					.getActualTypeArguments();
			JClass jClass = jCodeModel.ref(getJType(
					typeToken,
					getRealType(typeToken,
							((ParameterizedType) type).getRawType()))
					.fullName());
			for (Type type2 : parameters) {
				jClass = jClass.narrow((JClass) getJType(typeToken, type2));
			}
			return jClass;
		} else if (type instanceof GenericArrayType) {
			GenericArrayType arrayType = (GenericArrayType) type;
			return getJType(typeToken, arrayType.getGenericComponentType())
					.array();
		} else if (type instanceof WildcardType) {
			return jCodeModel.wildcard();
		} else if (type instanceof TypeVariable) {
			return null;
			// return jCodeModel.ref(getClassHolder(holder).getActualType(
			// (TypeVariable<?>) type));
		} else {
			return jCodeModel.parseType(((Class<?>) type).getName());
		}
	}

	public <T> void generatePropertyMethod(TypeToken<T> typeToken,
			JDefinedClass dc, Method method, Class<?> clazz, JDefinedClass dc2)
			throws ClassNotFoundException, SecurityException,
			NoSuchFieldException {
		JType classJType = getJType(typeToken, clazz);
		String name = method.getName().replace("get", "");
		name = name.substring(0, 1).toLowerCase() + name.substring(1);
		if (isId(method, name, clazz)) {
			JMethod jMethod = dc.method(
					JMod.PUBLIC,
					getjCodeModel().ref(ModelKeyProvider.class).narrow(
							classJType), "key");
			jMethod.annotate(Path.class).param("value", name);
		}
		dc.method(
				JMod.PUBLIC,
				getjCodeModel()
						.ref(ValueProvider.class)
						.narrow(classJType)
						.narrow(getJType(typeToken,
								method.getGenericReturnType())), name);
		JClass columnConfigClass = getjCodeModel().ref(ColumnConfig.class)
				.narrow(classJType)
				.narrow(getJType(typeToken, method.getGenericReturnType()));
		JMethod jMethod = dc2.method(JMod.PUBLIC + JMod.STATIC,
				columnConfigClass, name);
		JVar jVar = jMethod.body().decl(
				columnConfigClass,
				name,
				JExpr._new(columnConfigClass)
						.arg(propertyUtils.staticRef(dc.name()).invoke(name))
						.arg(JExpr.lit(200)).arg(name));
		// jMethod.body().invoke(jVar, "setSortable").arg(JExpr.lit(false));
		// jMethod.body().invoke(jVar, "setMenuDisabled").arg(JExpr.lit(true));
		jMethod.body()._return(jVar);
	}

	private static boolean isDoGeneratePropertyClass(Method method) {
		Class<?> clazz = method.getDeclaringClass();
		return method.getName().startsWith("get")
				&& (clazz.isAnnotationPresent(Entity.class) || clazz
						.isAnnotationPresent(MappedSuperclass.class))
				&& method.getParameterTypes().length == 0;
	}

	public static boolean isId(Method method, String name, Class<?> clazz) {
		if (method.isAnnotationPresent(Id.class)) {
			return true;
		}
		Field field = getField(name, clazz);
		if (field != null && field.isAnnotationPresent(Id.class)) {
			return true;
		}
		return false;
	}

	public static Field getField(String name, Class<?> clazz) {
		Field field = null;
		try {
			field = clazz.getDeclaredField(name);
		} catch (SecurityException e) {
		} catch (NoSuchFieldException e) {
		}
		if (field == null && !Object.class.equals(clazz.getSuperclass())) {
			field = getField(name, clazz.getSuperclass());
		}
		return field;
	}

	private void writeGwtInstance(JDefinedClass dc)
			throws JClassAlreadyExistsException {
		propertyUtils.field(JMod.PUBLIC + JMod.STATIC + JMod.FINAL, dc,
				dc.name(), getjCodeModel().ref(GWT.class)
						.staticInvoke("create").arg(JExpr.dotclass(dc)));
	}

}
