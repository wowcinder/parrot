
package com.voole.parrot.gwt.common.shared.rpcservice;

import java.util.List;
import java.util.Set;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.voole.parrot.shared.condition.QueryCondition;
import com.voole.parrot.shared.entity.authority.Authority;
import com.voole.parrot.shared.entity.authority.AuthorityEntrance;
import com.voole.parrot.shared.entity.authority.Role;
import com.voole.parrot.shared.entity.user.User;
import com.voole.parrot.shared.grid.GwtPagingLoadConfigBean;

public interface AuthorityRpcServiceAsync {


    public void getEntrances(AsyncCallback<List<AuthorityEntrance>> callback);

    public void getDependencies(Authority arg0, AsyncCallback<Set<Authority>> callback);

    public void setDependencies(Authority arg0, List<Authority> arg1, AsyncCallback<Void> callback);

    public void paging(GwtPagingLoadConfigBean<QueryCondition> arg0, AsyncCallback<PagingLoadResult<Authority>> callback);

    public void getAuthorityRoles(Authority arg0, AsyncCallback<Set<Role>> callback);

    public void modifyAuthorityRoles(Authority arg0, AsyncCallback<Void> callback);

    public void getAuthorityUsers(Authority arg0, AsyncCallback<Set<User>> callback);

    public void modifyAuthorityUsers(Authority arg0, AsyncCallback<Void> callback);

}
