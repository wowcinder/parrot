/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.parrot.shared.entity.menu;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

import com.google.gwt.editor.client.Editor.Path;
import com.voole.parrot.shared.entity.EntityHasAutoId;

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
	private Integer pos;

	public MenuNode() {
	}

	@Column(length = 20, nullable = false)
	public String getName() {
		return name;
	}

	@ManyToOne
	@Path("parent.name")
	public MenuGroup getParent() {
		return parent;
	}

	public Integer getPos() {
		return pos;
	}

	public void setPos(Integer pos) {
		this.pos = pos;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setParent(MenuGroup parent) {
		this.parent = parent;
	}

}
