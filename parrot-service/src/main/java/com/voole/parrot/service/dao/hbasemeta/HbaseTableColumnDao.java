package com.voole.parrot.service.dao.hbasemeta;

import com.voole.parrot.service.dao.EntityDao;
import com.voole.parrot.service.dao.EntityUpdater;
import com.voole.parrot.shared.entity.hbasemeta.HbaseTableColumn;

import org.springframework.stereotype.Repository;

@Repository
public class HbaseTableColumnDao extends EntityDao<HbaseTableColumn> implements
		IHbaseTableColumnDao {

	@Override
	public HbaseTableColumn modifyHbaseTableColumn(HbaseTableColumn column) {
		return update(column, new EntityUpdater<HbaseTableColumn>() {

			@Override
			public void invoke(HbaseTableColumn old, HbaseTableColumn e) {
				old.setName(e.getName());
				old.setShortname(e.getShortname());
				old.setDesc(e.getDesc());
				old.setType(e.getType());
			}
		});
	}

}
