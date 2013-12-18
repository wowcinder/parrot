package com.voole.parrot.rpc.service.rpc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.voole.parrot.gwt.common.shared.rpcservice.MenuNodeRpcService;
import com.voole.parrot.service.dao.menu.IMenuNodeDao;
import com.voole.parrot.shared.entity.menu.Menu;

@Service
public class MenuRpcServiceImpl implements MenuNodeRpcService {
	@Autowired
	private IMenuNodeDao dao;

	@Override
	@Transactional
	public Menu save(Menu menu) {
		return (Menu) dao.persist(menu);
	}

}
