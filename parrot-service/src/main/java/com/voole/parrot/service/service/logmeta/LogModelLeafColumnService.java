package com.voole.parrot.service.service.logmeta;

import com.voole.parrot.service.service.EntityService;
import com.voole.parrot.shared.entity.logmeta.LogModelColumn;
import com.voole.parrot.shared.entity.logmeta.LogModelLeafColumn;

public interface LogModelLeafColumnService extends
		EntityService<LogModelLeafColumn> {

	/**
	 * @param column
	 * @return
	 */
	LogModelColumn modifyColumn(LogModelLeafColumn column);

}
