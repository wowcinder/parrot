/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.parrot.service.dao.menu;

import java.util.ArrayList;
import java.util.List;

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
	public N create(N t) {
		MenuGroup mg = findParent(t);
		if (t.getPos() != null && t.getPos() < mg.getNodes().size()) {
			mg.getNodes().add(t.getPos(), t);
		} else {
			mg.getNodes().add(t);
		}
		getSimpleDao().create(mg);
		return t;
	}

	@Override
	public N update(N t) {
		MenuGroup mg = findParent(t);
		getCurrSession().merge(t);
		getSimpleDao().create(mg);
		return t;
	}

	protected MenuGroup findParent(N t) {
		MenuGroup mg = t.getParent();
		if (mg != null) {
			mg = refresh(mg);
			for (MenuNode node : mg.getNodes()) {
				node.setParent(mg);
			}
		} else {
			mg = findRoot();
		}
		t.setParent(mg);
		return mg;
	}

	protected MenuGroup createRoot() {
		MenuGroup mg = new MenuGroup();
		mg.setName("root");
		mg.setNodes(new ArrayList<MenuNode>());
		getSimpleDao().<MenuGroup> create(mg);
		return mg;
	}

	@Override
	public MenuGroup findRootMenu() {
		MenuGroup mg = (MenuGroup) getCurrSession()
				.createCriteria(MenuGroup.class)
				.add(Restrictions.eq("name", "root"))
				.add(Restrictions.isNull("parent")).uniqueResult();
		if (mg == null) {
			mg = createRoot();
		}
		return mg;
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

	@Override
	public List<MenuNode> move(MenuNode p, List<MenuNode> items, int index) {
		refresh(items);
		for (MenuNode node : items) {
			node.setParent(null);
		}
		getCurrSession().flush();
		MenuGroup mg = null;
		if (p == null) {
			mg = findRootMenu();
		} else {
			mg = (MenuGroup) refresh(p);
		}
		mg.getNodes().addAll(index, items);
		for (MenuNode node : items) {
			node.setParent(mg);
		}
		getSimpleDao().update(mg);
		return items;
	}
}
