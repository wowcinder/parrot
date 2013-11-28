/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.parrot.shared.user;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import com.voole.parrot.shared.EntityHasAutoId;
import com.voole.parrot.shared.authority.Authority;
import com.voole.parrot.shared.authority.AuthorityGroup;

/**
 * @author XuehuiHe
 * @date 2013年11月27日
 */
@Entity
public class Team extends EntityHasAutoId {
	private static final long serialVersionUID = -5080536443301833779L;
	private List<Authority> authorities;
	private List<AuthorityGroup> authorityGroups;

	@ManyToMany(cascade = { CascadeType.ALL }, mappedBy = "teams")
	public List<Authority> getAuthorities() {
		return authorities;
	}

	@ManyToMany(cascade = { CascadeType.ALL }, mappedBy = "teams")
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
