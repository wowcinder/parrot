package com.voole.parrot.service.dao.ctypelogmeta;

import org.springframework.stereotype.Repository;

import com.voole.parrot.service.dao.EntityDao;
import com.voole.parrot.shared.entity.ctypelogmeta.CtypeLogModelColumn;
import com.voole.parrot.shared.entity.ctypelogmeta.CtypeLogModelGroupColumn;

@Repository
public class CtypeLogModelColumnDao extends EntityDao<CtypeLogModelColumn>
		implements ICtypeLogModelColumnDao {

	@Override
	public void delete(CtypeLogModelColumn e) {
		e = refresh(e);
		if (e instanceof CtypeLogModelGroupColumn) {
			CtypeLogModelGroupColumn group = (CtypeLogModelGroupColumn) e;
			if (group.getColumns() != null && group.getColumns().size() > 0) {
				for (CtypeLogModelColumn column : group.getColumns()) {
					delete(column);
				}
			}
		}
		getCurrSession().delete(e);
		getCurrSession().flush();
	}
}
