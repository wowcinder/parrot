
package com.voole.parrot.service.dao.hbasemeta;

import com.voole.parrot.service.dao.IEntityDao;
import com.voole.parrot.shared.entity.hbasemeta.HbaseTableColumn;
import com.voole.parrot.shared.entity.hbasemeta.HbaseTableVersion;

public interface IHbaseTableVersionDao
    extends IEntityDao<HbaseTableVersion>
{

	/**
	 * @param column
	 * @return
	 */
	HbaseTableColumn createHbaseTableColumn(HbaseTableColumn column);

	/**
	 * @param version
	 * @return
	 */
	HbaseTableVersion modifyHbaseTableVersion(HbaseTableVersion version);


}
