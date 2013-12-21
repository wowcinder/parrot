package com.voole.parrot.service.dao.authority;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.voole.parrot.service.dao.ISimpleDao;
import com.voole.parrot.shared.entity.authority.Authority;
import com.voole.parrot.shared.entity.authority.AuthorityEntrance;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/TestEntityDao.xml" })
public class TestAuthorityDao {
	@Autowired
	private TestAuthorityService service;

	@Service
	public static class TestAuthorityService {
		@Autowired
		private IAuthorityDao dao;
		@Autowired
		private ISimpleDao dao2;
		private Authority authority;
		private AuthorityEntrance entrance;

		@Transactional
		public void save() {
			List<Authority> authorities = new ArrayList<Authority>();
			authority = new Authority();
			authorities.add(authority);

			entrance = new AuthorityEntrance();
			entrance.setName("entrance");
			entrance.setAuthorities(authorities);

			authority.setEntrance(entrance);
			authority.setName("name");
			dao2.persist(entrance);
			System.out.println(entrance);
			// dao.save(authority);
		}

		@Transactional
		public void delete() {
			// dao.delete(authority);
			dao2.delete(entrance);
		}
	}

	@Test
	public void test() {
		service.save();
		service.delete();
	}
}
