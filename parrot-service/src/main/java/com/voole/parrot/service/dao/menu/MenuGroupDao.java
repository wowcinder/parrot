/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.parrot.service.dao.menu;

import org.springframework.stereotype.Repository;

import com.voole.parrot.shared.entity.menu.MenuGroup;

/**
 * @author XuehuiHe
 * @date 2013年12月19日
 */
@Repository
public class MenuGroupDao extends MenuNodeDao<MenuGroup> implements
		IMenuGroupDao {
	@Override
	public void delete(MenuGroup e) {
		super.deleteFlush(e);
	}
}
