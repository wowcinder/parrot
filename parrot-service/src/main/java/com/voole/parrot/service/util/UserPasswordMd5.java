/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.parrot.service.util;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author XuehuiHe
 * @date 2013年12月23日
 */
public class UserPasswordMd5 {
	public static String md5(String password) {
		if (password == null) {
			return null;
		}
		return DigestUtils.md5Hex("voole_" + password);
	}
}
