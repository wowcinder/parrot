package com.voole.parrot.gwt.common.util.reflect;

import java.lang.reflect.Method;

public class MethodFinder {
	public static Method getGetMethod(Class<?> clazz, String field) {
		Method method = null;
		String pName = field.substring(0, 1).toUpperCase() + field.substring(1);
		method = getMethod(clazz, field);
		if (method != null) {
			return method;
		}
		method = getMethod(clazz, "get" + pName);
		if (method != null) {
			return method;
		}
		method = getMethod(clazz, "is" + pName);
		if (method != null) {
			return method;
		}
		method = getMethod(clazz, "has" + pName);
		if (method != null) {
			return method;
		}
		return method;
	}

	public static Method getMethod(Class<?> clazz, String name) {
		Method method = null;
		try {
			method = clazz.getMethod(name);
		} catch (SecurityException e) {
		} catch (NoSuchMethodException e) {
		}
		return method;
	}
}
