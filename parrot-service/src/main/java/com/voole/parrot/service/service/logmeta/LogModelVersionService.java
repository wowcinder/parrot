
package com.voole.parrot.service.service.logmeta;

import com.voole.parrot.service.service.EntityService;
import com.voole.parrot.shared.entity.logmeta.LogModelRootColumn;
import com.voole.parrot.shared.entity.logmeta.LogModelVersion;

public interface LogModelVersionService
    extends EntityService<LogModelVersion>
{

	/**
	 * @param version
	 * @return
	 */
	LogModelVersion modifyVersion(LogModelVersion version);

	/**
	 * @param version
	 * @return
	 */
	LogModelRootColumn getVersionRootColumnWithChildren(
			LogModelVersion version);

	/**
	 * @param duplicate
	 * @param from
	 * @return
	 */
	LogModelVersion duplicateVerion(LogModelVersion duplicate,
			LogModelVersion from);


}
