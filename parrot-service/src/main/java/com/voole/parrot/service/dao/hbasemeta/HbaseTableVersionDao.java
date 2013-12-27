package com.voole.parrot.service.dao.hbasemeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;

import com.voole.parrot.service.dao.EntityDao;
import com.voole.parrot.service.dao.EntityUpdater;
import com.voole.parrot.shared.entity.hbasemeta.HbaseTableColumn;
import com.voole.parrot.shared.entity.hbasemeta.HbaseTableVersion;

@Repository
public class HbaseTableVersionDao extends EntityDao<HbaseTableVersion>
		implements IHbaseTableVersionDao {

	@Override
	public HbaseTableColumn createHbaseTableColumn(HbaseTableColumn column) {
		HbaseTableVersion version = refresh(column.getVersion());
		column.setVersion(version);
		if (column.getPos() != null
				&& version.getColumns().size() > column.getPos()) {
			version.getColumns().add(column.getPos(), column);
		} else {
			version.getColumns().add(column);
		}
		persist(version);
		return column;
	}

	@Override
	public HbaseTableVersion modifyHbaseTableVersion(HbaseTableVersion version) {
		return update(version, new EntityUpdater<HbaseTableVersion>() {

			@Override
			public void invoke(HbaseTableVersion old, HbaseTableVersion e) {
				old.setVersion(e.getVersion());
				old.setDesc(e.getDesc());
			}
		});
	}

	@Override
	public HbaseTableVersion duplicateHbaseTableVerion(
			HbaseTableVersion duplicate, HbaseTableVersion from) {
		from = refresh(from);
		Hibernate.initialize(from.getColumns());
		getCurrSession().evict(from);
		from.setId(null);
		from.setLogModelGroupColumns(null);
		from.setDesc(duplicate.getDesc());
		from.setVersion(duplicate.getVersion());
		for (HbaseTableColumn column : from.getColumns()) {
			column.setId(null);
			column.setVersion(from);
			column.setLogModelLeafColumns(null);
		}
		persist(from);
		getSimpleDao().persist(from.getTable());
		return from;
	}

	@Override
	public void changeHbaseTableColumnsPos(List<HbaseTableColumn> columns,
			Integer pos) {
		if (columns == null || columns.size() == 0) {
			return;
		}
		HbaseTableVersion version = refresh(columns.get(0).getVersion());
		Hibernate.initialize(version.getColumns());
		List<HbaseTableColumn> versionColumns = version.getColumns();
		Map<Long, HbaseTableColumn> columnsMap = new HashMap<Long, HbaseTableColumn>();
		for (HbaseTableColumn column : versionColumns) {
			columnsMap.put(column.getId(), column);
		}

		List<HbaseTableColumn> columns2 = new ArrayList<HbaseTableColumn>();
		for (HbaseTableColumn column : columns) {
			Long id = column.getId();
			if (columnsMap.containsKey(id)) {
				HbaseTableColumn column2 = columnsMap.get(id);
				versionColumns.remove(column2);
				columns2.add(column2);
			}
		}
		versionColumns.addAll(pos, columns2);
		persist(version);
	}
}
