package com.voole.parrot.service.dao.logmeta;

import org.springframework.stereotype.Repository;

import com.voole.parrot.service.dao.EntityDao;
import com.voole.parrot.service.dao.EntityUpdater;
import com.voole.parrot.shared.entity.logmeta.LogModel;
import com.voole.parrot.shared.entity.logmeta.LogModelVersion;

@Repository
public class CtypeLogModelDao extends EntityDao<LogModel> implements
		ICtypeLogModelDao {

	@Override
	public LogModel modifyModel(LogModel model) {
		return update(model, new EntityUpdater<LogModel>() {
			@Override
			public void invoke(LogModel old, LogModel e) {
				old.setName(e.getName());
				old.setDesc(e.getDesc());
			}
		});
	}

	@Override
	public LogModelVersion createVersion(LogModelVersion version) {
		LogModel model = version.getModel();
		if (model == null) {
			return null;
		}
		model = refresh(model);
		version.setModel(model);
		Integer pos = version.getPos();
		Integer size = model.getVersions().size();
		if (pos != null && pos < size) {
			model.getVersions().add(pos, version);
		} else {
			model.getVersions().add(version);
		}
		persist(model);
		return version;
	}
}
