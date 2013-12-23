
package com.voole.parrot.service.service.authority;

import com.voole.parrot.service.dao.authority.IRoleDao;
import com.voole.parrot.service.service.EntityServiceImpl;
import com.voole.parrot.shared.entity.authority.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RoleServiceImpl
    extends EntityServiceImpl<Role>
    implements RoleService
{

    @Autowired
    private IRoleDao RoleDao;

    public IRoleDao getEntityDao() {
        return RoleDao;
    }

}
