package com.voole.parrot.service.dao.authority;

import org.springframework.stereotype.Repository;

import com.voole.parrot.service.dao.EntityDao;
import com.voole.parrot.service.dao.EntityUpdater;
import com.voole.parrot.shared.entity.authority.Role;

@Repository
public class RoleDao extends EntityDao<Role> implements IRoleDao {

	@Override
	public Role changeRoleName(Role role) {
		return update(role, new EntityUpdater<Role>() {

			@Override
			public void invoke(Role old, Role e) {
				old.setName(e.getName());
			}
		});
	}

	@Override
	public void modifyRoleUsers(Role role) {
		update(role, new EntityUpdater<Role>() {
			@Override
			public void invoke(Role old, Role e) {
				old.setUsers(e.getUsers());
			}
		});
	}

	@Override
	public void modifyRoleAuthorities(Role role) {
		update(role, new EntityUpdater<Role>() {
			@Override
			public void invoke(Role old, Role e) {
				old.setAuthorities(e.getAuthorities());
			}
		});
	}

}
