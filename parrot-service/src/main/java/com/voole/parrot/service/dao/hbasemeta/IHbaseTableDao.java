
package com.voole.parrot.service.dao.hbasemeta;

import com.voole.parrot.service.dao.IEntityDao;
import com.voole.parrot.shared.entity.hbasemeta.HbaseTable;
import com.voole.parrot.shared.entity.hbasemeta.HbaseTableVersion;

public interface IHbaseTableDao
    extends IEntityDao<HbaseTable>
{

	/**
	 * @param version
	 * @return
	 */
	HbaseTableVersion createHbaseTableVersion(HbaseTableVersion version);

	/**
	 * @param table
	 * @return
	 */
	HbaseTable modifyTable(HbaseTable table);


}
