
package com.voole.parrot.service.dao.ctypelogmeta;

import com.voole.parrot.service.dao.IEntityDao;
import com.voole.parrot.shared.entity.ctypelogmeta.CtypeLogModelVersion;

public interface ICtypeLogModelVersionDao
    extends IEntityDao<CtypeLogModelVersion>
{

	/**
	 * @param version
	 * @return
	 */
	CtypeLogModelVersion modifyVersion(CtypeLogModelVersion version);

	/**
	 * @param duplicate
	 * @param from
	 * @return
	 */
	CtypeLogModelVersion duplicateVerion(CtypeLogModelVersion duplicate,
			CtypeLogModelVersion from);


}
