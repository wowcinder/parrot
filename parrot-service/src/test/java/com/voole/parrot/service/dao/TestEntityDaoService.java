package com.voole.parrot.service.dao;

import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.voole.parrot.service.service.authority.AuthorityEntranceService;
import com.voole.parrot.service.service.authority.AuthorityService;
import com.voole.parrot.service.service.authority.RoleService;
import com.voole.parrot.service.service.organization.SubOrganizationService;
import com.voole.parrot.service.service.organization.TopOrganizationAuthorityService;
import com.voole.parrot.service.service.organization.TopOrganizationService;
import com.voole.parrot.service.service.organization.UserService;
import com.voole.parrot.shared.entity.authority.Authority;
import com.voole.parrot.shared.entity.authority.AuthorityEntrance;
import com.voole.parrot.shared.entity.authority.Role;
import com.voole.parrot.shared.entity.organization.Organization;
import com.voole.parrot.shared.entity.organization.SubOrganization;
import com.voole.parrot.shared.entity.organization.TopOrganization;
import com.voole.parrot.shared.entity.organization.TopOrganizationAuthority;
import com.voole.parrot.shared.entity.organization.User;

@Service
public class TestEntityDaoService {
	@Autowired
	private UserService accountDao;
	@Autowired
	private AuthorityService authorityDao;
	@Autowired
	private AuthorityEntranceService entranceService;
	@Autowired
	private TopOrganizationService topOrganizationService;
	@Autowired
	private TopOrganizationAuthorityService topOrganizationAuthorityService;
	@Autowired
	private SubOrganizationService subOrganizationService;
	@Autowired
	private RoleService roleService;
	private Random r;

	public TestEntityDaoService() {
		r = new Random();
	}

	public User saveAccount(User account) {
		accountDao.persist(account);
		return account;
	}

	public Authority saveAuthority(Authority authority) {
		authorityDao.persist(authority);
		return authority;
	}

	public TopOrganization saveTopOrganization(TopOrganization top) {
		topOrganizationService.persist(top);
		return top;
	}

	public void deleteTopOrganization(TopOrganization top) {
		topOrganizationService.delete(top);
	}

	public void deleteAuthority(Authority authority) {
		authorityDao.delete(authority);
	}

	public Set<TopOrganizationAuthority> saveTopOrganizationAuthority(
			Set<TopOrganizationAuthority> authorities) {
		topOrganizationAuthorityService.persist(authorities);
		return authorities;
	}

	public void delete(User account) {
		accountDao.delete(account);
	}

	public User createAccount(Organization organization) {
		return createAccount(organization, null);
	}

	public User createAccount(Organization organization, Integer i) {
		User account = new User();
		account.setOrganization(organization);
		if (i == null) {
			i = r.nextInt();
		}
		account.setName("test_name" + i);
		return account;
	}

	public Authority createAuthority() {
		return createAuthority(null, null);
	}

	public Authority createAuthority(Integer i, Integer j) {
		Authority authority = new Authority();
		authority.setEntrance(createEntrance(i));
		authority.setName("name" + (j == null ? r.nextInt() : j));
		return authority;
	}

	public AuthorityEntrance createEntrance(Integer i) {
		AuthorityEntrance entrance = new AuthorityEntrance();
		entrance.setName("entrance" + (i == null ? r.nextInt() : i));
		entranceService.persist(entrance);
		return entrance;
	}

	public SubOrganization save(SubOrganization sub) {
		subOrganizationService.persist(sub);
		return sub;
	}

	public SubOrganization createSub(Organization organization,
			Set<TopOrganizationAuthority> authorities) {
		SubOrganization sub = new SubOrganization();
		sub.setParentOrganization(organization);
		sub.setName("name" + r.nextInt(10000));
		sub.setAuthorities(authorities);
		return sub;
	}

	public Role save(Role role) {
		roleService.persist(role);
		return role;
	}

	public Role createRole(Organization organization,
			Set<TopOrganizationAuthority> authorities) {
		Role role = new Role();
		role.setName("name" + r.nextInt(10000));
		role.setOrganization(organization);
		role.setAuthorities(authorities);
		return role;
	}

	public TopOrganization createTopOrganization() {
		TopOrganization top = new TopOrganization();
		top.setName("name");
		return top;
	}

	public TopOrganizationAuthority createTopOrganizationAuthority(
			TopOrganization organization, Authority authority) {
		TopOrganizationAuthority topOrganizationAuthority = new TopOrganizationAuthority();
		topOrganizationAuthority.setAuthority(authority);
		topOrganizationAuthority.setOrganization(organization);
		return topOrganizationAuthority;
	}

	public void updateUser(User account, EntityUpdater<User> updater) {
		accountDao.update(account, updater);
	}

}
