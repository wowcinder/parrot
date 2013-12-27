package com.voole.parrot.service.dao.logmeta;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;

import com.voole.parrot.service.dao.EntityDao;
import com.voole.parrot.service.dao.EntityUpdater;
import com.voole.parrot.shared.entity.logmeta.LogModelColumn;
import com.voole.parrot.shared.entity.logmeta.LogModelGroupColumn;
import com.voole.parrot.shared.entity.logmeta.LogModelVersion;

@Repository
public class CtypeLogModelVersionDao extends EntityDao<LogModelVersion>
		implements ICtypeLogModelVersionDao {

	@Override
	public LogModelVersion modifyVersion(LogModelVersion version) {
		return update(version, new EntityUpdater<LogModelVersion>() {
			@Override
			public void invoke(LogModelVersion old, LogModelVersion e) {
				old.setDesc(e.getDesc());
				old.setVersion(e.getVersion());
			}
		});
	}

	@Override
	public LogModelVersion duplicateVerion(LogModelVersion duplicate,
			LogModelVersion from) {
		from = refresh(from);
		initColumns(from.getRootColumn());
		getCurrSession().evict(from);
		from.setDesc(duplicate.getDesc());
		from.setVersion(duplicate.getVersion());
		from.setId(null);

		evictColumns(from.getRootColumn());
		persist(from);
		getSimpleDao().persist(from.getModel());
		return from;
	}

	protected void evictColumns(LogModelGroupColumn group) {
		if (group == null) {
			return;
		}
		group.setId(null);
		if (group.getColumns() != null && group.getColumns().size() > 0) {
			for (LogModelColumn column : group.getColumns()) {
				column.setId(null);
				if (column instanceof LogModelGroupColumn) {
					evictColumns((LogModelGroupColumn) column);
				}
			}
		}
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
