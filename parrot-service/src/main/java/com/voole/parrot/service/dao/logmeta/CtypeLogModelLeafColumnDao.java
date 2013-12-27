package com.voole.parrot.service.dao.logmeta;

import org.springframework.stereotype.Repository;

import com.voole.parrot.service.dao.EntityDao;
import com.voole.parrot.service.dao.EntityUpdater;
import com.voole.parrot.shared.entity.logmeta.LogModelColumn;
import com.voole.parrot.shared.entity.logmeta.LogModelLeafColumn;

@Repository
public class CtypeLogModelLeafColumnDao extends
		EntityDao<LogModelLeafColumn> implements
		ICtypeLogModelLeafColumnDao {

	@Override
	public LogModelColumn modifyColumn(LogModelLeafColumn column) {
		return update(column, new EntityUpdater<LogModelLeafColumn>() {
			@Override
			public void invoke(LogModelLeafColumn old,
					LogModelLeafColumn e) {
				old.setName(e.getName());
				old.setDesc(e.getDesc());
				old.setType(e.getType());
				old.setHbaseTableColumn(e.getHbaseTableColumn());
			}
		});
	}

}
