package com.voole.parrot.service.service;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.voole.parrot.service.dao.ISimpleDao;
import com.voole.parrot.service.dao.organization.IUserDao;
import com.voole.parrot.shared.entity.organization.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/TestEntityDao.xml" })
public class TestAuthorizeService {
	@Autowired
	private AuthorizeService service;
	@Autowired
	private Helper helper;

	@Test
	public void test() {
		boolean result = service.login("admin", "admin");
		Assert.assertTrue(result);
		Assert.assertTrue(service.isLogin());
		Assert.assertTrue(service.isAdmin());
	}

	@Test
	public void test2() {
		boolean result = service.login("admin", "admin2");
		Assert.assertFalse(result);
		Assert.assertFalse(service.isLogin());
		Assert.assertFalse(service.isAdmin());
	}

	@Test
	public void test3() {
		helper.initData();
		boolean result = service.login("admin3", "admin3");
		Assert.assertTrue(result);
		Assert.assertTrue(service.isLogin());
		Assert.assertFalse(service.isAdmin());
	}

	@Service
	public static class Helper {
		@Autowired
		private IUserDao accountDao;
		@Autowired
		private ISimpleDao authorityEntranceDao;

		@Transactional
		public void initData() {
			User account = new User();
			account.setName("admin3");
			account.setPassword("admin3");
			accountDao.create(account);
			/*
			 * AuthorityEntrance entrance = new AuthorityEntrance();
			 * entrance.setName("test_name"); Authority authority = new
			 * Authority(); authority.setEntrance(entrance);
			 * authority.setName("test_name"); List<Authority> authorities = new
			 * ArrayList<Authority>(); authorities.add(authority);
			 * entrance.setAuthorities(authorities);
			 * 
			 * authorityEntranceDao.persist(entrance); Set<Authority> set = new
			 * HashSet<Authority>(); set.addAll(authorities);
			 * account.setAuthorities(set); accountDao.update(account);
			 */
		}
	}
}
