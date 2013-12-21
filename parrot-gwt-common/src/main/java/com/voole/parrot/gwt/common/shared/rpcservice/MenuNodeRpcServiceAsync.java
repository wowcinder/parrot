
package com.voole.parrot.gwt.common.shared.rpcservice;

import java.util.List;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.data.shared.loader.ListLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.voole.parrot.shared.entity.menu.Menu;
import com.voole.parrot.shared.entity.menu.MenuGroup;
import com.voole.parrot.shared.grid.GwtListLoadConfigBean;
import com.voole.parrot.shared.grid.GwtPagingLoadConfigBean;

public interface MenuNodeRpcServiceAsync {


    public void paging(GwtPagingLoadConfigBean<?> arg0, AsyncCallback<PagingLoadResult<Menu>> callback);

    public void get(AsyncCallback<List<Menu>> callback);

    public void list(GwtListLoadConfigBean<?> arg0, AsyncCallback<ListLoadResult<Menu>> callback);

    public void create(Menu arg0, AsyncCallback<Menu> callback);

    public void create(MenuGroup arg0, AsyncCallback<MenuGroup> callback);

    public void update(Menu arg0, AsyncCallback<Menu> callback);

    public void update(MenuGroup arg0, AsyncCallback<MenuGroup> callback);

}
