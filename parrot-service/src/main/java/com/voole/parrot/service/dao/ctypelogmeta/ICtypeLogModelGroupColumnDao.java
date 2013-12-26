
package com.voole.parrot.service.dao.ctypelogmeta;

import com.voole.parrot.service.dao.IEntityDao;
import com.voole.parrot.shared.entity.ctypelogmeta.CtypeLogModelColumn;
import com.voole.parrot.shared.entity.ctypelogmeta.CtypeLogModelGroupColumn;

public interface ICtypeLogModelGroupColumnDao
    extends IEntityDao<CtypeLogModelGroupColumn>
{

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


}
