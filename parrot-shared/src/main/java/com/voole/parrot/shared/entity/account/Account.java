/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.parrot.shared.entity.account;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.voole.parrot.shared.entity.EntityHasAutoId;
import com.voole.parrot.shared.entity.authority.Authority;
import com.voole.parrot.shared.entity.organization.Leader;
import com.voole.parrot.shared.entity.organization.Member;

/**
 * @author XuehuiHe
 * @date 2013年11月27日
 */
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
public class Account extends EntityHasAutoId {
	private static final long serialVersionUID = 8206791127888110185L;

	private Leader leader;
	private Member member;
	private Set<Authority> authorities;

	private String name;
	private String password;

	@OneToOne(mappedBy = "account", cascade = { CascadeType.REMOVE })
	public Leader getLeader() {
		return leader;
	}

	@OneToOne(mappedBy = "account", cascade = { CascadeType.REMOVE })
	public Member getMember() {
		return member;
	}

	@Column(length = 100, nullable = false, unique = true)
	@NotNull
	@Length(min = 1, max = 100)
	public String getName() {
		return name;
	}

	@Column(length = 32, nullable = true)
	public String getPassword() {
		return password;
	}

	@ManyToMany
	@JoinTable(name = "account_authority", joinColumns = { @JoinColumn(name = "`authorities`") }, inverseJoinColumns = { @JoinColumn(name = "accounts") })
	public Set<Authority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Set<Authority> authorities) {
		this.authorities = authorities;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setLeader(Leader leader) {
		this.leader = leader;
	}

	public void setMember(Member member) {
		this.member = member;
	}

}
