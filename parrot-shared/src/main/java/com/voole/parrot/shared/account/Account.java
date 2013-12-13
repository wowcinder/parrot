/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.parrot.shared.account;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

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

	private String name;

	@OneToOne(mappedBy = "account")
	public Leader getLeader() {
		return leader;
	}

	@OneToOne(mappedBy = "account")
	public Member getMember() {
		return member;
	}

	@Column(length = 100, nullable = false, unique = true)
	@NotNull
	@Length(min = 1, max = 100)
	public String getName() {
		return name;
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
