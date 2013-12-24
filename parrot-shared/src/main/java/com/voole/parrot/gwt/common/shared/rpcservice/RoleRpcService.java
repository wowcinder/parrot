package com.voole.parrot.gwt.common.shared.rpcservice;

import java.util.Set;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.sencha.gxt.data.shared.loader.ListLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.voole.parrot.shared.entity.authority.Authority;
import com.voole.parrot.shared.entity.authority.Role;
import com.voole.parrot.shared.entity.user.User;
import com.voole.parrot.shared.exception.SharedException;
import com.voole.parrot.shared.grid.GwtListLoadConfigBean;
import com.voole.parrot.shared.grid.GwtPagingLoadConfigBean;

@RemoteServiceRelativePath("rpc/role.rpc")
public interface RoleRpcService extends RemoteService {
	Role createRole(Role role) throws SharedException;

	Role changeRoleName(Role role) throws SharedException;

	public PagingLoadResult<Role> paging(GwtPagingLoadConfigBean<?> condition)
			throws SharedException;

	public ListLoadResult<Role> list(GwtListLoadConfigBean<?> configBean)
			throws SharedException;

	public void deleteRole(Role role) throws SharedException;

	public Set<User> getRoleUsers(Role role) throws SharedException;

	public void modifyRoleUsers(Role role) throws SharedException;

	public Set<Authority> getRoleAuthorities(Role role) throws SharedException;

	public void modifyRoleAuthorities(Role role) throws SharedException;
}
