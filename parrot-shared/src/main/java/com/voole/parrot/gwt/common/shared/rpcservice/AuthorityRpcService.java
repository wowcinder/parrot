/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.parrot.gwt.common.shared.rpcservice;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.voole.parrot.shared.entity.authority.Authority;

/**
 * @author XuehuiHe
 * @date 2013年12月20日
 */
@RemoteServiceRelativePath("rpc/authority.rpc")
public interface AuthorityRpcService extends NormalRpcService<Authority>,
		RemoteService {

}
