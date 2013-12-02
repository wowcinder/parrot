/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.parrot.shared.organization;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.voole.parrot.shared.EntityHasAutoId;
import com.voole.parrot.shared.account.Account;

/**
 * @author XuehuiHe
 * @date 2013年12月2日
 */
@Entity
//@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "account" }) })
public class Leader extends EntityHasAutoId {
	private static final long serialVersionUID = -1639115186521593269L;
	/**
	 * 管理的组织
	 */
	private Organization organization;
	private Account account;

	public Leader() {
	}

	@ManyToOne
	// (optional = false)
	public Organization getOrganization() {
		return organization;
	}

	@OneToOne(optional = false)
	public Account getAccount() {
		return account;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

}
