package com.voole.parrot.service.dao.logmeta;

import com.voole.parrot.service.dao.IEntityDao;
import com.voole.parrot.shared.entity.logmeta.LogModel;
import com.voole.parrot.shared.entity.logmeta.LogModelVersion;

public interface ILogModelDao extends IEntityDao<LogModel> {

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
