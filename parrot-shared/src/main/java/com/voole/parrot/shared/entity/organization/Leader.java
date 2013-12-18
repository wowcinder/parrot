/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.parrot.shared.entity.organization;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.voole.parrot.shared.entity.EntityHasAutoId;
import com.voole.parrot.shared.entity.account.Account;

/**
 * @author XuehuiHe
 * @date 2013年12月2日
 */
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
public class Leader extends EntityHasAutoId {
	private static final long serialVersionUID = -1639115186521593269L;
	/**
	 * 管理的组织
	 */
	private Organization organization;
	private Account account;

	public Leader() {
	}

	@ManyToOne(optional = false)
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
