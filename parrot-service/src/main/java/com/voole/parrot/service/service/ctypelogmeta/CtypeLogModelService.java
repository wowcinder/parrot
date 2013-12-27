
package com.voole.parrot.service.service.ctypelogmeta;

import com.voole.parrot.service.service.EntityService;
import com.voole.parrot.shared.entity.logmeta.LogModel;
import com.voole.parrot.shared.entity.logmeta.LogModelVersion;

public interface CtypeLogModelService
    extends EntityService<LogModel>
{

	/**
	 * @param model
	 * @return
	 */
	LogModel modifyModel(LogModel model);

	/**
	 * @param version
	 * @return
	 */
	LogModelVersion createVersion(LogModelVersion version);


}
