/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.parrot.service.dao.menu;

import org.springframework.stereotype.Repository;

import com.voole.parrot.service.dao.EntityDao;
import com.voole.parrot.shared.entity.menu.Menu;
import com.voole.parrot.shared.entity.menu.MenuGroup;
import com.voole.parrot.shared.entity.menu.MenuNode;

/**
 * @author XuehuiHe
 * @date 2013年12月16日
 */
@Repository
public class MenuNodeDao extends EntityDao<MenuNode> implements IMenuNodeDao {
	@Override
	public MenuNode persist(MenuNode t) {
		if (t instanceof Menu) {
			MenuGroup mg = t.getParent();
			if (mg != null) {
				refresh(mg);
				if (t.getPos() != null && t.getPos() < mg.getNodes().size()) {
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
