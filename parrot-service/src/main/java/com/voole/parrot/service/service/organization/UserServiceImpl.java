
package com.voole.parrot.service.service.organization;

import com.voole.parrot.service.dao.organization.IUserDao;
import com.voole.parrot.service.service.EntityServiceImpl;
import com.voole.parrot.shared.entity.organization.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl
    extends EntityServiceImpl<User>
    implements UserService
{

    @Autowired
    private IUserDao UserDao;

    public IUserDao getEntityDao() {
        return UserDao;
    }

}
