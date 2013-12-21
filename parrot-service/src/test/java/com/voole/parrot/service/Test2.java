package com.voole.parrot.service;

import com.google.common.reflect.TypeToken;

public class Test2 {
	public <T> T getValue(Class<T> clazz) {
		@SuppressWarnings("serial")
		TypeToken<T> typeToken = new TypeToken<T>() {
		};
		System.out.println(typeToken.getRawType());
		return null;
	}

	public static void main(String[] args) {
		Test2 t = new Test2();
		String s = t.getValue(String.class);
	}
}
