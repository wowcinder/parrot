/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.parrot.shared.entity.authority;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.voole.parrot.shared.entity.EntityHasAutoId;
import com.voole.parrot.shared.entity.user.User;

/**
 * @author XuehuiHe
 * @date 2013年12月2日
 */
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
public class Role extends EntityHasAutoId {
	private static final long serialVersionUID = -2172965273200082653L;

	private String name;
	private Set<User> users;
	private Set<Authority> authorities;

	@Column(nullable = false, length = 50, unique = true)
	@NotNull
	@Length(min = 1, max = 50)
	public String getName() {
		return name;
	}

	@ManyToMany
	@JoinTable(name = "user_role", joinColumns = { @JoinColumn(name = "role") }, inverseJoinColumns = { @JoinColumn(name = "user") })
	public Set<User> getUsers() {
		return users;
	}

	@ManyToMany
	@JoinTable(name = "role_authority", joinColumns = { @JoinColumn(name = "role") }, inverseJoinColumns = { @JoinColumn(name = "authority") })
	public Set<Authority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Set<Authority> authorities) {
		this.authorities = authorities;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public void setName(String name) {
		this.name = name;
	}

}
