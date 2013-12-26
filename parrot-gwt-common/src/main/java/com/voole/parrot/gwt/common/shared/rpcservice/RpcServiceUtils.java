
package com.voole.parrot.gwt.common.shared.rpcservice;

import com.google.gwt.core.client.GWT;

public class RpcServiceUtils {

    public final static AuthorityRpcServiceAsync AuthorityRpcService = GWT.create(com.voole.parrot.gwt.common.shared.rpcservice.AuthorityRpcService.class);
    public final static CtypeLogModelRpcServiceAsync CtypeLogModelRpcService = GWT.create(com.voole.parrot.gwt.common.shared.rpcservice.CtypeLogModelRpcService.class);
    public final static HbaseTableMetaRpcServiceAsync HbaseTableMetaRpcService = GWT.create(com.voole.parrot.gwt.common.shared.rpcservice.HbaseTableMetaRpcService.class);
    public final static MenuNodeRpcServiceAsync MenuNodeRpcService = GWT.create(com.voole.parrot.gwt.common.shared.rpcservice.MenuNodeRpcService.class);
    public final static OpenAuthorizeRpcServiceAsync OpenAuthorizeRpcService = GWT.create(com.voole.parrot.gwt.common.shared.rpcservice.OpenAuthorizeRpcService.class);
    public final static RoleRpcServiceAsync RoleRpcService = GWT.create(com.voole.parrot.gwt.common.shared.rpcservice.RoleRpcService.class);
    public final static UserRpcServiceAsync UserRpcService = GWT.create(com.voole.parrot.gwt.common.shared.rpcservice.UserRpcService.class);

}
