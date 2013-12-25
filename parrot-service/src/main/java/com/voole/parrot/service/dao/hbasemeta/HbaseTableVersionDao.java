package com.voole.parrot.service.dao.hbasemeta;

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

}
