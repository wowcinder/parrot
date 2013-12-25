
package com.voole.parrot.service.service.ctypelogmeta;

import com.voole.parrot.service.dao.ctypelogmeta.ICTypeLogModelGroupColumnDao;
import com.voole.parrot.service.service.EntityServiceImpl;
import com.voole.parrot.shared.entity.ctypelogmeta.CTypeLogModelGroupColumn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CTypeLogModelGroupColumnServiceImpl
    extends EntityServiceImpl<CTypeLogModelGroupColumn>
    implements CTypeLogModelGroupColumnService
{

    @Autowired
    private ICTypeLogModelGroupColumnDao CTypeLogModelGroupColumnDao;

    public ICTypeLogModelGroupColumnDao getEntityDao() {
        return CTypeLogModelGroupColumnDao;
    }

}
