
package com.voole.parrot.service.service.ctypelogmeta;

import com.voole.parrot.service.dao.ctypelogmeta.ICtypeLogModelGroupColumnDao;
import com.voole.parrot.service.service.EntityServiceImpl;
import com.voole.parrot.shared.entity.ctypelogmeta.CtypeLogModelGroupColumn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CtypeLogModelGroupColumnServiceImpl
    extends EntityServiceImpl<CtypeLogModelGroupColumn>
    implements CtypeLogModelGroupColumnService
{

    @Autowired
    private ICtypeLogModelGroupColumnDao CtypeLogModelGroupColumnDao;

    public ICtypeLogModelGroupColumnDao getEntityDao() {
        return CtypeLogModelGroupColumnDao;
    }

}
