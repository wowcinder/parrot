/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.parrot.shared.entity.organization;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Length;

import com.voole.parrot.shared.entity.EntityHasAutoId;
import com.voole.parrot.shared.entity.authority.Authority;
import com.voole.parrot.shared.entity.authority.Role;

/**
 * @author XuehuiHe
 * @date 2013年11月27日
 */
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
public class User extends EntityHasAutoId {
	private static final long serialVersionUID = 8206791127888110185L;

	private Organization organization;
	private Set<Role> roles;
	private Set<Authority> authorities;

	private String name;
	private String password;
	private boolean isLeader;

	public User() {
		isLeader = true;
	}

	@Column(length = 100, nullable = false, unique = true)
	@NotNull
	@Length(min = 1, max = 100)
	public String getName() {
		return name;
	}

	@Column(length = 32, nullable = true)
	public String getPassword() {
		return password;
	}

	@ManyToMany
	@JoinTable(name = "account_authority", joinColumns = { @JoinColumn(name = "`authorities`") }, inverseJoinColumns = { @JoinColumn(name = "accounts") })
	public Set<Authority> getAuthorities() {
		return authorities;
	}

	@ManyToOne
	public Organization getOrganization() {
		return organization;
	}

	@ManyToMany
	@JoinTable(name = "member_roles", joinColumns = { @JoinColumn(name = "member") }, inverseJoinColumns = { @JoinColumn(name = "role") })
	public Set<Role> getRoles() {
		return roles;
	}

	@Type(type = "boolean")
	public boolean isLeader() {
		return isLeader;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public void setLeader(boolean isLeader) {
		this.isLeader = isLeader;
	}

	public void setAuthorities(Set<Authority> authorities) {
		this.authorities = authorities;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setName(String name) {
		this.name = name;
	}

}
