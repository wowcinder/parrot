
package com.voole.parrot.service.service.ctypelogmeta;

import com.voole.parrot.service.service.EntityService;
import com.voole.parrot.shared.entity.ctypelogmeta.CtypeLogModelRootColumn;
import com.voole.parrot.shared.entity.ctypelogmeta.CtypeLogModelVersion;

public interface CtypeLogModelVersionService
    extends EntityService<CtypeLogModelVersion>
{

	/**
	 * @param version
	 * @return
	 */
	CtypeLogModelVersion modifyVersion(CtypeLogModelVersion version);

	/**
	 * @param version
	 * @return
	 */
	CtypeLogModelRootColumn getVersionRootColumnWithChildren(
			CtypeLogModelVersion version);


}
