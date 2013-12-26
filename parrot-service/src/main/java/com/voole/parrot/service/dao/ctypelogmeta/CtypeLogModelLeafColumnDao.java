package com.voole.parrot.service.dao.ctypelogmeta;

import org.springframework.stereotype.Repository;

import com.voole.parrot.service.dao.EntityDao;
import com.voole.parrot.service.dao.EntityUpdater;
import com.voole.parrot.shared.entity.ctypelogmeta.CtypeLogModelColumn;
import com.voole.parrot.shared.entity.ctypelogmeta.CtypeLogModelLeafColumn;

@Repository
public class CtypeLogModelLeafColumnDao extends
		EntityDao<CtypeLogModelLeafColumn> implements
		ICtypeLogModelLeafColumnDao {

	@Override
	public CtypeLogModelColumn modifyColumn(CtypeLogModelLeafColumn column) {
		return update(column, new EntityUpdater<CtypeLogModelLeafColumn>() {
			@Override
			public void invoke(CtypeLogModelLeafColumn old,
					CtypeLogModelLeafColumn e) {
				old.setName(e.getName());
				old.setDesc(e.getDesc());
				old.setType(e.getType());
				old.setHbaseTableColumn(e.getHbaseTableColumn());
			}
		});
	}

}
