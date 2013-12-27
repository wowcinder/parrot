package com.voole.parrot.service.service.ctypelogmeta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.voole.parrot.service.dao.logmeta.ICtypeLogModelLeafColumnDao;
import com.voole.parrot.service.service.EntityServiceImpl;
import com.voole.parrot.shared.entity.logmeta.LogModelColumn;
import com.voole.parrot.shared.entity.logmeta.LogModelLeafColumn;

@Service
@Transactional
public class CtypeLogModelLeafColumnServiceImpl extends
		EntityServiceImpl<LogModelLeafColumn> implements
		CtypeLogModelLeafColumnService {

	@Autowired
	private ICtypeLogModelLeafColumnDao CtypeLogModelLeafColumnDao;

	public ICtypeLogModelLeafColumnDao getEntityDao() {
		return CtypeLogModelLeafColumnDao;
	}

	@Override
	public LogModelColumn modifyColumn(LogModelLeafColumn column) {
		return CtypeLogModelLeafColumnDao.modifyColumn(column);
	}

}
