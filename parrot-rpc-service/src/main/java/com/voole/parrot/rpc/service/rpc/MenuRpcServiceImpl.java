package com.voole.parrot.rpc.service.rpc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sencha.gxt.data.shared.loader.ListLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.voole.parrot.gwt.common.shared.rpcservice.MenuNodeRpcService;
import com.voole.parrot.service.dao.ISimpleDao;
import com.voole.parrot.service.dao.menu.IMenuDao;
import com.voole.parrot.service.dao.menu.IMenuGroupDao;
import com.voole.parrot.shared.entity.menu.Menu;
import com.voole.parrot.shared.entity.menu.MenuGroup;
import com.voole.parrot.shared.entity.menu.MenuNode;
import com.voole.parrot.shared.exception.SharedException;
import com.voole.parrot.shared.grid.GwtListLoadConfigBean;
import com.voole.parrot.shared.grid.GwtPagingLoadConfigBean;

@Service
@Transactional
public class MenuRpcServiceImpl implements MenuNodeRpcService {
	@Autowired
	private IMenuGroupDao menuGroupDao;
	@Autowired
	private IMenuDao menuDao;
	@Autowired
	private ISimpleDao simpleDao;

	@Override
	public List<Menu> get() throws SharedException {
		return menuDao.list();
	}

	@Override
	public ListLoadResult<Menu> list(GwtListLoadConfigBean<?> condition) {
		return menuDao.list(condition);
	}

	@Override
	public PagingLoadResult<Menu> paging(GwtPagingLoadConfigBean<?> condition) {
		return menuDao.paging(condition);
	}

	@Override
	public Menu create(Menu menu) throws SharedException {
		return menuDao.create(menu);
	}

	@Override
	public MenuGroup create(MenuGroup menu) throws SharedException {
		return menuGroupDao.create(menu);
	}

	@Override
	public Menu update(Menu menu) throws SharedException {
		return menuDao.update(menu);
	}

	@Override
	public MenuGroup update(MenuGroup menu) throws SharedException {
		return menuGroupDao.update(menu);
	}

	@Override
	public void delete(Menu menu) throws SharedException {
		simpleDao.<Menu> delete(menu);
	}

	@Override
	public void delete(MenuGroup menu) throws SharedException {
		simpleDao.<MenuGroup> delete(menu);
	}

	@Override
	public List<MenuNode> move(MenuNode p, List<MenuNode> items, int index) {
		return menuDao.move(p, items, index);
	}

}
