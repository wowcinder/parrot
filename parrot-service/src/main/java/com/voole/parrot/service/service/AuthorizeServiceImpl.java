/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.parrot.service.service;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.voole.parrot.service.dao.account.IAccountDao;
import com.voole.parrot.shared.entity.account.Account;
import com.voole.parrot.shared.entity.authority.Authority;
import com.voole.parrot.shared.entity.authority.Role;
import com.voole.parrot.shared.entity.organization.Organization;
import com.voole.parrot.shared.entity.organization.TopOrganization;
import com.voole.parrot.shared.entity.organization.TopOrganizationAuthority;

/**
 * 
 * @author XuehuiHe
 * @date 2013年9月6日
 */
@SuppressWarnings("deprecation")
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
			session.setAttribute(USER_ID_NAME_IN_SESSION, 0L);
			session.setAttribute(IS_ADMIN_NAME_IN_SESSION, true);
			return true;
		}
		Account account = accountDao.findAccount(name, password);
		if (account != null) {
			HttpSession session = getSession();
			session.setAttribute(USER_ID_NAME_IN_SESSION, account.getId());
			if (account.getMember() != null
					&& account.getMember().getOrganization() != null) {
				Organization organization = account.getMember()
						.getOrganization();
				if (organization instanceof TopOrganization
						&& organization.getName().equals("admin")) {
					session.setAttribute(IS_ADMIN_NAME_IN_SESSION, true);
				} else {
					session.setAttribute(IS_ADMIN_NAME_IN_SESSION, false);
				}
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
		// 角色的权限
		if (account.getMember() != null) {
			Set<Role> roles = account.getMember().getRoles();
			for (Role role : roles) {
				for (TopOrganizationAuthority topOrganizationAuthority : role
						.getAuthorities()) {
					fillToken(authorities,
							topOrganizationAuthority.getAuthority());
				}
			}
		}
		// LEADER的权限
		if (account.getLeader() != null) {
			for (TopOrganizationAuthority topOrganizationAuthority : account
					.getLeader().getOrganization().getAuthorities()) {
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
		Long uid = getUserId();
		if (uid != null) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isAdmin() {
		Boolean isAdmin = (Boolean) getSession().getAttribute(
				IS_ADMIN_NAME_IN_SESSION);
		if (isAdmin != null) {
			return isAdmin;
		}
		return false;
	}

	@Override
	public Long getUserId() {
		return (Long) getSession().getAttribute(USER_ID_NAME_IN_SESSION);
	}

	protected HttpSession getSession() {
		RequestAttributes requestAttributes = null;
		try {
			requestAttributes = RequestContextHolder.currentRequestAttributes();
		} catch (IllegalStateException e) {
			return getTestSession();
		}
		ServletRequestAttributes attr = (ServletRequestAttributes) requestAttributes;
		return attr.getRequest().getSession();

	}

	private TestHttpSession testHttpSession;

	protected synchronized HttpSession getTestSession() {
		if (testHttpSession == null) {
			testHttpSession = new TestHttpSession();
		}
		return testHttpSession;
	}

	public class TestHttpSession implements HttpSession {

		private Map<String, Object> map;

		public TestHttpSession() {
			map = new HashMap<String, Object>();
		}

		@Override
		public Object getAttribute(String arg0) {
			return map.get(arg0);
		}

		@SuppressWarnings("rawtypes")
		@Override
		public Enumeration getAttributeNames() {
			final List<String> list = new ArrayList<String>();
			final AtomicInteger pos = new AtomicInteger(0);
			list.addAll(map.keySet());
			return new Enumeration<String>() {
				@Override
				public boolean hasMoreElements() {
					return list.size() > pos.get();
				}

				@Override
				public String nextElement() {
					return list.get(pos.getAndIncrement());
				}
			};
		}

		@Override
		public long getCreationTime() {
			return 0;
		}

		@Override
		public String getId() {
			return null;
		}

		@Override
		public long getLastAccessedTime() {
			return 0;
		}

		@Override
		public int getMaxInactiveInterval() {
			return 0;
		}

		@Override
		public ServletContext getServletContext() {
			return null;
		}

		@Override
		public HttpSessionContext getSessionContext() {
			return null;
		}

		@Override
		public Object getValue(String arg0) {
			return null;
		}

		@Override
		public String[] getValueNames() {
			int size = map.size();
			String[] strs = new String[size];
			int i = 0;
			for (String str : map.keySet()) {
				strs[i] = str;
				i++;
			}
			return strs;
		}

		@Override
		public void invalidate() {

		}

		@Override
		public boolean isNew() {
			return false;
		}

		@Override
		public void putValue(String arg0, Object arg1) {

		}

		@Override
		public void removeAttribute(String arg0) {
			map.remove(arg0);
		}

		@Override
		public void removeValue(String arg0) {

		}

		@Override
		public void setAttribute(String arg0, Object arg1) {
			map.put(arg0, arg1);
		}

		@Override
		public void setMaxInactiveInterval(int arg0) {
		}

	}

}
