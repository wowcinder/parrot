
package com.voole.parrot.service.service.ctypelogmeta;

import com.voole.parrot.service.dao.ctypelogmeta.ICtypeLogModelVersionDao;
import com.voole.parrot.service.service.EntityServiceImpl;
import com.voole.parrot.shared.entity.ctypelogmeta.CtypeLogModelVersion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CtypeLogModelVersionServiceImpl
    extends EntityServiceImpl<CtypeLogModelVersion>
    implements CtypeLogModelVersionService
{

    @Autowired
    private ICtypeLogModelVersionDao CtypeLogModelVersionDao;

    public ICtypeLogModelVersionDao getEntityDao() {
        return CtypeLogModelVersionDao;
    }

}
