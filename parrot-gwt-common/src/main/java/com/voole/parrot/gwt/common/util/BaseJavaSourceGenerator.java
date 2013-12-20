/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.parrot.gwt.common.util;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;

import com.google.common.reflect.TypeToken;
import com.sun.codemodel.JClass;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JType;

/**
 * @author XuehuiHe
 * @date 2013年12月20日
 */
public class BaseJavaSourceGenerator {
	protected final JCodeModel jCodeModel;

	public BaseJavaSourceGenerator() {
		jCodeModel = new JCodeModel();
	}

	public void build() throws IOException {
		build(getMainJavaPath());
	}

	public void build(File path) throws IOException {
		jCodeModel.build(path);
	}

	private File getMainJavaPath() {
		String path = BaseJavaSourceGenerator.class.getClassLoader().getResource("")
				.getFile();
		File file = new File(path);
		file = file.getParentFile().getParentFile();
		path = file.getAbsolutePath() + File.separator + "src" + File.separator
				+ "main" + File.separator + "java";
		file = new File(path);
		return file;
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
			return jCodeModel.ref(getJType(typeToken, getRealType(typeToken, type))
					.fullName());
//			return null;
			// return jCodeModel.ref(getClassHolder(holder).getActualType(
			// (TypeVariable<?>) type));
		} else {
			return jCodeModel.parseType(((Class<?>) type).getName());
		}
	}

	public JCodeModel getjCodeModel() {
		return jCodeModel;
	}

}
