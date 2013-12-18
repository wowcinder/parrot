/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.parrot.shared.entity.organization;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.voole.parrot.shared.entity.EntityHasAutoId;
import com.voole.parrot.shared.entity.account.Account;
import com.voole.parrot.shared.entity.authority.Role;

/**
 * @author XuehuiHe
 * @date 2013年12月2日
 */
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
public class Member extends EntityHasAutoId {

	private static final long serialVersionUID = 6959507466773040621L;
	/**
	 * 所属的组织
	 */
	private Organization organization;
	private Set<Role> roles;
	private Account account;

	@ManyToOne(optional = false)
	public Organization getOrganization() {
		return organization;
	}

	@OneToOne(optional = false)
	public Account getAccount() {
		return account;
	}

	@ManyToMany
	@JoinTable(name = "member_roles", joinColumns = { @JoinColumn(name = "member") }, inverseJoinColumns = { @JoinColumn(name = "role") })
	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

}
