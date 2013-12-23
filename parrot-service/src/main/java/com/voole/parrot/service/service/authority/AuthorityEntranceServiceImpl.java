
package com.voole.parrot.service.service.authority;

import com.voole.parrot.service.dao.authority.IAuthorityEntranceDao;
import com.voole.parrot.service.service.EntityServiceImpl;
import com.voole.parrot.shared.entity.authority.AuthorityEntrance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AuthorityEntranceServiceImpl
    extends EntityServiceImpl<AuthorityEntrance>
    implements AuthorityEntranceService
{

    @Autowired
    private IAuthorityEntranceDao AuthorityEntranceDao;

    public IAuthorityEntranceDao getEntityDao() {
        return AuthorityEntranceDao;
    }

}
