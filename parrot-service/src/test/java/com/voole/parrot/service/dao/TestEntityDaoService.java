package com.voole.parrot.service.dao;

import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.voole.parrot.service.dao.account.IAccountDao;
import com.voole.parrot.service.dao.authority.IAuthorityDao;
import com.voole.parrot.service.dao.organization.ILeaderDao;
import com.voole.parrot.service.dao.organization.IMemberDao;
import com.voole.parrot.service.dao.organization.IOrganizationDao;
import com.voole.parrot.service.dao.organization.IRoleDao;
import com.voole.parrot.service.dao.organization.ITopOrganizationAuthorityDao;
import com.voole.parrot.shared.account.Account;
import com.voole.parrot.shared.authority.Authority;
import com.voole.parrot.shared.authority.Role;
import com.voole.parrot.shared.organization.Leader;
import com.voole.parrot.shared.organization.Member;
import com.voole.parrot.shared.organization.Organization;
import com.voole.parrot.shared.organization.SubOrganization;
import com.voole.parrot.shared.organization.TopOrganization;
import com.voole.parrot.shared.organization.TopOrganizationAuthority;

@Service
public class TestEntityDaoService {
	@Autowired
	private IAccountDao accountDao;
	@Autowired
	private IAuthorityDao authorityDao;
	@Autowired
	private ILeaderDao leaderDao;
	@Autowired
	private IMemberDao memberDao;
	@Autowired
	private IOrganizationDao organizationDao;
	@Autowired
	private IRoleDao roleDao;
	@Autowired
	private ITopOrganizationAuthorityDao topOrganizationAuthorityDao;
	private Random r;

	public TestEntityDaoService() {
		r = new Random();
	}

	@Transactional
	public Account saveAccount(Account account) {
		accountDao.save(account);
		return account;
	}

	@Transactional
	public Authority saveAuthority(Authority authority) {
		authorityDao.save(authority);
		return authority;
	}

	@Transactional
	public Leader saveLeader(Leader leader) {
		leaderDao.save(leader);
		return leader;
	}

	@Transactional
	public TopOrganization saveTopOrganization(TopOrganization top) {
		organizationDao.save(top);
		return top;
	}

	@Transactional
	public void deleteTopOrganization(TopOrganization top) {
		organizationDao.delete(top);
	}

	@Transactional
	public void deleteAuthority(Authority authority) {
		authorityDao.delete(authority);
	}

	@Transactional
	public Set<TopOrganizationAuthority> saveTopOrganizationAuthority(
			Set<TopOrganizationAuthority> authorities) {
		topOrganizationAuthorityDao.save(authorities);
		return authorities;
	}

	@Transactional
	public void delete(Account account) {
		accountDao.delete(account);
	}

	public Account createAccount() {
		return createAccount(null);
	}

	public Account createAccount(Integer i) {
		Account account = new Account();
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
		authority.setEntrance("entrance" + (i == null ? r.nextInt() : i));
		authority.setName("name" + (j == null ? r.nextInt() : j));
		return authority;
	}

	public Leader createLeader(Account account, Organization organization) {
		Leader leader = new Leader();
		leader.setAccount(account);
		leader.setOrganization(organization);
		return leader;
	}

	@Transactional
	public Member save(Member member) {
		memberDao.save(member);
		return member;
	}

	public Member createMember(Account account, Organization organization,
			Set<Role> roles) {
		Member member = new Member();
		member.setAccount(account);
		member.setOrganization(organization);
		member.setRoles(roles);
		return member;
	}

	@Transactional
	public SubOrganization save(SubOrganization sub) {
		organizationDao.save(sub);
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
		roleDao.save(role);
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
