/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.parrot.shared.menu;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.voole.parrot.shared.EntityHasAutoId;

/**
 * @author XuehuiHe
 * @date 2013年8月22日
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class MenuNode extends EntityHasAutoId {
	private static final long serialVersionUID = -692226783023465869L;

	private String name;
	private MenuGroup parent;
	private MenuNode prev;

	public MenuNode() {
	}

	@Column(length = 20, nullable = false)
	public String getName() {
		return name;
	}

	@ManyToOne
	public MenuGroup getParent() {
		return parent;
	}

	@OneToOne
	public MenuNode getPrev() {
		return prev;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setParent(MenuGroup parent) {
		this.parent = parent;
	}

	public void setPrev(MenuNode prev) {
		this.prev = prev;
	}

}
