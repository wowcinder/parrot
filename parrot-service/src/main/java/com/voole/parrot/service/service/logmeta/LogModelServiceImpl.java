package com.voole.parrot.service.service.logmeta;

import com.voole.parrot.service.dao.logmeta.ILogModelDao;
import com.voole.parrot.service.service.EntityServiceImpl;
import com.voole.parrot.shared.entity.logmeta.LogModel;
import com.voole.parrot.shared.entity.logmeta.LogModelVersion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LogModelServiceImpl extends EntityServiceImpl<LogModel>
		implements LogModelService {

	@Autowired
	private ILogModelDao LogModelDao;

	public ILogModelDao getEntityDao() {
		return LogModelDao;
	}

	@Override
	public LogModel modifyModel(LogModel model) {
		return LogModelDao.modifyModel(model);
	}

	@Override
	public LogModelVersion createVersion(LogModelVersion version) {
		return LogModelDao.createVersion(version);
	}

}
