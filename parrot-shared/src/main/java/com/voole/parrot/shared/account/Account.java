/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.parrot.shared.account;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import com.voole.parrot.shared.EntityHasAutoId;
import com.voole.parrot.shared.organization.Leader;
import com.voole.parrot.shared.organization.Member;

/**
 * @author XuehuiHe
 * @date 2013年11月27日
 */
@Entity
public class Account extends EntityHasAutoId {
	private static final long serialVersionUID = 8206791127888110185L;

	private Leader leader;
	private Member member;

	@OneToOne(mappedBy = "account", cascade = { CascadeType.ALL })
	public Leader getLeader() {
		return leader;
	}

	@OneToOne(mappedBy = "account", cascade = { CascadeType.ALL })
	public Member getMember() {
		return member;
	}

	public void setLeader(Leader leader) {
		this.leader = leader;
	}

	public void setMember(Member member) {
		this.member = member;
	}

}
