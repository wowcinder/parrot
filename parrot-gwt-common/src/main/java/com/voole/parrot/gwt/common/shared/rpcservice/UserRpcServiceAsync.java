
package com.voole.parrot.gwt.common.shared.rpcservice;

import java.util.Set;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.data.shared.loader.ListLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.voole.parrot.shared.entity.authority.Authority;
import com.voole.parrot.shared.entity.authority.Role;
import com.voole.parrot.shared.entity.user.User;
import com.voole.parrot.shared.grid.GwtListLoadConfigBean;
import com.voole.parrot.shared.grid.GwtPagingLoadConfigBean;

public interface UserRpcServiceAsync {


    public void paging(GwtPagingLoadConfigBean<?> arg0, AsyncCallback<PagingLoadResult<User>> callback);

    public void persist(User arg0, AsyncCallback<User> callback);

    public void changePassword(User arg0, AsyncCallback<Void> callback);

    public void getUserRoles(User arg0, AsyncCallback<Set<Role>> callback);

    public void modifyUserRoles(User arg0, AsyncCallback<Void> callback);

    public void getUserAuthorities(User arg0, AsyncCallback<Set<Authority>> callback);

    public void modifyUserAuthorities(User arg0, AsyncCallback<Void> callback);

    public void list(GwtListLoadConfigBean<?> arg0, AsyncCallback<ListLoadResult<User>> callback);

    public void delete(User arg0, AsyncCallback<Void> callback);

}
