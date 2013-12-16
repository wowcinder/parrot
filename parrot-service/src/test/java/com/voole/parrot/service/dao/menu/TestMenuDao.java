/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.parrot.service.dao.menu;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.voole.parrot.shared.menu.Menu;
import com.voole.parrot.shared.menu.MenuGroup;
import com.voole.parrot.shared.menu.MenuNode;

/**
 * @author XuehuiHe
 * @date 2013年12月16日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/TestEntityDao.xml" })
public class TestMenuDao {
	@Autowired
	private MenuNodeDao dao;

	@Test
	@Transactional
	@Rollback(false)
	public void test2() {
		MenuGroup mg = new MenuGroup();
		mg.setName("mg");

		List<MenuNode> menus = new ArrayList<MenuNode>();
		for (int i = 0; i < 5; i++) {
			Menu menu = new Menu();
			menu.setName("sts" + i);
			menu.setToken("sdf" + i);
			menu.setParent(mg);

			menus.add(menu);
		}

		mg.setNodes(menus);

		dao.persist(mg);

		dao.getCurrSession().flush();

		Menu menu = new Menu();
		menu.setPos(7);
		menu.setName("testssdsf");
		menu.setParent(mg);
		menu.setToken("kkkk");

//		mg.getNodes().add(1, menu);

		dao.persist(menu);

		dao.getCurrSession().flush();
	}

	// @Test
	// @Transactional
	// @Rollback(false)
	public void test3() {
		MenuGroup mg = new MenuGroup();
		mg.setId(1L);

		Menu menu = new Menu();
		menu.setPos(1);
		menu.setName("testssdsf");
		menu.setParent(mg);
		menu.setToken("kkkk");

		dao.persist(menu);
	}
}
