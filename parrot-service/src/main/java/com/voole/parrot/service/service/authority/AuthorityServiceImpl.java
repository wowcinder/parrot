
package com.voole.parrot.service.service.authority;

import com.voole.parrot.service.dao.authority.IAuthorityDao;
import com.voole.parrot.service.service.EntityServiceImpl;
import com.voole.parrot.shared.entity.authority.Authority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AuthorityServiceImpl
    extends EntityServiceImpl<Authority>
    implements AuthorityService
{

    @Autowired
    private IAuthorityDao AuthorityDao;

    public IAuthorityDao getEntityDao() {
        return AuthorityDao;
    }

}
