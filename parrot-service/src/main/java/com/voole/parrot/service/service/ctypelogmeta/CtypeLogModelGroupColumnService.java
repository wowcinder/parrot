package com.voole.parrot.service.service.ctypelogmeta;

import com.voole.parrot.service.service.EntityService;
import com.voole.parrot.shared.entity.logmeta.LogModelColumn;
import com.voole.parrot.shared.entity.logmeta.LogModelGroupColumn;

public interface CtypeLogModelGroupColumnService extends
		EntityService<LogModelGroupColumn> {

	/**
	 * @param column
	 * @return
	 */
	LogModelColumn createColumn(LogModelColumn column);

	/**
	 * @param column
	 * @return
	 */
	LogModelColumn modifyColumn(LogModelGroupColumn column);

	LogModelColumn changeColumnsPos(LogModelColumn column);

}
