
package com.voole.parrot.service.service.hbasemeta;

import com.voole.parrot.service.dao.hbasemeta.IHbaseTableColumnDao;
import com.voole.parrot.service.service.EntityServiceImpl;
import com.voole.parrot.shared.entity.hbasemeta.HbaseTableColumn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class HbaseTableColumnServiceImpl
    extends EntityServiceImpl<HbaseTableColumn>
    implements HbaseTableColumnService
{

    @Autowired
    private IHbaseTableColumnDao HbaseTableColumnDao;

    public IHbaseTableColumnDao getEntityDao() {
        return HbaseTableColumnDao;
    }

	@Override
	public HbaseTableColumn modifyHbaseTableColumn(HbaseTableColumn column) {
		return HbaseTableColumnDao.modifyHbaseTableColumn(column);
	}

}
