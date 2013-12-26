package com.voole.parrot.service.dao.ctypelogmeta;

import com.voole.parrot.service.dao.IEntityDao;
import com.voole.parrot.shared.entity.ctypelogmeta.CtypeLogModel;
import com.voole.parrot.shared.entity.ctypelogmeta.CtypeLogModelVersion;

public interface ICtypeLogModelDao extends IEntityDao<CtypeLogModel> {

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
