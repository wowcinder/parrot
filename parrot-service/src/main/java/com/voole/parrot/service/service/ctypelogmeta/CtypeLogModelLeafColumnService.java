package com.voole.parrot.service.service.ctypelogmeta;

import com.voole.parrot.service.service.EntityService;
import com.voole.parrot.shared.entity.ctypelogmeta.CtypeLogModelColumn;
import com.voole.parrot.shared.entity.ctypelogmeta.CtypeLogModelLeafColumn;

public interface CtypeLogModelLeafColumnService extends
		EntityService<CtypeLogModelLeafColumn> {

	/**
	 * @param column
	 * @return
	 */
	CtypeLogModelColumn modifyColumn(CtypeLogModelLeafColumn column);

}
