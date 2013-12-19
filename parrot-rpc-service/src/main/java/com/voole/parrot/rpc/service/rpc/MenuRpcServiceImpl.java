package com.voole.parrot.rpc.service.rpc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sencha.gxt.data.shared.loader.ListLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.voole.parrot.gwt.common.shared.rpcservice.MenuNodeRpcService;
import com.voole.parrot.service.dao.menu.IMenuDao;
import com.voole.parrot.service.dao.menu.IMenuGroupDao;
import com.voole.parrot.shared.entity.menu.Menu;
import com.voole.parrot.shared.entity.menu.MenuGroup;
import com.voole.parrot.shared.exception.SharedException;
import com.voole.parrot.shared.grid.GwtListLoadConfigBean;
import com.voole.parrot.shared.grid.GwtPagingLoadConfigBean;

@Service
public class MenuRpcServiceImpl implements MenuNodeRpcService {
	@Autowired
	private IMenuGroupDao menuGroupDao;
	@Autowired
	private IMenuDao menuDao;

	@Override
	@Transactional(readOnly = true)
	public List<Menu> get() throws SharedException {
		return menuDao.get();
	}

	@Override
	@Transactional(readOnly = true)
	public ListLoadResult<Menu> list(GwtListLoadConfigBean<?> condition) {
		return menuDao.list(condition);
	}

	@Override
	@Transactional(readOnly = true)
	public PagingLoadResult<Menu> paging(GwtPagingLoadConfigBean<?> condition) {
		return menuDao.paging(condition);
	}

	@Override
	@Transactional
	public Menu save(Menu menu) throws SharedException {
		return menuDao.save(menu);
	}

	@Override
	@Transactional
	public MenuGroup save(MenuGroup menu) throws SharedException {
		return menuGroupDao.save(menu);
	}

}
