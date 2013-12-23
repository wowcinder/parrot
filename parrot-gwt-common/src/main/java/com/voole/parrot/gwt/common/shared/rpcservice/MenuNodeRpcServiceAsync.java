
package com.voole.parrot.gwt.common.shared.rpcservice;

import java.util.List;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.voole.parrot.shared.entity.menu.Menu;
import com.voole.parrot.shared.entity.menu.MenuGroup;
import com.voole.parrot.shared.entity.menu.MenuNode;

public interface MenuNodeRpcServiceAsync {


    public void persist(Menu arg0, AsyncCallback<Menu> callback);

    public void persist(MenuGroup arg0, AsyncCallback<MenuGroup> callback);

    public void delete(MenuGroup arg0, AsyncCallback<Void> callback);

    public void delete(Menu arg0, AsyncCallback<Void> callback);

    public void update(Menu arg0, AsyncCallback<Menu> callback);

    public void update(MenuGroup arg0, AsyncCallback<MenuGroup> callback);

    public void move(MenuNode arg0, List<MenuNode> arg1, int arg2, AsyncCallback<List<MenuNode>> callback);

}
