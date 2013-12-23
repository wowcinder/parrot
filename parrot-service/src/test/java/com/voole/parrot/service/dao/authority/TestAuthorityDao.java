package com.voole.parrot.service.dao.authority;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.voole.parrot.service.service.authority.AuthorityEntranceService;
import com.voole.parrot.service.service.authority.AuthorityService;
import com.voole.parrot.shared.entity.authority.Authority;
import com.voole.parrot.shared.entity.authority.AuthorityEntrance;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/TestEntityDao.xml" })
public class TestAuthorityDao {
	@Autowired
	private AuthorityService authorityService;
	@Autowired
	private AuthorityEntranceService entranceService;

	private Authority authority;
	private AuthorityEntrance entrance;
	List<Authority> authorities;

	public void save() {
		authorities = new ArrayList<Authority>();
		authority = new Authority();
		authorities.add(authority);

		entrance = new AuthorityEntrance();
		entrance.setName("entrance");

		authority.setEntrance(entrance);
		authority.setName("name");
		entrance.setAuthorities(authorities);
		entrance = entranceService.create(entrance);

	}

	@Test
	@Transactional
	@Rollback(false)
	public void update() {
		save();
		authorityService.getEntityDao().getCurrSession().flush();
		authorityService.getEntityDao().getCurrSession().clear();
		authority.setEntrance(entrance);
		entrance.setName("sjdlfjsldj");
		entranceService.update(entrance);
	}
}
