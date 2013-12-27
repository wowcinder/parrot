package com.voole.parrot.service.service.logmeta;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.voole.parrot.service.dao.logmeta.ILogModelVersionDao;
import com.voole.parrot.service.service.EntityServiceImpl;
import com.voole.parrot.shared.entity.logmeta.LogModelColumn;
import com.voole.parrot.shared.entity.logmeta.LogModelGroupColumn;
import com.voole.parrot.shared.entity.logmeta.LogModelRootColumn;
import com.voole.parrot.shared.entity.logmeta.LogModelVersion;

@Service
@Transactional
public class LogModelVersionServiceImpl extends
		EntityServiceImpl<LogModelVersion> implements
		LogModelVersionService {

	@Autowired
	private ILogModelVersionDao LogModelVersionDao;

	public ILogModelVersionDao getEntityDao() {
		return LogModelVersionDao;
	}

	@Override
	public LogModelVersion modifyVersion(LogModelVersion version) {
		return LogModelVersionDao.modifyVersion(version);
	}

	@Override
	public LogModelRootColumn getVersionRootColumnWithChildren(
			LogModelVersion version) {
		version = LogModelVersionDao.refresh(version);
		initColumns(version.getRootColumn());
		return version.getRootColumn();
	}

	protected void initColumns(LogModelGroupColumn group) {
		if (group != null && group.getColumns() != null) {
			Hibernate.initialize(group.getColumns());
		}
		if (group.getColumns().size() > 0) {
			for (LogModelColumn column : group.getColumns()) {
				if (column instanceof LogModelGroupColumn) {
					initColumns((LogModelGroupColumn) column);
				}
			}
		}
	}

	@Override
	public LogModelVersion duplicateVerion(LogModelVersion duplicate,
			LogModelVersion from) {
		return LogModelVersionDao.duplicateVerion(duplicate, from);
	}

}
