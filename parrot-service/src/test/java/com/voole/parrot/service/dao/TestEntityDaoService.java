package com.voole.parrot.service.dao;

import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.voole.parrot.service.dao.authority.IAuthorityDao;
import com.voole.parrot.service.dao.organization.IUserDao;
import com.voole.parrot.shared.entity.authority.Authority;
import com.voole.parrot.shared.entity.authority.AuthorityEntrance;
import com.voole.parrot.shared.entity.authority.Role;
import com.voole.parrot.shared.entity.organization.User;
import com.voole.parrot.shared.entity.organization.Leader;
import com.voole.parrot.shared.entity.organization.Member;
import com.voole.parrot.shared.entity.organization.Organization;
import com.voole.parrot.shared.entity.organization.SubOrganization;
import com.voole.parrot.shared.entity.organization.TopOrganization;
import com.voole.parrot.shared.entity.organization.TopOrganizationAuthority;

@Service
public class TestEntityDaoService {
	@Autowired
	private IUserDao accountDao;
	@Autowired
	private IAuthorityDao authorityDao;
	@Autowired
	private ISimpleDao simpleDao;
	private Random r;

	public TestEntityDaoService() {
		r = new Random();
	}

	@Transactional
	public User saveAccount(User account) {
		accountDao.create(account);
		return account;
	}

	@Transactional
	public Authority saveAuthority(Authority authority) {
		authorityDao.create(authority);
		return authority;
	}

	@Transactional
	public Leader saveLeader(Leader leader) {
		simpleDao.create(leader);
		return leader;
	}

	@Transactional
	public TopOrganization saveTopOrganization(TopOrganization top) {
		simpleDao.create(top);
		return top;
	}

	@Transactional
	public void deleteTopOrganization(TopOrganization top) {
		simpleDao.delete(top);
	}

	@Transactional
	public void deleteAuthority(Authority authority) {
		authorityDao.delete(authority);
	}

	@Transactional
	public Set<TopOrganizationAuthority> saveTopOrganizationAuthority(
			Set<TopOrganizationAuthority> authorities) {
		simpleDao.create(authorities);
		return authorities;
	}

	@Transactional
	public void delete(User account) {
		accountDao.delete(account);
	}

	public User createAccount() {
		return createAccount(null);
	}

	public User createAccount(Integer i) {
		User account = new User();
		if (i == null) {
			i = r.nextInt();
		}
		account.setName("test_name" + i);
		return account;
	}

	@Transactional
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
		simpleDao.create(entrance);
		return entrance;
	}

	public Leader createLeader(User account, Organization organization) {
		Leader leader = new Leader();
		leader.setAccount(account);
		leader.setOrganization(organization);
		return leader;
	}

	@Transactional
	public Member save(Member member) {
		simpleDao.create(member);
		return member;
	}

	public Member createMember(User account, Organization organization,
			Set<Role> roles) {
		Member member = new Member();
		member.setAccount(account);
		member.setOrganization(organization);
		member.setRoles(roles);
		return member;
	}

	@Transactional
	public SubOrganization save(SubOrganization sub) {
		simpleDao.create(sub);
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

	@Transactional
	public Role save(Role role) {
		simpleDao.create(role);
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

}
