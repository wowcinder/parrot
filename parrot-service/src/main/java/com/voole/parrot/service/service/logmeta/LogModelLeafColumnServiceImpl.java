package com.voole.parrot.service.service.logmeta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.voole.parrot.service.dao.logmeta.ILogModelLeafColumnDao;
import com.voole.parrot.service.service.EntityServiceImpl;
import com.voole.parrot.shared.entity.logmeta.LogModelColumn;
import com.voole.parrot.shared.entity.logmeta.LogModelLeafColumn;

@Service
@Transactional
public class LogModelLeafColumnServiceImpl extends
		EntityServiceImpl<LogModelLeafColumn> implements
		LogModelLeafColumnService {

	@Autowired
	private ILogModelLeafColumnDao LogModelLeafColumnDao;

	public ILogModelLeafColumnDao getEntityDao() {
		return LogModelLeafColumnDao;
	}

	@Override
	public LogModelColumn modifyColumn(LogModelLeafColumn column) {
		return LogModelLeafColumnDao.modifyColumn(column);
	}

}
