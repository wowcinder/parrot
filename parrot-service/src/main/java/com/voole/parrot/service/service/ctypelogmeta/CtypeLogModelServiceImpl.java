package com.voole.parrot.service.service.ctypelogmeta;

import com.voole.parrot.service.dao.ctypelogmeta.ICtypeLogModelDao;
import com.voole.parrot.service.service.EntityServiceImpl;
import com.voole.parrot.shared.entity.ctypelogmeta.CtypeLogModel;
import com.voole.parrot.shared.entity.ctypelogmeta.CtypeLogModelVersion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CtypeLogModelServiceImpl extends EntityServiceImpl<CtypeLogModel>
		implements CtypeLogModelService {

	@Autowired
	private ICtypeLogModelDao CtypeLogModelDao;

	public ICtypeLogModelDao getEntityDao() {
		return CtypeLogModelDao;
	}

	@Override
	public CtypeLogModel modifyModel(CtypeLogModel model) {
		return CtypeLogModelDao.modifyModel(model);
	}

	@Override
	public CtypeLogModelVersion createVersion(CtypeLogModelVersion version) {
		return CtypeLogModelDao.createVersion(version);
	}

}
