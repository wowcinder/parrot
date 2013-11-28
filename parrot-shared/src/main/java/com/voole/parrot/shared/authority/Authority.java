/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.parrot.shared.authority;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.voole.parrot.shared.EntityHasAutoId;
import com.voole.parrot.shared.user.Team;
import com.voole.parrot.shared.user.User;

/**
 * @author XuehuiHe
 * @date 2013年11月27日
 */
@Entity
public class Authority extends EntityHasAutoId {
	private static final long serialVersionUID = 4903463335586557358L;

	private List<User> users;
	private List<Team> teams;
	private String name;

	public Authority() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ManyToMany
	@JoinTable(name = "authority_user", joinColumns = { @JoinColumn(name = "authority_id") }, inverseJoinColumns = { @JoinColumn(name = "user_id") })
	public List<User> getUsers() {
		return users;
	}

	@ManyToMany
	@JoinTable(name = "authority_team")
	public List<Team> getTeams() {
		return teams;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}

}
