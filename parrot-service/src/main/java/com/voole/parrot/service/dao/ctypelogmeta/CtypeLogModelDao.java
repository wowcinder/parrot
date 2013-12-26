package com.voole.parrot.service.dao.ctypelogmeta;

import org.springframework.stereotype.Repository;

import com.voole.parrot.service.dao.EntityDao;
import com.voole.parrot.service.dao.EntityUpdater;
import com.voole.parrot.shared.entity.ctypelogmeta.CtypeLogModel;
import com.voole.parrot.shared.entity.ctypelogmeta.CtypeLogModelVersion;

@Repository
public class CtypeLogModelDao extends EntityDao<CtypeLogModel> implements
		ICtypeLogModelDao {

	@Override
	public CtypeLogModel modifyModel(CtypeLogModel model) {
		return update(model, new EntityUpdater<CtypeLogModel>() {
			@Override
			public void invoke(CtypeLogModel old, CtypeLogModel e) {
				old.setName(e.getName());
				old.setDesc(e.getDesc());
			}
		});
	}

	@Override
	public CtypeLogModelVersion createVersion(CtypeLogModelVersion version) {
		CtypeLogModel model = version.getModel();
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
