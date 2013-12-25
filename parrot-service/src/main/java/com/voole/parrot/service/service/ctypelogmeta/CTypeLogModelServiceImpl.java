
package com.voole.parrot.service.service.ctypelogmeta;

import com.voole.parrot.service.dao.ctypelogmeta.ICTypeLogModelDao;
import com.voole.parrot.service.service.EntityServiceImpl;
import com.voole.parrot.shared.entity.ctypelogmeta.CTypeLogModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CTypeLogModelServiceImpl
    extends EntityServiceImpl<CTypeLogModel>
    implements CTypeLogModelService
{

    @Autowired
    private ICTypeLogModelDao CTypeLogModelDao;

    public ICTypeLogModelDao getEntityDao() {
        return CTypeLogModelDao;
    }

}
