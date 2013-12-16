/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.parrot.service.dao.menu;

import org.springframework.stereotype.Repository;

import com.voole.parrot.service.dao.EntityDao;
import com.voole.parrot.shared.menu.Menu;
import com.voole.parrot.shared.menu.MenuGroup;
import com.voole.parrot.shared.menu.MenuNode;

/**
 * @author XuehuiHe
 * @date 2013年12月16日
 */
@Repository
public class MenuNodeDao extends EntityDao<MenuNode> implements IMenuNodeDao {
	@Override
	public MenuNode persist(MenuNode t) {
		if (t instanceof Menu) {
			Menu menu = (Menu) t;
			MenuGroup mg = t.getParent();
			if (mg != null) {
				mg = em.merge(mg);
				menu.setParent(mg);
				if (t.getPos() != null) {
					mg.getNodes().add(t.getPos(), t);
				} else {
					mg.getNodes().add(t);
				}
				super.persist(mg);
				return t;
			}
		}
		return super.persist(t);
	}
}
