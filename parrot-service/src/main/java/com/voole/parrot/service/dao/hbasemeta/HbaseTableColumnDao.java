
package com.voole.parrot.service.dao.hbasemeta;

import com.voole.parrot.service.dao.EntityDao;
import com.voole.parrot.shared.entity.hbasemeta.HbaseTableColumn;
import org.springframework.stereotype.Repository;

@Repository
public class HbaseTableColumnDao
    extends EntityDao<HbaseTableColumn>
    implements IHbaseTableColumnDao
{


}
