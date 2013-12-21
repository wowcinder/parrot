package com.voole.parrot.rpc.service.rpc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.voole.parrot.gwt.common.shared.rpcservice.AuthorityRpcService;
import com.voole.parrot.service.dao.authority.IAuthorityDao;
import com.voole.parrot.shared.entity.authority.Authority;

@Service
public class AuthorityRpcServiceImpl extends EntityRpcServiceImpl<Authority>
		implements AuthorityRpcService {
	@Autowired
	private IAuthorityDao authorityDao;

	@Override
	@Transactional
	public Authority persist(Authority e) {
		return authorityDao.persist(e);
	}

}
