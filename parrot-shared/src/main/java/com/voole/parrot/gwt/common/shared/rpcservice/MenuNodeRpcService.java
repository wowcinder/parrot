/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.parrot.gwt.common.shared.rpcservice;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.sencha.gxt.data.shared.loader.ListLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.voole.parrot.shared.entity.menu.Menu;
import com.voole.parrot.shared.entity.menu.MenuGroup;
import com.voole.parrot.shared.exception.SharedException;
import com.voole.parrot.shared.grid.GwtListLoadConfigBean;
import com.voole.parrot.shared.grid.GwtPagingLoadConfigBean;

/**
 * @author XuehuiHe
 * @date 2013年9月9日
 */
@RemoteServiceRelativePath("rpc/menu.rpc")
public interface MenuNodeRpcService extends RemoteService {

	Menu save(Menu menu) throws SharedException;

	MenuGroup save(MenuGroup menu) throws SharedException;

	List<Menu> get() throws SharedException;

	public ListLoadResult<Menu> list(GwtListLoadConfigBean<?> condition);

	public PagingLoadResult<Menu> paging(GwtPagingLoadConfigBean<?> condition);
}
