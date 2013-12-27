package com.voole.parrot.service.dao.logmeta;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;

import com.voole.parrot.service.dao.EntityDao;
import com.voole.parrot.service.dao.EntityUpdater;
import com.voole.parrot.shared.entity.logmeta.LogModelColumn;
import com.voole.parrot.shared.entity.logmeta.LogModelGroupColumn;

@Repository
public class LogModelGroupColumnDao extends
		EntityDao<LogModelGroupColumn> implements
		ILogModelGroupColumnDao {

	@Override
	public LogModelColumn createColumn(LogModelColumn column) {
		LogModelGroupColumn group = column.getParent();
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
	public LogModelColumn modifyColumn(LogModelGroupColumn column) {
		return update(column, new EntityUpdater<LogModelGroupColumn>() {
			@Override
			public void invoke(LogModelGroupColumn old,
					LogModelGroupColumn e) {
				old.setName(e.getName());
				old.setDesc(e.getDesc());
				old.setHbaseTableVersion(e.getHbaseTableVersion());
			}
		});
	}

	@Override
	public LogModelColumn changeColumnsPos(LogModelColumn column) {
		LogModelColumn old = refresh(column);
		LogModelGroupColumn parent = old.getParent();
		// 先移除
		int i = 0;
		for (LogModelColumn item : parent.getColumns()) {
			if (item.getId().equals(old.getId())) {
				break;
			}
			i++;
		}
		parent.getColumns().remove(i);
		// 后添加
		LogModelGroupColumn parent2 = null;
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
		if (old instanceof LogModelGroupColumn) {
			initColumns((LogModelGroupColumn) old);
		}
		return old;
	}

	protected void initColumns(LogModelGroupColumn group) {
		if (group != null && group.getColumns() != null) {
			Hibernate.initialize(group.getColumns());
		}
		if (group.getColumns().size() > 0) {
			for (LogModelColumn column : group.getColumns()) {
				if (column instanceof LogModelGroupColumn) {
					initColumns((LogModelGroupColumn) column);
				}
			}
		}
	}
}
