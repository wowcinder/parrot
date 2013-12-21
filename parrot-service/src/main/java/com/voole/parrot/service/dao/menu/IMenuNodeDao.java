/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.parrot.service.dao.menu;

import java.util.List;

import com.voole.parrot.service.dao.IEntityDao;
import com.voole.parrot.shared.entity.menu.MenuGroup;
import com.voole.parrot.shared.entity.menu.MenuNode;

/**
 * @author XuehuiHe
 * @date 2013年12月19日
 */
public interface IMenuNodeDao<N extends MenuNode> extends IEntityDao<N> {
	public N save(N t);

	public N update(N t);

	List<MenuNode> move(MenuNode p, List<MenuNode> items, int index);
	
	MenuGroup findRootMenu();
}
