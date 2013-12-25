
package com.voole.parrot.service.service.ctypelogmeta;

import com.voole.parrot.service.dao.ctypelogmeta.ICTypeLogModelLeafColumnDao;
import com.voole.parrot.service.service.EntityServiceImpl;
import com.voole.parrot.shared.entity.ctypelogmeta.CTypeLogModelLeafColumn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CTypeLogModelLeafColumnServiceImpl
    extends EntityServiceImpl<CTypeLogModelLeafColumn>
    implements CTypeLogModelLeafColumnService
{

    @Autowired
    private ICTypeLogModelLeafColumnDao CTypeLogModelLeafColumnDao;

    public ICTypeLogModelLeafColumnDao getEntityDao() {
        return CTypeLogModelLeafColumnDao;
    }

}
