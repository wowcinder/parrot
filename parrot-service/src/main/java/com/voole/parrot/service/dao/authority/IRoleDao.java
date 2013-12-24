package com.voole.parrot.service.dao.authority;

import com.voole.parrot.service.dao.IEntityDao;
import com.voole.parrot.shared.entity.authority.Role;

public interface IRoleDao extends IEntityDao<Role> {

	Role changeRoleName(Role role);

	void modifyRoleUsers(Role role);

	void modifyRoleAuthorities(Role role);

}
