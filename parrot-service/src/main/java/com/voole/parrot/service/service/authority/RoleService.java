package com.voole.parrot.service.service.authority;

import com.voole.parrot.service.service.EntityService;
import com.voole.parrot.shared.entity.authority.Role;

public interface RoleService extends EntityService<Role> {

	Role changeRoleName(Role role);

	void modifyRoleUsers(Role role);

	void modifyRoleAuthorities(Role role);

}
