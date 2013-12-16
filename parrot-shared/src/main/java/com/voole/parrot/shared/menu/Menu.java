/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.parrot.shared.menu;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.voole.parrot.shared.authority.Authority;

/**
 * @author XuehuiHe
 * @date 2013年8月8日
 */
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
public class Menu extends MenuNode {
	private static final long serialVersionUID = -7971390347504825311L;

	private String token;

	private Authority requireAuthority;

	public Menu() {
	}

	@Column(length = 50, nullable = false, unique = true)
	@NotNull
	@Length(min = 1, max = 50)
	public String getToken() {
		return token;
	}

	@ManyToOne
	public Authority getRequireAuthority() {
		return requireAuthority;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public void setRequireAuthority(Authority requireAuthority) {
		this.requireAuthority = requireAuthority;
	}

}
