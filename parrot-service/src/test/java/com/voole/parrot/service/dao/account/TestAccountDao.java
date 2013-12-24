package com.voole.parrot.service.dao.account;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.voole.parrot.service.dao.user.IUserDao;
import com.voole.parrot.shared.entity.user.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/TestEntityDao.xml" })
public class TestAccountDao {
	@Autowired
	private TestAccountService service;

	@org.springframework.stereotype.Service
	public static class TestAccountService {
		@Autowired
		private IUserDao dao;
		private User account;
		private List<User> list;

		public TestAccountService() {
			list = new ArrayList<User>();
		}

		@Transactional
		public void save() {
			account = new User();
			account.setName("test_name");
			dao.create(account);
		}

		@Transactional
		public void delete() {
			dao.delete(account);
		}

		@Transactional
		public void saves() {
			account = new User();
			account.setName("test_name");
			list.add(account);

			account = new User();
			account.setName("test_name2");
			list.add(account);

			dao.create(list);
		}

		@Transactional
		public void deletes() {
			dao.delete(list);
		}
	}

	@Test
	public void test() {
		service.save();
		service.delete();

		service.saves();
		service.deletes();
	}
}
