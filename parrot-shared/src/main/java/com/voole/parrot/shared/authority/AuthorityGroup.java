/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.parrot.shared.authority;

import java.util.List;

import javax.persistence.Entity;
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
public class AuthorityGroup extends EntityHasAutoId {
	private static final long serialVersionUID = -3067079705996938972L;
	private List<User> users;
	private List<Team> teams;

	public AuthorityGroup() {
	}

	@ManyToMany
	@JoinTable(name = "authority_group_user")
	public List<User> getUsers() {
		return users;
	}

	@ManyToMany
	@JoinTable(name = "authority_group_team")
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
