package com.voole.parrot.service.dao;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.voole.parrot.shared.entity.authority.Authority;
import com.voole.parrot.shared.entity.authority.Role;
import com.voole.parrot.shared.entity.organization.SubOrganization;
import com.voole.parrot.shared.entity.organization.TopOrganization;
import com.voole.parrot.shared.entity.organization.TopOrganizationAuthority;
import com.voole.parrot.shared.entity.organization.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/TestEntityDao.xml" })
public class TestEntityDao2 {
	@Autowired
	private TestEntityDaoService service;

	@Test
	public void test() {
		TopOrganization top = service.createTopOrganization();
		service.saveTopOrganization(top);

		Set<TopOrganizationAuthority> authorities = new HashSet<TopOrganizationAuthority>();
		Authority authority = service.createAuthority();
		authorities.add(service.createTopOrganizationAuthority(top,
				service.saveAuthority(authority)));
		authorities.add(service.createTopOrganizationAuthority(top,
				service.saveAuthority(service.createAuthority())));
		authorities.add(service.createTopOrganizationAuthority(top,
				service.saveAuthority(service.createAuthority())));
		service.saveTopOrganizationAuthority(authorities);

		Set<Role> roles = new HashSet<Role>();

		Role role = service.createRole(top, authorities);
		service.save(role);
		roles.add(role);

		User account = service.saveAccount(service.createAccount(top));
		account.setLeader(true);
		account.setRoles(roles);
		service.updateUser(account, new EntityUpdater<User>() {

			@Override
			public void invoke(User old, User e) {
				old.setLeader(e.isLeader());
				old.setRoles(e.getRoles());
			}
		});

		SubOrganization sub = service.createSub(top, authorities);
		service.save(sub);

		role = service.createRole(sub, authorities);
		service.save(role);
		roles.clear();
		roles.add(role);
		account = service.saveAccount(service.createAccount(sub));
		account.setLeader(true);
		account.setRoles(roles);

		service.delete(account);
		service.deleteTopOrganization(top);
		// service.deleteAuthority(authority);
	}
}
