package com.voole.parrot.service.service.ctypelogmeta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.voole.parrot.service.dao.ctypelogmeta.ICtypeLogModelGroupColumnDao;
import com.voole.parrot.service.service.EntityServiceImpl;
import com.voole.parrot.shared.entity.ctypelogmeta.CtypeLogModelColumn;
import com.voole.parrot.shared.entity.ctypelogmeta.CtypeLogModelGroupColumn;

@Service
@Transactional
public class CtypeLogModelGroupColumnServiceImpl extends
		EntityServiceImpl<CtypeLogModelGroupColumn> implements
		CtypeLogModelGroupColumnService {

	@Autowired
	private ICtypeLogModelGroupColumnDao CtypeLogModelGroupColumnDao;

	public ICtypeLogModelGroupColumnDao getEntityDao() {
		return CtypeLogModelGroupColumnDao;
	}

	@Override
	public CtypeLogModelColumn createColumn(CtypeLogModelColumn column) {
		return CtypeLogModelGroupColumnDao.createColumn(column);
	}

	@Override
	public CtypeLogModelColumn modifyColumn(CtypeLogModelGroupColumn column) {
		return CtypeLogModelGroupColumnDao.modifyColumn(column);
	}

	@Override
	public CtypeLogModelColumn changeColumnsPos(CtypeLogModelColumn column) {
		return CtypeLogModelGroupColumnDao.changeColumnsPos(column);
	}

}
