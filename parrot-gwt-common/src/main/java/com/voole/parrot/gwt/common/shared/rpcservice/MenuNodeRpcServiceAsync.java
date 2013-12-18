
package com.voole.parrot.gwt.common.shared.rpcservice;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.voole.parrot.shared.entity.menu.Menu;

public interface MenuNodeRpcServiceAsync {


    public void save(Menu arg0, AsyncCallback<Menu> callback);

}
