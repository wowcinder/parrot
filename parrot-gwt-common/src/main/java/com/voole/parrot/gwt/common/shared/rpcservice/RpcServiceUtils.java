
package com.voole.parrot.gwt.common.shared.rpcservice;

import com.google.gwt.core.client.GWT;

public class RpcServiceUtils {

    public final static MenuNodeRpcServiceAsync MenuNodeRpcService = GWT.create(com.voole.parrot.gwt.common.shared.rpcservice.MenuNodeRpcService.class);
    public final static OpenAuthorizeRpcServiceAsync OpenAuthorizeRpcService = GWT.create(com.voole.parrot.gwt.common.shared.rpcservice.OpenAuthorizeRpcService.class);

}
