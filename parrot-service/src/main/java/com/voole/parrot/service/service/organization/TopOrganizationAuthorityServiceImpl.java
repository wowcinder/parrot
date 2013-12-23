
package com.voole.parrot.service.service.organization;

import com.voole.parrot.service.dao.organization.ITopOrganizationAuthorityDao;
import com.voole.parrot.service.service.EntityServiceImpl;
import com.voole.parrot.shared.entity.organization.TopOrganizationAuthority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TopOrganizationAuthorityServiceImpl
    extends EntityServiceImpl<TopOrganizationAuthority>
    implements TopOrganizationAuthorityService
{

    @Autowired
    private ITopOrganizationAuthorityDao TopOrganizationAuthorityDao;

    public ITopOrganizationAuthorityDao getEntityDao() {
        return TopOrganizationAuthorityDao;
    }

}
