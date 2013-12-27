
package com.voole.parrot.service.dao.logmeta;

import com.voole.parrot.service.dao.IEntityDao;
import com.voole.parrot.shared.entity.logmeta.LogModelColumn;
import com.voole.parrot.shared.entity.logmeta.LogModelGroupColumn;

public interface ILogModelGroupColumnDao
    extends IEntityDao<LogModelGroupColumn>
{

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
