package com.voole.parrot.service.dao.logmeta;

import com.voole.parrot.service.dao.IEntityDao;
import com.voole.parrot.shared.entity.logmeta.LogModelColumn;
import com.voole.parrot.shared.entity.logmeta.LogModelLeafColumn;

public interface ICtypeLogModelLeafColumnDao extends
		IEntityDao<LogModelLeafColumn> {

	/**
	 * @param column
	 * @return
	 */
	LogModelColumn modifyColumn(LogModelLeafColumn column);

}
