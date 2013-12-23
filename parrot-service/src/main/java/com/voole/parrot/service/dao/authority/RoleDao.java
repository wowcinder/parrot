
package com.voole.parrot.service.dao.authority;

import com.voole.parrot.service.dao.EntityDao;
import com.voole.parrot.shared.entity.authority.Role;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDao
    extends EntityDao<Role>
    implements IRoleDao
{


}
