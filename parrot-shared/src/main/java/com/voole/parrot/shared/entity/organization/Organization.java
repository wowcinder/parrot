/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.parrot.shared.entity.organization;

import java.util.HashSet;
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

import com.voole.parrot.shared.entity.EntityHasAutoId;
import com.voole.parrot.shared.entity.authority.Role;

/**
 * @author XuehuiHe
 * @date 2013年11月27日
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
public abstract class Organization extends EntityHasAutoId {
	private static final long serialVersionUID = -6873521357227566626L;
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
	private Set<User> members;
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
	public Set<Role> getRoles() {
		return roles;
	}

	@OneToMany(mappedBy = "parentOrganization", cascade = { CascadeType.REMOVE })
	public Set<SubOrganization> getSubOrganizations() {
		return subOrganizations;
	}

	@OneToMany(mappedBy = "organization", cascade = { CascadeType.REMOVE })
	public Set<User> getMembers() {
		return members;
	}

	@Transient
	public Set<User> getLeaders() {
		Set<User> users = getMembers();
		if (users != null && users.size() > 0) {
			Set<User> leaders = new HashSet<User>();
			for (User user : users) {
				if (user.isLeader()) {
					leaders.add(user);
				}
			}
			return leaders;
		}
		return null;
	}

	@Column(length = 50, nullable = false)
	@NotNull
	@Length(min = 1, max = 50)
	public String getName() {
		return name;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public void setSubOrganizations(Set<SubOrganization> subOrganizations) {
		this.subOrganizations = subOrganizations;
	}

	public void setMembers(Set<User> members) {
		this.members = members;
	}

	public void setName(String name) {
		this.name = name;
	}

}
