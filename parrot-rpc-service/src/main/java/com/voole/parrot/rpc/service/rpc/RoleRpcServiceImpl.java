package com.voole.parrot.rpc.service.rpc;

import java.util.Set;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sencha.gxt.data.shared.loader.ListLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.voole.parrot.gwt.common.shared.rpcservice.RoleRpcService;
import com.voole.parrot.service.service.authority.RoleService;
import com.voole.parrot.shared.entity.authority.Authority;
import com.voole.parrot.shared.entity.authority.Role;
import com.voole.parrot.shared.entity.user.User;
import com.voole.parrot.shared.exception.SharedException;
import com.voole.parrot.shared.grid.GwtListLoadConfigBean;
import com.voole.parrot.shared.grid.GwtPagingLoadConfigBean;

@Service
public class RoleRpcServiceImpl implements RoleRpcService {
	@Autowired
	private RoleService roleService;

	@Override
	public Role createRole(Role role) throws SharedException {
		return roleService.persist(role);
	}

	@Override
	public Role changeRoleName(Role role) throws SharedException {
		return roleService.changeRoleName(role);
	}

	@Override
	public PagingLoadResult<Role> paging(GwtPagingLoadConfigBean<?> condition)
			throws SharedException {
		return roleService.paging(condition);
	}

	@Override
	public void deleteRole(Role role) throws SharedException {
		roleService.delete(role);
	}

	@Override
	public ListLoadResult<Role> list(GwtListLoadConfigBean<?> configBean)
			throws SharedException {
		return roleService.list(configBean);
	}

	@Override
	@Transactional
	public Set<User> getRoleUsers(Role role) throws SharedException {
		role = roleService.getEntityDao().refresh(role);
		Hibernate.initialize(role.getUsers());
		return role.getUsers();
	}

	@Override
	public void modifyRoleUsers(Role role) throws SharedException {
		roleService.modifyRoleUsers(role);
	}

	@Override
	@Transactional
	public Set<Authority> getRoleAuthorities(Role role) throws SharedException {
		role = roleService.getEntityDao().refresh(role);
		Hibernate.initialize(role.getAuthorities());
		return role.getAuthorities();
	}

	@Override
	public void modifyRoleAuthorities(Role role) throws SharedException {
		roleService.modifyRoleAuthorities(role);
	}

}
