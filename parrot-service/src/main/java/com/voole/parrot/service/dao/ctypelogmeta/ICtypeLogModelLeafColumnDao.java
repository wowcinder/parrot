package com.voole.parrot.service.dao.ctypelogmeta;

import com.voole.parrot.service.dao.IEntityDao;
import com.voole.parrot.shared.entity.ctypelogmeta.CtypeLogModelColumn;
import com.voole.parrot.shared.entity.ctypelogmeta.CtypeLogModelLeafColumn;

public interface ICtypeLogModelLeafColumnDao extends
		IEntityDao<CtypeLogModelLeafColumn> {

	/**
	 * @param column
	 * @return
	 */
	CtypeLogModelColumn modifyColumn(CtypeLogModelLeafColumn column);

}
