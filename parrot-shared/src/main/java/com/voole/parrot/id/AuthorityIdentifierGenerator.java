/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.parrot.id;

import java.io.Serializable;

import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.HibernateException;
import org.hibernate.engine.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;

import com.voole.parrot.shared.authority.Authority;

/**
 * @author XuehuiHe
 * @date 2013年12月2日
 */
public class AuthorityIdentifierGenerator implements IdentifierGenerator {

	@Override
	public Serializable generate(SessionImplementor session, Object object)
			throws HibernateException {
		if (object != null && object instanceof Authority) {
			Authority authority = (Authority) object;
			if (authority.getEntrance() != null && authority.getName() != null) {
				return DigestUtils.md5Hex(authority.getEntrance().getName()
						+ "_" + authority.getName());
			}
		}
		return null;
	}

}
