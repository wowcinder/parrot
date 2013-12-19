/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.parrot.service.dao.menu;

import java.util.ArrayList;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.voole.parrot.shared.entity.menu.MenuGroup;
import com.voole.parrot.shared.entity.menu.MenuNode;

/**
 * @author XuehuiHe
 * @date 2013年12月19日
 */
@Repository
public class MenuGroupDao extends MenuNodeDao<MenuGroup> implements
		IMenuGroupDao {

	@Override
	public MenuGroup findRootMenu() {
		MenuGroup mg = (MenuGroup) getCurrSession()
				.createCriteria(MenuGroup.class)
				.add(Restrictions.eq("name", "root"))
				.add(Restrictions.isNull("parent")).uniqueResult();
		if (mg == null) {
			mg = new MenuGroup();
			mg.setName("root");
			mg.setNodes(new ArrayList<MenuNode>());
			persist(mg);
		}
		return mg;
	}
}
