
package com.voole.parrot.service.service.ctypelogmeta;

import com.voole.parrot.service.dao.ctypelogmeta.ICTypeLogModelColumnDao;
import com.voole.parrot.service.service.EntityServiceImpl;
import com.voole.parrot.shared.entity.ctypelogmeta.CTypeLogModelColumn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CTypeLogModelColumnServiceImpl
    extends EntityServiceImpl<CTypeLogModelColumn>
    implements CTypeLogModelColumnService
{

    @Autowired
    private ICTypeLogModelColumnDao CTypeLogModelColumnDao;

    public ICTypeLogModelColumnDao getEntityDao() {
        return CTypeLogModelColumnDao;
    }

}
