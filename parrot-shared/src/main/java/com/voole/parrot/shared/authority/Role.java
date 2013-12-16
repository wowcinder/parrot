/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.parrot.shared.authority;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.voole.parrot.shared.EntityHasAutoId;
import com.voole.parrot.shared.organization.Member;
import com.voole.parrot.shared.organization.Organization;
import com.voole.parrot.shared.organization.TopOrganizationAuthority;

/**
 * @author XuehuiHe
 * @date 2013年12月2日
 */
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
public class Role extends EntityHasAutoId {
	private static final long serialVersionUID = -2172965273200082653L;
	/**
	 * 所属组织
	 */
	private Organization organization;
	/**
	 * 角色的权限
	 */
	private Set<TopOrganizationAuthority> authorities;
	/**
	 * 角色名称
	 */
	private String name;

	private Set<Member> members;

	@Column(nullable = false, length = 50)
	@NotNull
	@Length(min = 1, max = 50)
	public String getName() {
		return name;
	}

	@ManyToMany
	@JoinTable(name = "role_authorities", joinColumns = { @JoinColumn(name = "role") }, inverseJoinColumns = { @JoinColumn(name = "authority") })
	public Set<TopOrganizationAuthority> getAuthorities() {
		return authorities;
	}

	@ManyToOne(optional = false)
	public Organization getOrganization() {
		return organization;
	}

	@ManyToMany
	@JoinTable(name = "member_roles", joinColumns = { @JoinColumn(name = "role") }, inverseJoinColumns = { @JoinColumn(name = "member") })
	public Set<Member> getMembers() {
		return members;
	}

	public void setMembers(Set<Member> members) {
		this.members = members;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public void setAuthorities(Set<TopOrganizationAuthority> authorities) {
		this.authorities = authorities;
	}

}
