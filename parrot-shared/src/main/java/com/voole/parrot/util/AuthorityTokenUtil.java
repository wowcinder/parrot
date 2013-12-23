/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.parrot.util;

import org.apache.commons.codec.digest.DigestUtils;

import com.voole.parrot.shared.entity.authority.Authority;

/**
 * @author XuehuiHe
 * @date 2013年12月23日
 */
public class AuthorityTokenUtil {
	public static String getToken(String entrence, String name) {
		return DigestUtils.md5Hex(entrence + "_" + name);
	}

	public static String getToken(Authority authority) {
		return getToken(authority.getEntrance().getName(), authority.getName());
	}
}
