package com.voole.parrot.service.service.ctypelogmeta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.voole.parrot.service.dao.ctypelogmeta.ICtypeLogModelLeafColumnDao;
import com.voole.parrot.service.service.EntityServiceImpl;
import com.voole.parrot.shared.entity.ctypelogmeta.CtypeLogModelColumn;
import com.voole.parrot.shared.entity.ctypelogmeta.CtypeLogModelLeafColumn;

@Service
@Transactional
public class CtypeLogModelLeafColumnServiceImpl extends
		EntityServiceImpl<CtypeLogModelLeafColumn> implements
		CtypeLogModelLeafColumnService {

	@Autowired
	private ICtypeLogModelLeafColumnDao CtypeLogModelLeafColumnDao;

	public ICtypeLogModelLeafColumnDao getEntityDao() {
		return CtypeLogModelLeafColumnDao;
	}

	@Override
	public CtypeLogModelColumn modifyColumn(CtypeLogModelLeafColumn column) {
		return CtypeLogModelLeafColumnDao.modifyColumn(column);
	}

}
