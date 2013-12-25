
package com.voole.parrot.service.service.hbasemeta;

import com.voole.parrot.service.service.EntityService;
import com.voole.parrot.shared.entity.hbasemeta.HbaseTableColumn;
import com.voole.parrot.shared.entity.hbasemeta.HbaseTableVersion;

public interface HbaseTableVersionService
    extends EntityService<HbaseTableVersion>
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
