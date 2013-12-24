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
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.google.gwt.editor.client.Editor.Path;
import com.voole.parrot.shared.entity.EntityHasAutoId;
import com.voole.parrot.shared.entity.user.User;

/**
 * @author XuehuiHe
 * @date 2013年11月27日
 */
@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "entrance",
		"name" }) })
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
public class Authority extends EntityHasAutoId {
	private static final long serialVersionUID = 4903463335586557358L;

	private String token;
	@Path("entrance.name")
	private AuthorityEntrance entrance;
	private String name;
	private Integer pos;
	private Set<Authority> dependencies;
	private Set<Authority> reDependencies;
	private Set<User> users;
	private Set<Role> roles;

	public Authority() {
	}

	@Column(length = 32, nullable = false)
	@NotNull
	public String getToken() {
		return token;
	}

	@ManyToOne(optional = false)
	public AuthorityEntrance getEntrance() {
		return entrance;
	}

	@NotNull
	@Column(nullable = false, length = 100)
	@Length(min = 1, max = 100)
	public String getName() {
		return name;
	}

	@ManyToMany
	@JoinTable(name = "authority_depend_authority", joinColumns = { @JoinColumn(name = "dependencies") }, inverseJoinColumns = { @JoinColumn(name = "reDependencies") })
	public Set<Authority> getDependencies() {
		return dependencies;
	}

	@ManyToMany
	@JoinTable(name = "authority_depend_authority", joinColumns = { @JoinColumn(name = "reDependencies") }, inverseJoinColumns = { @JoinColumn(name = "dependencies") })
	public Set<Authority> getReDependencies() {
		return reDependencies;
	}

	@ManyToMany
	@JoinTable(name = "user_authority", joinColumns = { @JoinColumn(name = "user") }, inverseJoinColumns = { @JoinColumn(name = "authority") })
	public Set<User> getUsers() {
		return users;
	}

	@ManyToMany
	@JoinTable(name = "role_authority", joinColumns = { @JoinColumn(name = "authority") }, inverseJoinColumns = { @JoinColumn(name = "role") })
	public Set<Role> getRoles() {
		return roles;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public void setDependencies(Set<Authority> dependencies) {
		this.dependencies = dependencies;
	}

	public void setReDependencies(Set<Authority> reDependencies) {
		this.reDependencies = reDependencies;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Integer getPos() {
		return pos;
	}

	public void setPos(Integer pos) {
		this.pos = pos;
	}

	public void setEntrance(AuthorityEntrance entrance) {
		this.entrance = entrance;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public void setName(String name) {
		this.name = name;
	}
}
