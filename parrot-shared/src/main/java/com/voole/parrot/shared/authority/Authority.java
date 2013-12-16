/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.parrot.shared.authority;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;

import com.voole.parrot.shared.organization.TopOrganizationAuthority;

/**
 * @author XuehuiHe
 * @date 2013年11月27日
 */
@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "entrance",
		"name" }) })
public class Authority implements Serializable {
	private static final long serialVersionUID = 4903463335586557358L;

	private String token;
	private AuthorityEntrance entrance;
	private String name;
	private List<TopOrganizationAuthority> organizationAuthorities;
	private Integer pos;

	public Authority() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "authority_token")
	@GenericGenerator(name = "authority_token", strategy = "com.voole.parrot.id.AuthorityIdentifierGenerator")
	@Column(length = 32)
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

	@OneToMany(mappedBy = "authority", cascade = { CascadeType.REMOVE })
	public List<TopOrganizationAuthority> getOrganizationAuthorities() {
		return organizationAuthorities;
	}

	public Integer getPos() {
		return pos;
	}

	public void setPos(Integer pos) {
		this.pos = pos;
	}

	public void setOrganizationAuthorities(
			List<TopOrganizationAuthority> organizationAuthorities) {
		this.organizationAuthorities = organizationAuthorities;
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
