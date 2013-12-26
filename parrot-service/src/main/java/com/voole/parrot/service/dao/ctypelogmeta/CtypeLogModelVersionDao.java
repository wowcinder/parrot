package com.voole.parrot.service.dao.ctypelogmeta;

import org.springframework.stereotype.Repository;

import com.voole.parrot.service.dao.EntityDao;
import com.voole.parrot.service.dao.EntityUpdater;
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

}
