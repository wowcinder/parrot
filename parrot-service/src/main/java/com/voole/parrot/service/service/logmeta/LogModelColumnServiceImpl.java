
package com.voole.parrot.service.service.logmeta;

import com.voole.parrot.service.dao.logmeta.ILogModelColumnDao;
import com.voole.parrot.service.service.EntityServiceImpl;
import com.voole.parrot.shared.entity.logmeta.LogModelColumn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LogModelColumnServiceImpl
    extends EntityServiceImpl<LogModelColumn>
    implements LogModelColumnService
{

    @Autowired
    private ILogModelColumnDao CtypeLogModelColumnDao;

    public ILogModelColumnDao getEntityDao() {
        return CtypeLogModelColumnDao;
    }

}
