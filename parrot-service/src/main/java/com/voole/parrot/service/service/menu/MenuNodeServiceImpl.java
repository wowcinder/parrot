package com.voole.parrot.service.service.menu;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.voole.parrot.service.dao.menu.IMenuNodeDao;
import com.voole.parrot.service.service.EntityServiceImpl;
import com.voole.parrot.shared.entity.menu.MenuNode;

@Service
@Transactional
public class MenuNodeServiceImpl extends EntityServiceImpl<MenuNode> implements
		MenuNodeService {

	@Resource(name = "menuNodeDao")
	private IMenuNodeDao<MenuNode> MenuNodeDao;

	public IMenuNodeDao<MenuNode> getEntityDao() {
		return MenuNodeDao;
	}

}
