
package com.voole.parrot.gwt.common.shared.rpcservice;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.data.shared.loader.ListLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.voole.parrot.shared.entity.authority.Authority;
import com.voole.parrot.shared.entity.authority.AuthorityEntrance;
import com.voole.parrot.shared.grid.GwtListLoadConfigBean;
import com.voole.parrot.shared.grid.GwtPagingLoadConfigBean;
import com.voole.parrot.shared.grid.QueryCondition;

public interface AuthorityRpcServiceAsync {


    public void getEntrances(AsyncCallback<List<AuthorityEntrance>> callback);

    public void getDependencies(Authority arg0, AsyncCallback<Set<Authority>> callback);

    public void setDependencies(Authority arg0, List<Authority> arg1, AsyncCallback<Void> callback);

    public void paging(GwtPagingLoadConfigBean<QueryCondition> arg0, AsyncCallback<PagingLoadResult<Authority>> callback);

    public void persist(Collection<Authority> arg0, AsyncCallback<Collection<Authority>> callback);

    public void persist(Authority arg0, AsyncCallback<Authority> callback);

    public void get(AsyncCallback<List<Authority>> callback);

    public void get(Authority arg0, AsyncCallback<Authority> callback);

    public void list(GwtListLoadConfigBean<QueryCondition> arg0, AsyncCallback<ListLoadResult<Authority>> callback);

    public void delete(Collection<Authority> arg0, AsyncCallback<Void> callback);

    public void delete(Authority arg0, AsyncCallback<Void> callback);

}
