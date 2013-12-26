package com.voole.parrot.service.dao.ctypelogmeta;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;

import com.voole.parrot.service.dao.EntityDao;
import com.voole.parrot.service.dao.EntityUpdater;
import com.voole.parrot.shared.entity.ctypelogmeta.CtypeLogModelColumn;
import com.voole.parrot.shared.entity.ctypelogmeta.CtypeLogModelGroupColumn;
import com.voole.parrot.shared.entity.ctypelogmeta.CtypeLogModelVersion;

@Repository
public class CtypeLogModelVersionDao extends EntityDao<CtypeLogModelVersion>
		implements ICtypeLogModelVersionDao {

	@Override
	public CtypeLogModelVersion modifyVersion(CtypeLogModelVersion version) {
		return update(version, new EntityUpdater<CtypeLogModelVersion>() {
			@Override
			public void invoke(CtypeLogModelVersion old, CtypeLogModelVersion e) {
				old.setDesc(e.getDesc());
				old.setVersion(e.getVersion());
			}
		});
	}

	@Override
	public CtypeLogModelVersion duplicateVerion(CtypeLogModelVersion duplicate,
			CtypeLogModelVersion from) {
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

	protected void evictColumns(CtypeLogModelGroupColumn group) {
		if (group == null) {
			return;
		}
		group.setId(null);
		if (group.getColumns() != null && group.getColumns().size() > 0) {
			for (CtypeLogModelColumn column : group.getColumns()) {
				column.setId(null);
				if (column instanceof CtypeLogModelGroupColumn) {
					evictColumns((CtypeLogModelGroupColumn) column);
				}
			}
		}
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
