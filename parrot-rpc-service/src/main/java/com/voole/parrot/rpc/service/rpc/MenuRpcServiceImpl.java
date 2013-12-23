package com.voole.parrot.rpc.service.rpc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.voole.parrot.gwt.common.shared.rpcservice.MenuNodeRpcService;
import com.voole.parrot.service.dao.EntityUpdater;
import com.voole.parrot.service.service.menu.MenuGroupService;
import com.voole.parrot.service.service.menu.MenuService;
import com.voole.parrot.shared.entity.menu.Menu;
import com.voole.parrot.shared.entity.menu.MenuGroup;
import com.voole.parrot.shared.entity.menu.MenuNode;
import com.voole.parrot.shared.exception.SharedException;

@Service
@Transactional
public class MenuRpcServiceImpl implements MenuNodeRpcService {
	@Autowired
	private MenuService menuService;
	@Autowired
	private MenuGroupService menuGroupService;

	@Override
	public Menu persist(Menu menu) throws SharedException {
		return menuService.persist(menu);
	}

	@Override
	public Menu update(Menu menu) throws SharedException {
		return menuService.update(menu, new EntityUpdater<Menu>() {
			@Override
			public void invoke(Menu old, Menu e) {
				old.setName(e.getName());
				old.setToken(e.getToken());
				old.setRequireAuthority(e.getRequireAuthority());
			}

		});
	}

	@Override
	public MenuGroup persist(MenuGroup mg) throws SharedException {
		return menuGroupService.persist(mg);
	}

	@Override
	public MenuGroup update(MenuGroup mg) throws SharedException {
		return menuGroupService.update(mg, new EntityUpdater<MenuGroup>() {
			@Override
			public void invoke(MenuGroup old, MenuGroup e) {
				old.setName(e.getName());
			}
		});
	}

	@Override
	public void delete(MenuGroup mg) throws SharedException {
		menuGroupService.delete(mg);
	}

	@Override
	public void delete(Menu menu) throws SharedException {
		menuService.delete(menu);
	}

	@Override
	public List<MenuNode> move(MenuNode p, List<MenuNode> items, int index) {
		return menuService.move(p, items, index);
	}

}
