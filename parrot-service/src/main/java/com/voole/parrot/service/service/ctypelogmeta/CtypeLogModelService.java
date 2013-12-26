
package com.voole.parrot.service.service.ctypelogmeta;

import com.voole.parrot.service.service.EntityService;
import com.voole.parrot.shared.entity.ctypelogmeta.CtypeLogModel;
import com.voole.parrot.shared.entity.ctypelogmeta.CtypeLogModelVersion;

public interface CtypeLogModelService
    extends EntityService<CtypeLogModel>
{

	/**
	 * @param model
	 * @return
	 */
	CtypeLogModel modifyModel(CtypeLogModel model);

	/**
	 * @param version
	 * @return
	 */
	CtypeLogModelVersion createVersion(CtypeLogModelVersion version);


}
