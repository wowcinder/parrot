package com.voole.parrot.rpc.service.rpc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sencha.gxt.data.shared.loader.ListLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.voole.parrot.gwt.common.shared.rpcservice.AuthorityRpcService;
import com.voole.parrot.service.dao.authority.IAuthorityDao;
import com.voole.parrot.shared.entity.authority.Authority;
import com.voole.parrot.shared.exception.SharedException;
import com.voole.parrot.shared.grid.GwtListLoadConfigBean;
import com.voole.parrot.shared.grid.GwtPagingLoadConfigBean;

@Service
public class AuthorityRpcServiceImpl implements AuthorityRpcService {
	@Autowired
	private IAuthorityDao authorityDao;

	@Override
	public Authority save(Authority t) throws SharedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Authority update(Authority t) throws SharedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Authority t) throws SharedException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Authority> save(List<Authority> list) throws SharedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Authority> update(List<Authority> list) throws SharedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(List<Authority> list) throws SharedException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Authority> get() throws SharedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListLoadResult<Authority> list(GwtListLoadConfigBean<?> condition) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public PagingLoadResult<Authority> paging(
			GwtPagingLoadConfigBean<?> condition) {
		return authorityDao.paging(condition);
	}

}
