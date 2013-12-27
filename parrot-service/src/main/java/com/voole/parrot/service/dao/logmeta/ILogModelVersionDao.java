
package com.voole.parrot.service.dao.logmeta;

import com.voole.parrot.service.dao.IEntityDao;
import com.voole.parrot.shared.entity.logmeta.LogModelVersion;

public interface ILogModelVersionDao
    extends IEntityDao<LogModelVersion>
{

	/**
	 * @param version
	 * @return
	 */
	LogModelVersion modifyVersion(LogModelVersion version);

	/**
	 * @param duplicate
	 * @param from
	 * @return
	 */
	LogModelVersion duplicateVerion(LogModelVersion duplicate,
			LogModelVersion from);


}
