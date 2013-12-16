/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.parrot.shared.organization;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

/**
 * @author XuehuiHe
 * @date 2013年12月2日
 */
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
public class TopOrganization extends Organization {
	private static final long serialVersionUID = -4769344538675997251L;

	private Set<TopOrganizationAuthority> authorities;

	@OneToMany(mappedBy = "organization", cascade = { CascadeType.REMOVE })
	public Set<TopOrganizationAuthority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Set<TopOrganizationAuthority> authorities) {
		this.authorities = authorities;
	}

}
