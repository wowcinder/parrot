/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.parrot.gwt.common.shared.rpcservice;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.voole.parrot.shared.entity.menu.Menu;
import com.voole.parrot.shared.entity.menu.MenuGroup;
import com.voole.parrot.shared.entity.menu.MenuNode;
import com.voole.parrot.shared.exception.SharedException;

/**
 * @author XuehuiHe
 * @date 2013年9月9日
 */
@RemoteServiceRelativePath("rpc/menu.rpc")
public interface MenuNodeRpcService extends RemoteService {
	Menu persist(Menu menu) throws SharedException;

	Menu update(Menu menu) throws SharedException;

	MenuGroup persist(MenuGroup mg) throws SharedException;

	MenuGroup update(MenuGroup mg)
			throws SharedException;

	void delete(MenuGroup mg) throws SharedException;

	void delete(Menu menu) throws SharedException;

	public List<MenuNode> move(MenuNode p, List<MenuNode> items, int index);

}
