/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.parrot.service.dao.menu;

import java.util.ArrayList;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.voole.parrot.service.dao.EntityDao;
import com.voole.parrot.shared.entity.menu.MenuGroup;
import com.voole.parrot.shared.entity.menu.MenuNode;

/**
 * @author XuehuiHe
 * @date 2013年12月19日
 */
@Repository
public class MenuNodeDao<N extends MenuNode> extends EntityDao<N> implements
		IMenuNodeDao<N> {

	@Override
	public N save(N t) {
		MenuGroup mg = t.getParent();
		if (mg != null) {
			mg = refresh(mg);
			for (MenuNode node : mg.getNodes()) {
				node.setParent(mg);
			}
		} else {
			mg = findRoot();
			t.setParent(mg);
		}
		if (t.getPos() != null && t.getPos() < mg.getNodes().size()) {
			mg.getNodes().add(t.getPos(), t);
		} else {
			mg.getNodes().add(t);
		}
		getSimpleDao().<MenuGroup> persist(mg);
		return t;
	}

	protected MenuGroup findRoot() {
		MenuGroup mg = (MenuGroup) getCurrSession()
				.createCriteria(MenuGroup.class)
				.add(Restrictions.eq("name", "root"))
				.add(Restrictions.isNull("parent")).uniqueResult();
		if (mg == null) {
			mg = new MenuGroup();
			mg.setName("root");
			mg.setNodes(new ArrayList<MenuNode>());
		}
		return mg;
	}
}
