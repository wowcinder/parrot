
package com.voole.parrot.service.service.hbasemeta;

import com.voole.parrot.service.service.EntityService;
import com.voole.parrot.shared.entity.hbasemeta.HbaseTable;
import com.voole.parrot.shared.entity.hbasemeta.HbaseTableVersion;

public interface HbaseTableService
    extends EntityService<HbaseTable>
{

	/**
	 * @param versino
	 * @return
	 */
	HbaseTableVersion createHbaseTableVersion(HbaseTableVersion version);

	/**
	 * @param table
	 * @return
	 */
	HbaseTable modifyTable(HbaseTable table);


}
