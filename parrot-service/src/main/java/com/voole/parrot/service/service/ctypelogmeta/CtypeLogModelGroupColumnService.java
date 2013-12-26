package com.voole.parrot.service.service.ctypelogmeta;

import com.voole.parrot.service.service.EntityService;
import com.voole.parrot.shared.entity.ctypelogmeta.CtypeLogModelColumn;
import com.voole.parrot.shared.entity.ctypelogmeta.CtypeLogModelGroupColumn;

public interface CtypeLogModelGroupColumnService extends
		EntityService<CtypeLogModelGroupColumn> {

	/**
	 * @param column
	 * @return
	 */
	CtypeLogModelColumn createColumn(CtypeLogModelColumn column);

	/**
	 * @param column
	 * @return
	 */
	CtypeLogModelColumn modifyColumn(CtypeLogModelGroupColumn column);

	CtypeLogModelColumn changeColumnsPos(CtypeLogModelColumn column);

}
