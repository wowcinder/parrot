package com.voole.parrot.service.dao.ctypelogmeta;

import org.hibernate.Hibernate;
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

	@Override
	public CtypeLogModelColumn changeColumnsPos(CtypeLogModelColumn column) {
		CtypeLogModelColumn old = refresh(column);
		CtypeLogModelGroupColumn parent = old.getParent();
		// 先移除
		int i = 0;
		for (CtypeLogModelColumn item : parent.getColumns()) {
			if (item.getId().equals(old.getId())) {
				break;
			}
			i++;
		}
		parent.getColumns().remove(i);
		// 后添加
		CtypeLogModelGroupColumn parent2 = null;
		if (!column.getParent().getId().equals(parent.getId())) {
			parent2 = refresh(column.getParent());
		} else {
			parent2 = parent;
		}

		old.setParent(parent2);
		parent2.getColumns().add(column.getPos(), old);
		persist(parent);
		persist(parent2);

		getCurrSession().flush();
		if (old instanceof CtypeLogModelGroupColumn) {
			initColumns((CtypeLogModelGroupColumn) old);
		}
		return old;
	}

	protected void initColumns(CtypeLogModelGroupColumn group) {
		if (group != null && group.getColumns() != null) {
			Hibernate.initialize(group.getColumns());
		}
		if (group.getColumns().size() > 0) {
			for (CtypeLogModelColumn column : group.getColumns()) {
				if (column instanceof CtypeLogModelGroupColumn) {
					initColumns((CtypeLogModelGroupColumn) column);
				}
			}
		}
	}
}
