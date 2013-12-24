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

@RemoteServiceRelativePath("rpc/user.rpc")
public interface UserRpcService extends RemoteService {
	public User persist(User e) throws SharedException;

	public PagingLoadResult<User> paging(GwtPagingLoadConfigBean<?> condition)
			throws SharedException;

	public ListLoadResult<User> list(GwtListLoadConfigBean<?> configBean)
			throws SharedException;

	public void changePassword(User user) throws SharedException;

	public void delete(User e) throws SharedException;

	public Set<Role> getUserRoles(User user) throws SharedException;

	public void modifyUserRoles(User user) throws SharedException;

	public Set<Authority> getUserAuthorities(User user) throws SharedException;

	public void modifyUserAuthorities(User user) throws SharedException;
}
