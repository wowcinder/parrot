/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.parrot.service.service;

import java.lang.reflect.Method;

import com.voole.parrot.shared.entity.menu.MenuGroup;

/**
 * 认证，授权服务
 * 
 * @author XuehuiHe
 * @date 2013年9月6日
 */
public interface AuthorizeService {
	boolean verify(Class<?> targetClass, Method invokeMethod);

	boolean login(String name, String password);

	void logout();

	boolean isLogin();

	boolean isAdmin();

	Long getUserId();

	MenuGroup getMenuRoot();

}
