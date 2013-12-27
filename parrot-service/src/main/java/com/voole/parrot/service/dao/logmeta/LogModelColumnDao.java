package com.voole.parrot.service.dao.logmeta;

import org.springframework.stereotype.Repository;

import com.voole.parrot.service.dao.EntityDao;
import com.voole.parrot.shared.entity.logmeta.LogModelColumn;
import com.voole.parrot.shared.entity.logmeta.LogModelGroupColumn;

@Repository
public class LogModelColumnDao extends EntityDao<LogModelColumn>
		implements ICtypeLogModelColumnDao {

	@Override
	public void delete(LogModelColumn e) {
		e = refresh(e);
		if (e instanceof LogModelGroupColumn) {
			LogModelGroupColumn group = (LogModelGroupColumn) e;
			if (group.getColumns() != null && group.getColumns().size() > 0) {
				for (LogModelColumn column : group.getColumns()) {
					delete(column);
				}
			}
		}
		getCurrSession().delete(e);
		getCurrSession().flush();
	}
}
