package com.voole.parrot.service.dao.hbasemeta;

import org.springframework.stereotype.Repository;

import com.voole.parrot.service.dao.EntityDao;
import com.voole.parrot.service.dao.EntityUpdater;
import com.voole.parrot.shared.entity.hbasemeta.HbaseTable;
import com.voole.parrot.shared.entity.hbasemeta.HbaseTableVersion;

@Repository
public class HbaseTableDao extends EntityDao<HbaseTable> implements
		IHbaseTableDao {

	@Override
	public HbaseTableVersion createHbaseTableVersion(HbaseTableVersion version) {
		HbaseTable table = refresh(version.getTable());
		version.setTable(table);
		if (version.getPos() != null
				&& table.getVersions().size() > version.getPos()) {
			table.getVersions().add(version.getPos(), version);
		} else {
			table.getVersions().add(version);
		}
		this.persist(table);
		return version;
	}

	@Override
	public HbaseTable modifyTable(HbaseTable table) {
		return update(table, new EntityUpdater<HbaseTable>() {

			@Override
			public void invoke(HbaseTable old, HbaseTable e) {
				old.setName(e.getName());
				old.setDesc(e.getDesc());
				old.setShortname(e.getShortname());
			}
		});
	}
}
