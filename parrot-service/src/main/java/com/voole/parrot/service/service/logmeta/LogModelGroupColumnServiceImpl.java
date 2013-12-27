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
	private ILogModelGroupColumnDao CtypeLogModelGroupColumnDao;

	public ILogModelGroupColumnDao getEntityDao() {
		return CtypeLogModelGroupColumnDao;
	}

	@Override
	public LogModelColumn createColumn(LogModelColumn column) {
		return CtypeLogModelGroupColumnDao.createColumn(column);
	}

	@Override
	public LogModelColumn modifyColumn(LogModelGroupColumn column) {
		return CtypeLogModelGroupColumnDao.modifyColumn(column);
	}

	@Override
	public LogModelColumn changeColumnsPos(LogModelColumn column) {
		return CtypeLogModelGroupColumnDao.changeColumnsPos(column);
	}

}
