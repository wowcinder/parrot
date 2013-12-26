package com.voole.parrot.service.dao.ctypelogmeta;

import org.springframework.stereotype.Repository;

import com.voole.parrot.service.dao.EntityDao;
import com.voole.parrot.service.dao.EntityUpdater;
import com.voole.parrot.shared.entity.ctypelogmeta.CtypeLogModelColumn;
import com.voole.parrot.shared.entity.ctypelogmeta.CtypeLogModelGroupColumn;

@Repository
public class CtypeLogModelGroupColumnDao extends
		EntityDao<CtypeLogModelGroupColumn> implements
		ICtypeLogModelGroupColumnDao {

	@Override
	public CtypeLogModelColumn createColumn(CtypeLogModelColumn column) {
		CtypeLogModelGroupColumn group = column.getParent();
		if (group == null) {
			return null;
		}
		group = refresh(group);
		column.setParent(group);

		Integer pos = column.getPos();
		Integer size = group.getColumns().size();
		if (pos != null && pos < size) {
			group.getColumns().add(pos, column);
		} else {
			group.getColumns().add(column);
		}
		persist(group);
		return column;
	}

	@Override
	public CtypeLogModelColumn modifyColumn(CtypeLogModelGroupColumn column) {
		return update(column, new EntityUpdater<CtypeLogModelGroupColumn>() {
			@Override
			public void invoke(CtypeLogModelGroupColumn old,
					CtypeLogModelGroupColumn e) {
				old.setName(e.getName());
				old.setDesc(e.getDesc());
				old.setHbaseTableVersion(e.getHbaseTableVersion());
			}
		});
	}

}
