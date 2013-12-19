
package com.voole.parrot.gwt.common.shared.rpcservice;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.voole.parrot.shared.entity.menu.MenuGroup;

public interface OpenAuthorizeRpcServiceAsync {


    public void logout(AsyncCallback<Void> callback);

    public void getUserMenus(AsyncCallback<MenuGroup> callback);

    public void login(String arg0, String arg1, AsyncCallback<Boolean> callback);

    public void isLogin(AsyncCallback<Boolean> callback);

}
