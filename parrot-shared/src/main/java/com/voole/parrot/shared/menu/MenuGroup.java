/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.parrot.shared.menu;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import com.voole.parrot.shared.EntityWithOrderChildren;

/**
 * @author XuehuiHe
 * @date 2013年8月8日
 */
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
public class MenuGroup extends MenuNode implements EntityWithOrderChildren {
	private static final long serialVersionUID = 2138324039371528785L;

	private List<MenuNode> nodes;

	@OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
	@OrderBy("pos")
	public List<MenuNode> getNodes() {
		return nodes;
	}

	public void setNodes(List<MenuNode> nodes) {
		this.nodes = nodes;
	}

	@Override
	public void sortChildren() {
		if (nodes != null && nodes.size() > 0) {
			for (MenuNode node : nodes) {
				if (node instanceof MenuGroup) {
					((MenuGroup) node).sortChildren();
				}
				node.setPos(nodes.indexOf(node));
			}
		}
	}

}
