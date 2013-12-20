
package com.voole.parrot.gwt.common.shared.rpcservice;

import java.util.List;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.data.shared.loader.ListLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.voole.parrot.shared.entity.authority.Authority;
import com.voole.parrot.shared.grid.GwtListLoadConfigBean;
import com.voole.parrot.shared.grid.GwtPagingLoadConfigBean;

public interface AuthorityRpcServiceAsync {


    public void paging(GwtPagingLoadConfigBean<?> arg0, AsyncCallback<PagingLoadResult<Authority>> callback);

    public void get(AsyncCallback<List<Authority>> callback);

    public void list(GwtListLoadConfigBean<?> arg0, AsyncCallback<ListLoadResult<Authority>> callback);

    public void save(List<Authority> arg0, AsyncCallback<List<Authority>> callback);

    public void save(Authority arg0, AsyncCallback<Authority> callback);

    public void delete(Authority arg0, AsyncCallback<Void> callback);

    public void delete(List<Authority> arg0, AsyncCallback<Void> callback);

    public void update(List<Authority> arg0, AsyncCallback<List<Authority>> callback);

    public void update(Authority arg0, AsyncCallback<Authority> callback);

}
