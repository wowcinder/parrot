
package com.voole.parrot.service.service.logmeta;

import com.voole.parrot.service.dao.logmeta.ILogModelRootColumnDao;
import com.voole.parrot.service.service.EntityServiceImpl;
import com.voole.parrot.shared.entity.logmeta.LogModelRootColumn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LogModelRootColumnServiceImpl
    extends EntityServiceImpl<LogModelRootColumn>
    implements LogModelRootColumnService
{

    @Autowired
    private ILogModelRootColumnDao LogModelRootColumnDao;

    public ILogModelRootColumnDao getEntityDao() {
        return LogModelRootColumnDao;
    }

}
