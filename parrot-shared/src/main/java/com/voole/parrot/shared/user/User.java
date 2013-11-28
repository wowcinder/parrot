/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.parrot.shared.user;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.voole.parrot.shared.EntityHasAutoId;
import com.voole.parrot.shared.authority.Authority;
import com.voole.parrot.shared.authority.AuthorityGroup;

/**
 * @author XuehuiHe
 * @date 2013年11月27日
 */
@Entity
public class User extends EntityHasAutoId {
	private static final long serialVersionUID = 8206791127888110185L;
	private List<Authority> authorities;
	private List<AuthorityGroup> authorityGroups;
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ManyToMany
	@JoinTable(name = "authority_user", inverseJoinColumns = { @JoinColumn(name = "authority_id") }, joinColumns = { @JoinColumn(name = "user_id") })
	public List<Authority> getAuthorities() {
		return authorities;
	}

	@ManyToMany(cascade = { CascadeType.ALL }, mappedBy = "users")
	public List<AuthorityGroup> getAuthorityGroups() {
		return authorityGroups;
	}

	public void setAuthorities(List<Authority> authorities) {
		this.authorities = authorities;
	}

	public void setAuthorityGroups(List<AuthorityGroup> authorityGroups) {
		this.authorityGroups = authorityGroups;
	}
}
