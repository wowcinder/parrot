package com.voole.parrot.service.service.authority;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.voole.parrot.service.dao.authority.IRoleDao;
import com.voole.parrot.service.service.EntityServiceImpl;
import com.voole.parrot.shared.entity.authority.Role;

@Service
@Transactional
public class RoleServiceImpl extends EntityServiceImpl<Role> implements
		RoleService {

	@Autowired
	private IRoleDao RoleDao;

	public IRoleDao getEntityDao() {
		return RoleDao;
	}

	@Override
	public Role changeRoleName(Role role) {
		return RoleDao.changeRoleName(role);
	}

	@Override
	public void modifyRoleUsers(Role role) {
		RoleDao.modifyRoleUsers(role);
	}

	@Override
	public void modifyRoleAuthorities(Role role) {
		RoleDao.modifyRoleAuthorities(role);
	}

}
