/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.parrot.gwt.common.util;

import java.lang.reflect.Field;
import java.util.Map.Entry;

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
import com.voole.parrot.gwt.common.util.reflect.EntityClassUtil;
import com.voole.parrot.gwt.common.util.reflect.EntityColumns;
import com.voole.parrot.gwt.common.util.reflect.MethodFinder;
import com.voole.parrot.util.ClassScanner;

/**
 * @author XuehuiHe
 * @date 2013年12月20日
 */
public class PropertiesGenerator extends BaseJavaSourceGenerator {
	private static final String PROPERTY_SOURCE_PATH = "com.voole.parrot.gwt.common.shared.property.";
	private static final String GRID_COLUMN_PROPERTY_SOURCE_PATH = "com.voole.parrot.gwt.common.shared.gridcolumn.";
	private static final String[] ENTITY_PATHS = new String[] { "com.voole.parrot.shared.entity" };
	private JDefinedClass propertyUtils;

	public PropertiesGenerator() throws JClassAlreadyExistsException {
		propertyUtils = getjCodeModel()._class(
				PROPERTY_SOURCE_PATH + "PropertyUtils");
	}

	public static void main(String[] args) throws Exception {
		PropertiesGenerator t = new PropertiesGenerator();
		t.work();
	}

	public void work() throws Exception {
		ClassScanner scaner = new ClassScanner(ENTITY_PATHS);
		for (Class<?> clazz : scaner.getClazzes()) {
			if (EntityClassUtil.isEntityClass(clazz)) {
				generate(clazz);
			}
		}
		build();
	}

	private void generate(Class<?> clazz) throws JClassAlreadyExistsException,
			ClassNotFoundException, SecurityException, NoSuchFieldException {
		EntityColumns entityColumns = EntityColumns.create(clazz);
		TypeToken<?> typeToken = TypeToken.of(clazz);
		Class<?> clazz1 = entityColumns.getClazz();
		JType classJType = getJType(typeToken, clazz1);
		// JDefinedClass propertyDc = null;
		// try {
		//
		// } catch (Exception e) {
		// // TODO: handle exception
		// }
		System.out.println(clazz1.getName());
		JDefinedClass propertyDc = getjCodeModel()._class(
				PROPERTY_SOURCE_PATH + clazz1.getSimpleName() + "Property",
				ClassType.INTERFACE);
		propertyDc._extends(getjCodeModel().ref(PropertyAccess.class).narrow(
				classJType));
		JDefinedClass gridColumnDc = getjCodeModel()._class(
				GRID_COLUMN_PROPERTY_SOURCE_PATH + clazz1.getSimpleName()
						+ "ColumnConfig");
		Field idField = entityColumns.getId();
		if (idField != null) {
			JMethod jMethod = propertyDc.method(JMod.PUBLIC, getjCodeModel()
					.ref(ModelKeyProvider.class).narrow(classJType), "key");
			jMethod.annotate(Path.class).param("value", idField.getName());
		}
		for (Entry<String, Field> entry : entityColumns.getColumsMap()
				.entrySet()) {
			String name = entry.getKey();
			Field field = entry.getValue();
			JType jType = getJType(typeToken, field.getGenericType());
			propertyDc.method(JMod.PUBLIC,
					getjCodeModel().ref(ValueProvider.class).narrow(classJType)
							.narrow(jType), name);

			writeGridColumn(classJType, propertyDc, gridColumnDc, name, jType);
		}
		for (Entry<String, Field> entry : entityColumns.getPathFields()
				.entrySet()) {
			String path = entry.getKey();
			TypeToken<?> itemTypeToken = typeToken;
			Class<?> itemClazz = clazz1;
			String[] paths = path.split("\\.");
			String pathName = "";
			for (String itemPath : paths) {
				if (pathName.length() != 0) {
					pathName += "_";
				}
				pathName += itemPath;
				itemTypeToken = itemTypeToken.method(
						MethodFinder.getGetMethod(itemClazz, itemPath))
						.getReturnType();
				itemClazz = itemTypeToken.getRawType();
			}
			JType jType = getJType(itemTypeToken, itemTypeToken.getRawType());
			JMethod jMethod = propertyDc.method(JMod.PUBLIC, getjCodeModel()
					.ref(ValueProvider.class).narrow(classJType).narrow(jType),
					pathName);
			jMethod.annotate(Path.class).param("value", path);
			writeGridColumn(classJType, propertyDc, gridColumnDc, pathName,
					jType);
		}
		writeGwtInstance(propertyDc);
	}

	private void writeGridColumn(JType classJType, JDefinedClass propertyDc,
			JDefinedClass gridColumnDc, String name, JType jType) {
		JClass columnConfigClass = getjCodeModel().ref(ColumnConfig.class)
				.narrow(classJType).narrow(jType);
		JMethod jMethod = gridColumnDc.method(JMod.PUBLIC + JMod.STATIC,
				columnConfigClass, name);
		JVar jVar = jMethod.body().decl(
				columnConfigClass,
				name,
				JExpr._new(columnConfigClass)
						.arg(propertyUtils.staticRef(propertyDc.name()).invoke(
								name)).arg(JExpr.lit(200)).arg(name));
		// jMethod.body().invoke(jVar, "setSortable").arg(JExpr.lit(false));
		// jMethod.body().invoke(jVar,
		// "setMenuDisabled").arg(JExpr.lit(true));
		jMethod.body()._return(jVar);
	}

	private void writeGwtInstance(JDefinedClass dc)
			throws JClassAlreadyExistsException {
		propertyUtils.field(JMod.PUBLIC + JMod.STATIC + JMod.FINAL, dc,
				dc.name(), getjCodeModel().ref(GWT.class)
						.staticInvoke("create").arg(JExpr.dotclass(dc)));
	}

}
