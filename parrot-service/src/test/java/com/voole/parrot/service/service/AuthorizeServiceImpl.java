/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.parrot.service.service;

import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.voole.parrot.service.dao.account.IAccountDao;
import com.voole.parrot.shared.account.Account;
import com.voole.parrot.shared.authority.Authority;
import com.voole.parrot.shared.authority.Role;
import com.voole.parrot.shared.organization.Organization;
import com.voole.parrot.shared.organization.TopOrganization;
import com.voole.parrot.shared.organization.TopOrganizationAuthority;

/**
 * 
 * @author XuehuiHe
 * @date 2013年9月6日
 */
@Service
public class AuthorizeServiceImpl implements AuthorizeService {
	private static final String USER_ID_NAME_IN_SESSION = "USERID";
	private static final String AUTHORITIES_NAME_IN_SESSION = "AUTHORIZES";
	private static final String IS_ADMIN_NAME_IN_SESSION = "ADMIN";

	@Autowired
	private IAccountDao accountDao;

	public AuthorizeServiceImpl() {
	}

	@Override
	public boolean verify(Class<?> targetClass, Method invokeMethod) {
		// TODO
		return false;
	}

	@SuppressWarnings({ "unchecked", "unused" })
	private Set<String> getCurrUserAuthorizeTokens() {
		return (Set<String>) getSession().getAttribute(
				AUTHORITIES_NAME_IN_SESSION);
	}

	@Override
	@Transactional(readOnly = true)
	public boolean login(String name, String password) {
		if (isLogin()) {
			logout();
		}
		if (password == null) {
			password = "";
		}
		if ("admin".equals(name) && "admin".equals(password)) {
			HttpSession session = getSession();
			session.setAttribute(USER_ID_NAME_IN_SESSION, 0);
			session.setAttribute(IS_ADMIN_NAME_IN_SESSION, true);
			return true;
		}
		Account account = accountDao.findAccount(name, password);
		if (account != null) {
			HttpSession session = getSession();
			session.setAttribute(USER_ID_NAME_IN_SESSION, account.getId());
			Organization organization = account.getMember().getOrganization();
			if (organization instanceof TopOrganization
					&& organization.getName().equals("admin")) {
				session.setAttribute(IS_ADMIN_NAME_IN_SESSION, true);
			} else {
				session.setAttribute(IS_ADMIN_NAME_IN_SESSION, false);
			}
			session.setAttribute(AUTHORITIES_NAME_IN_SESSION,
					getAuthorities(account));
			return true;
		}
		return false;
	}

	public Set<String> getAuthorities(Account account) {
		Set<String> authorities = new HashSet<String>();
		// 额外的权限
		for (Authority authority : account.getAuthorities()) {
			fillToken(authorities, authority);
		}
		Set<Role> roles = account.getMember().getRoles();
		// 角色的权限
		for (Role role : roles) {
			for (TopOrganizationAuthority topOrganizationAuthority : role
					.getAuthorities()) {
				fillToken(authorities, topOrganizationAuthority.getAuthority());
			}
		}
		return authorities;
	}

	private void fillToken(Set<String> authorities, Authority authority) {
		authorities.add(authority.getToken());
		for (Authority authority2 : authority.getDependencies()) {
			authorities.add(authority2.getToken());
		}
	}

	@Override
	public void logout() {
		HttpSession session = getSession();
		@SuppressWarnings({ "unchecked" })
		Enumeration<String> names = session.getAttributeNames();
		while (names.hasMoreElements()) {
			String name = names.nextElement();
			session.removeAttribute(name);
		}
	}

	@Override
	public boolean isLogin() {
		Integer uid = getUserId();
		if (uid != null) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isAdmin() {
		return (Boolean) getSession().getAttribute(IS_ADMIN_NAME_IN_SESSION);
	}

	@Override
	public Integer getUserId() {
		return (Integer) getSession().getAttribute(USER_ID_NAME_IN_SESSION);
	}

	protected HttpSession getSession() {
		if (RequestContextHolder.currentRequestAttributes() != null) {
			ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder
					.currentRequestAttributes();
			return attr.getRequest().getSession();
		}
		return null;
	}

}
