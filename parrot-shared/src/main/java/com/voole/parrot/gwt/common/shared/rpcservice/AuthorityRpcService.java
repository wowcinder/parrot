/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.parrot.gwt.common.shared.rpcservice;

import java.util.List;
import java.util.Set;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.voole.parrot.shared.condition.QueryCondition;
import com.voole.parrot.shared.entity.authority.Authority;
import com.voole.parrot.shared.entity.authority.AuthorityEntrance;
import com.voole.parrot.shared.exception.SharedException;
import com.voole.parrot.shared.grid.GwtPagingLoadConfigBean;

/**
 * @author XuehuiHe
 * @date 2013年12月20日
 */
@RemoteServiceRelativePath("rpc/authority.rpc")
public interface AuthorityRpcService extends RemoteService {
	List<AuthorityEntrance> getEntrances() throws SharedException;

	Set<Authority> getDependencies(Authority authority) throws SharedException;

	void setDependencies(Authority authority, List<Authority> dependencies)
			throws SharedException;

	public <Condition extends QueryCondition> PagingLoadResult<Authority> paging(
			GwtPagingLoadConfigBean<Condition> configBean)
			throws SharedException;
}
