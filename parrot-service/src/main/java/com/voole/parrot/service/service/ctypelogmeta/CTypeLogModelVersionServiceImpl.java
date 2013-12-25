
package com.voole.parrot.service.service.ctypelogmeta;

import com.voole.parrot.service.dao.ctypelogmeta.ICTypeLogModelVersionDao;
import com.voole.parrot.service.service.EntityServiceImpl;
import com.voole.parrot.shared.entity.ctypelogmeta.CTypeLogModelVersion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CTypeLogModelVersionServiceImpl
    extends EntityServiceImpl<CTypeLogModelVersion>
    implements CTypeLogModelVersionService
{

    @Autowired
    private ICTypeLogModelVersionDao CTypeLogModelVersionDao;

    public ICTypeLogModelVersionDao getEntityDao() {
        return CTypeLogModelVersionDao;
    }

}
