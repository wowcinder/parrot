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
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.voole.parrot.shared.entity.EntityHasAutoId;
import com.voole.parrot.shared.entity.authority.Authority;
import com.voole.parrot.shared.entity.authority.Role;

/**
 * @author XuehuiHe
 * @date 2013年12月2日
 */
@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "organization",
		"authority" }) })
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
public class TopOrganizationAuthority extends EntityHasAutoId {
	private static final long serialVersionUID = 4186121982307714960L;
	private TopOrganization organization;
	private Authority authority;

	private Set<Role> roles;
	private Set<SubOrganization> subOrganizations;

	public TopOrganizationAuthority() {
	}

	@ManyToOne(optional = false)
	public TopOrganization getOrganization() {
		return organization;
	}

	@ManyToOne
//	(optional = false)
	public Authority getAuthority() {
		return authority;
	}

	@ManyToMany
	@JoinTable(name = "role_authorities", joinColumns = { @JoinColumn(name = "authority") }, inverseJoinColumns = { @JoinColumn(name = "role") })
	public Set<Role> getRoles() {
		return roles;
	}

	@ManyToMany
	@JoinTable(name = "sub_organization_authorities", joinColumns = { @JoinColumn(name = "authority") }, inverseJoinColumns = { @JoinColumn(name = "sub_organization") })
	public Set<SubOrganization> getSubOrganizations() {
		return subOrganizations;
	}

	public void setSubOrganizations(Set<SubOrganization> subOrganizations) {
		this.subOrganizations = subOrganizations;
	}

	public void setOrganization(TopOrganization organization) {
		this.organization = organization;
	}

	public void setAuthority(Authority authority) {
		this.authority = authority;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

}
