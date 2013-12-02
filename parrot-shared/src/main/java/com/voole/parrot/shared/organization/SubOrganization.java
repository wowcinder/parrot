/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.parrot.shared.organization;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 * @author XuehuiHe
 * @date 2013年12月2日
 */
@Entity
public class SubOrganization extends Organization {

	private static final long serialVersionUID = -111390578929008826L;
	private Organization parentOrganization;
	private Set<TopOrganizationAuthority> authorities;

	@ManyToMany
	@JoinTable(name = "sub_organization_authorities", joinColumns = { @JoinColumn(name = "sub_organization") }, inverseJoinColumns = { @JoinColumn(name = "authority") })
	public Set<TopOrganizationAuthority> getAuthorities() {
		return authorities;
	}

	@ManyToOne(optional = false)
	@NotNull
	public Organization getParentOrganization() {
		return parentOrganization;
	}

	public void setParentOrganization(Organization parentOrganization) {
		this.parentOrganization = parentOrganization;
	}

	public void setAuthorities(Set<TopOrganizationAuthority> authorities) {
		this.authorities = authorities;
	}
}
