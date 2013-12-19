package com.voole.parrot.rpc.service.rpc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.voole.parrot.gwt.common.shared.rpcservice.OpenAuthorizeRpcService;
import com.voole.parrot.rpc.service.rpc.open.OpenRpcService;
import com.voole.parrot.service.service.AuthorizeService;
import com.voole.parrot.shared.entity.menu.MenuGroup;

@Service
public class OpenAuthorizeRpcServiceImpl implements OpenAuthorizeRpcService,
		OpenRpcService {
	@Autowired
	private AuthorizeService authorizeService;

	@Override
	public void logout() {
		authorizeService.logout();
	}

	@Override
	public MenuGroup getUserMenus() {
		return authorizeService.getMenuRoot();
	}

	@Override
	public Boolean login(String email, String password) {
		return authorizeService.login(email, password);
	}

	@Override
	public Boolean isLogin() {
		return authorizeService.isLogin();
	}

}
