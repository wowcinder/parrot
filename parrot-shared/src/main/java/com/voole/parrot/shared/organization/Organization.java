/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.parrot.shared.organization;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.voole.parrot.shared.EntityHasAutoId;
import com.voole.parrot.shared.authority.Role;

/**
 * @author XuehuiHe
 * @date 2013年11月27日
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Organization extends EntityHasAutoId {
	private static final long serialVersionUID = -6873521357227566626L;
	/**
	 * 该组织的领导者们
	 */
	private Set<Leader> leaders;
	/**
	 * 该组织拥有的角色
	 */
	private Set<Role> roles;
	/**
	 * 子组织
	 */
	private Set<SubOrganization> subOrganizations;
	/**
	 * 该组织的成员
	 */
	private Set<Member> members;
	/**
	 * 组织名称
	 */
	private String name;

	public Organization() {
	}

	@Transient
	public abstract Set<TopOrganizationAuthority> getAuthorities();

	public abstract void setAuthorities(
			Set<TopOrganizationAuthority> authorities);

	@OneToMany(mappedBy = "organization", cascade = { CascadeType.REMOVE })
	public Set<Leader> getLeaders() {
		return leaders;
	}

	@OneToMany(mappedBy = "organization", cascade = { CascadeType.REMOVE })
	public Set<Role> getRoles() {
		return roles;
	}

	@OneToMany(mappedBy = "parentOrganization", cascade = { CascadeType.REMOVE })
	public Set<SubOrganization> getSubOrganizations() {
		return subOrganizations;
	}

	@OneToMany(mappedBy = "organization", cascade = { CascadeType.REMOVE })
	public Set<Member> getMembers() {
		return members;
	}

	@Column(length = 50, nullable = false)
	@NotNull
	@Length(min = 1, max = 50)
	public String getName() {
		return name;
	}

	public void setLeaders(Set<Leader> leaders) {
		this.leaders = leaders;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public void setSubOrganizations(Set<SubOrganization> subOrganizations) {
		this.subOrganizations = subOrganizations;
	}

	public void setMembers(Set<Member> members) {
		this.members = members;
	}

	public void setName(String name) {
		this.name = name;
	}

}
