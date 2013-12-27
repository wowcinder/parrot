package com.voole.parrot.service.service.logmeta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.voole.parrot.service.dao.logmeta.ILogModelGroupColumnDao;
import com.voole.parrot.service.service.EntityServiceImpl;
import com.voole.parrot.shared.entity.logmeta.LogModelColumn;
import com.voole.parrot.shared.entity.logmeta.LogModelGroupColumn;

@Service
@Transactional
public class LogModelGroupColumnServiceImpl extends
		EntityServiceImpl<LogModelGroupColumn> implements
		LogModelGroupColumnService {

	@Autowired
	private ILogModelGroupColumnDao LogModelGroupColumnDao;

	public ILogModelGroupColumnDao getEntityDao() {
		return LogModelGroupColumnDao;
	}

	@Override
	public LogModelColumn createColumn(LogModelColumn column) {
		return LogModelGroupColumnDao.createColumn(column);
	}

	@Override
	public LogModelColumn modifyColumn(LogModelGroupColumn column) {
		return LogModelGroupColumnDao.modifyColumn(column);
	}

	@Override
	public LogModelColumn changeColumnsPos(LogModelColumn column) {
		return LogModelGroupColumnDao.changeColumnsPos(column);
	}

}
