/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.parrot.gwt.common;

import java.lang.reflect.Method;
import java.lang.reflect.Type;

import com.google.common.reflect.TypeToken;
import com.voole.parrot.gwt.common.shared.rpcservice.AuthorityRpcService;

/**
 * @author XuehuiHe
 * @date 2013年12月20日
 */
public class Test {
	public static void main(String[] args) {
		TypeToken<AuthorityRpcService> typeToken = TypeToken
				.of(AuthorityRpcService.class);
		// typeResolver = TypeResolver.accordingTo(runtimeType)
		// typeToken.resolveType(type)
		for (Method method : AuthorityRpcService.class.getMethods()) {
			System.out.println(method.getName());
			for (Type type : method.getGenericParameterTypes()) {
				System.out.println(typeToken.resolveType(type).getRawType());
			}
		}

	}
}
