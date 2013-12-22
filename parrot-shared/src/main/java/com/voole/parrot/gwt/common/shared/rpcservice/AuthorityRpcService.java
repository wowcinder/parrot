/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.parrot.gwt.common.shared.rpcservice;

import java.util.List;
import java.util.Set;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.voole.parrot.shared.entity.authority.Authority;
import com.voole.parrot.shared.entity.authority.AuthorityEntrance;
import com.voole.parrot.shared.exception.SharedException;

/**
 * @author XuehuiHe
 * @date 2013年12月20日
 */
@RemoteServiceRelativePath("rpc/authority.rpc")
public interface AuthorityRpcService extends EntityRpcService<Authority>,
		RemoteService {
	List<AuthorityEntrance> getEntrances() throws SharedException;

	Set<Authority> getDependencies(Authority authority) throws SharedException;

	void setDependencies(Authority authority, List<Authority> dependencies)
			throws SharedException;
}
