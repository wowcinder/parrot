package com.voole.parrot.service.service.menu;

import java.util.List;

import com.voole.parrot.service.dao.menu.IMenuDao;
import com.voole.parrot.service.service.EntityServiceImpl;
import com.voole.parrot.shared.entity.menu.Menu;
import com.voole.parrot.shared.entity.menu.MenuNode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MenuServiceImpl extends EntityServiceImpl<Menu> implements
		MenuService {

	@Autowired
	private IMenuDao MenuDao;

	public IMenuDao getEntityDao() {
		return MenuDao;
	}

	@Override
	public List<MenuNode> move(MenuNode p, List<MenuNode> items, int index) {
		return MenuDao.move(p, items, index);
	}

}
