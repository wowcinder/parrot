package com.voole.parrot.service.dao.authority;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.voole.parrot.shared.authority.Authority;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/TestEntityDao.xml" })
public class TestAuthorityDao {
	@Autowired
	private TestAuthorityService service;

	@Service
	public static class TestAuthorityService {
		@Autowired
		private IAuthorityDao dao;
		private Authority authority;

		@Transactional
		public void save() {
			authority = new Authority();
			authority.setEntrance("entrance");
			authority.setName("name");
			dao.save(authority);
		}

		@Transactional
		public void delete() {
			dao.delete(authority);
		}
	}

	@Test
	public void test() {
		service.save();
		service.delete();
	}
}
