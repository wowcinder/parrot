
package com.voole.parrot.service.service.ctypelogmeta;

import com.voole.parrot.service.dao.ctypelogmeta.ICtypeLogModelColumnDao;
import com.voole.parrot.service.service.EntityServiceImpl;
import com.voole.parrot.shared.entity.ctypelogmeta.CtypeLogModelColumn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CtypeLogModelColumnServiceImpl
    extends EntityServiceImpl<CtypeLogModelColumn>
    implements CtypeLogModelColumnService
{

    @Autowired
    private ICtypeLogModelColumnDao CtypeLogModelColumnDao;

    public ICtypeLogModelColumnDao getEntityDao() {
        return CtypeLogModelColumnDao;
    }

}
