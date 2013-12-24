package com.voole.parrot.rpc.service.rpc;

import java.util.Set;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sencha.gxt.data.shared.loader.ListLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.voole.parrot.gwt.common.shared.rpcservice.UserRpcService;
import com.voole.parrot.service.service.user.UserService;
import com.voole.parrot.shared.entity.authority.Authority;
import com.voole.parrot.shared.entity.authority.Role;
import com.voole.parrot.shared.entity.user.User;
import com.voole.parrot.shared.exception.SharedException;
import com.voole.parrot.shared.grid.GwtListLoadConfigBean;
import com.voole.parrot.shared.grid.GwtPagingLoadConfigBean;

@Service
public class UserRpcServiceImpl implements UserRpcService {
	@Autowired
	private UserService userService;

	@Override
	public User persist(User e) throws SharedException {
		return userService.persist(e);
	}

	@Override
	public PagingLoadResult<User> paging(GwtPagingLoadConfigBean<?> condition)
			throws SharedException {
		return userService.paging(condition);
	}

	@Override
	public void changePassword(User user) throws SharedException {
		userService.changePassword(user);
	}

	@Override
	public void delete(User e) throws SharedException {
		userService.delete(e);
	}

	@Override
	@Transactional
	public Set<Role> getUserRoles(User user) throws SharedException {
		user = userService.getEntityDao().refresh(user);
		Hibernate.initialize(user.getRoles());
		return user.getRoles();
	}

	@Override
	public void modifyUserRoles(User user) throws SharedException {
		userService.modifyUserRoles(user);
	}

	@Override
	public ListLoadResult<User> list(GwtListLoadConfigBean<?> configBean)
			throws SharedException {
		return userService.list(configBean);
	}

	@Override
	@Transactional
	public Set<Authority> getUserAuthorities(User user) throws SharedException {
		user = userService.getEntityDao().refresh(user);
		Hibernate.initialize(user.getAuthorities());
		return user.getAuthorities();
	}

	@Override
	public void modifyUserAuthorities(User user) throws SharedException {
		userService.modifyUserAuthorities(user);
	}

}
