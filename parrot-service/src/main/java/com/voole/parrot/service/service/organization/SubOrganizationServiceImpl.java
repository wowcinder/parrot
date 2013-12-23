
package com.voole.parrot.service.service.organization;

import com.voole.parrot.service.dao.organization.ISubOrganizationDao;
import com.voole.parrot.service.service.EntityServiceImpl;
import com.voole.parrot.shared.entity.organization.SubOrganization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SubOrganizationServiceImpl
    extends EntityServiceImpl<SubOrganization>
    implements SubOrganizationService
{

    @Autowired
    private ISubOrganizationDao SubOrganizationDao;

    public ISubOrganizationDao getEntityDao() {
        return SubOrganizationDao;
    }

}
