/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.parrot.gwt.common.shared.rpcservice;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.voole.parrot.shared.entity.menu.Menu;
import com.voole.parrot.shared.exception.SharedException;

/**
 * @author XuehuiHe
 * @date 2013年9月9日
 */
@RemoteServiceRelativePath("rpc/menu.rpc")
public interface MenuNodeRpcService extends RemoteService {

	Menu save(Menu menu) throws SharedException;
}
