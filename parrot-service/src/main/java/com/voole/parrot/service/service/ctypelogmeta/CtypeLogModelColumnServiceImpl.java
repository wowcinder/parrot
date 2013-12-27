
package com.voole.parrot.service.service.ctypelogmeta;

import com.voole.parrot.service.dao.logmeta.ICtypeLogModelColumnDao;
import com.voole.parrot.service.service.EntityServiceImpl;
import com.voole.parrot.shared.entity.logmeta.LogModelColumn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CtypeLogModelColumnServiceImpl
    extends EntityServiceImpl<LogModelColumn>
    implements CtypeLogModelColumnService
{

    @Autowired
    private ICtypeLogModelColumnDao CtypeLogModelColumnDao;

    public ICtypeLogModelColumnDao getEntityDao() {
        return CtypeLogModelColumnDao;
    }

}
