
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

public interface RoleRpcServiceAsync {


    public void paging(GwtPagingLoadConfigBean<?> arg0, AsyncCallback<PagingLoadResult<Role>> callback);

    public void createRole(Role arg0, AsyncCallback<Role> callback);

    public void changeRoleName(Role arg0, AsyncCallback<Role> callback);

    public void deleteRole(Role arg0, AsyncCallback<Void> callback);

    public void getRoleUsers(Role arg0, AsyncCallback<Set<User>> callback);

    public void modifyRoleUsers(Role arg0, AsyncCallback<Void> callback);

    public void getRoleAuthorities(Role arg0, AsyncCallback<Set<Authority>> callback);

    public void modifyRoleAuthorities(Role arg0, AsyncCallback<Void> callback);

    public void list(GwtListLoadConfigBean<?> arg0, AsyncCallback<ListLoadResult<Role>> callback);

}
