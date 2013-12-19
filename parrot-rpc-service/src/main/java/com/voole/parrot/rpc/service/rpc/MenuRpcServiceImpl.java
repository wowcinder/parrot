package com.voole.parrot.rpc.service.rpc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sencha.gxt.data.shared.loader.ListLoadResult;
import com.sencha.gxt.data.shared.loader.ListLoadResultBean;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoadResultBean;
import com.voole.parrot.gwt.common.shared.rpcservice.MenuNodeRpcService;
import com.voole.parrot.service.dao.menu.IMenuNodeDao;
import com.voole.parrot.shared.entity.menu.Menu;
import com.voole.parrot.shared.entity.menu.MenuNode;
import com.voole.parrot.shared.exception.SharedException;
import com.voole.parrot.shared.grid.GwtListLoadConfigBean;
import com.voole.parrot.shared.grid.GwtPagingLoadConfigBean;

@Service
public class MenuRpcServiceImpl implements MenuNodeRpcService {
	@Autowired
	private IMenuNodeDao dao;

	@Override
	@Transactional
	public MenuNode save(MenuNode menu) {
		return dao.persist(menu);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Menu> get() throws SharedException {
		List<MenuNode> nodes = dao.get();
		return getMenus(nodes);
	}

	private List<Menu> getMenus(List<MenuNode> nodes) {
		List<Menu> menus = new ArrayList<Menu>();
		for (MenuNode node : nodes) {
			if (node instanceof Menu) {
				menus.add((Menu) node);
			}
		}
		return menus;
	}

	private ListLoadResult<Menu> getMenus(ListLoadResult<MenuNode> nodes) {
		List<Menu> menus = getMenus(nodes.getData());
		ListLoadResultBean<Menu> resultBean = new ListLoadResultBean<Menu>();
		resultBean.setData(menus);
		return resultBean;
	}

	private PagingLoadResult<Menu> getMenus(PagingLoadResult<MenuNode> nodes) {
		List<Menu> menus = getMenus(nodes.getData());
		PagingLoadResultBean<Menu> resultBean = new PagingLoadResultBean<Menu>();
		resultBean.setData(menus);
		resultBean.setOffset(nodes.getOffset());
		resultBean.setTotalLength(nodes.getTotalLength());
		return resultBean;
	}

	@Override
	@Transactional(readOnly = true)
	public ListLoadResult<Menu> list(GwtListLoadConfigBean<?> condition) {
		return getMenus(dao.list(condition));
	}

	@Override
	@Transactional(readOnly = true)
	public PagingLoadResult<Menu> paging(GwtPagingLoadConfigBean<?> condition) {
		return getMenus(dao.paging(condition));
	}

}
