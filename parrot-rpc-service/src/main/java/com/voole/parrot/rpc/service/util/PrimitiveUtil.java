package com.voole.parrot.rpc.service.util;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class PrimitiveUtil {

	private static final Set<Class<?>> WRAPPER_TYPES = getWrapperTypes();

	public static boolean isPrimitive(Class<?> clazz) {
		if (clazz.isPrimitive() || WRAPPER_TYPES.contains(clazz)) {
			return true;
		}
		return false;
	}

	public static boolean isPrimitive(Object bean) {
		return isPrimitive(bean.getClass());
	}

	private static Set<Class<?>> getWrapperTypes() {
		Set<Class<?>> ret = new HashSet<Class<?>>();
		ret.add(Boolean.class);
		ret.add(Character.class);
		ret.add(Byte.class);
		ret.add(Short.class);
		ret.add(Integer.class);
		ret.add(Long.class);
		ret.add(Float.class);
		ret.add(Double.class);
		ret.add(Void.class);
		ret.add(String.class);
		ret.add(Date.class);
		return ret;
	}
}
