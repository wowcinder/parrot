package com.voole.parrot.gwt.common.shared.rpcservice;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.voole.parrot.shared.entity.menu.MenuGroup;
import com.voole.parrot.shared.exception.SharedException;

@RemoteServiceRelativePath("rpc/open_authorize.rpc")
public interface OpenAuthorizeRpcService extends RemoteService {
	void logout();

	MenuGroup getUserMenus() throws SharedException;

	Boolean login(String email, String password);

	Boolean isLogin();
}
