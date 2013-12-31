package com.voole.parrot.log.batch;

import org.hibernate.Hibernate;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.voole.parrot.service.dao.SimpleDao;
import com.voole.parrot.shared.entity.logmeta.LogModelColumn;
import com.voole.parrot.shared.entity.logmeta.LogModelGroupColumn;
import com.voole.parrot.shared.entity.logmeta.LogModelVersion;

@Transactional
@Service
public class LogModelBatchService {
	@Autowired
	private SimpleDao simpleDao;

	public LogModelBatchService() {
	}

	public LogModelVersion get(String table, String version) {
		LogModelVersion version2 = (LogModelVersion) simpleDao.getCurrSession()
				.createCriteria(LogModelVersion.class)
				.add(Restrictions.eq("version", version))
				.createAlias("model", "model")
				.add(Restrictions.eq("model.name", table)).uniqueResult();
		Hibernate.initialize(version2.getRootColumn().getHbaseTableVersion());
		initialize(version2.getRootColumn());
		return version2;
	}

	private void initialize(LogModelGroupColumn groupColumn) {
		Hibernate.initialize(groupColumn.getColumns());
		for (LogModelColumn column : groupColumn.getColumns()) {
			if (column instanceof LogModelGroupColumn) {
				initialize((LogModelGroupColumn) column);
			}
		}
	}
}
