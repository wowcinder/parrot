package com.voole.parrot.service.service.ctypelogmeta;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.voole.parrot.service.dao.ctypelogmeta.ICtypeLogModelVersionDao;
import com.voole.parrot.service.service.EntityServiceImpl;
import com.voole.parrot.shared.entity.ctypelogmeta.CtypeLogModelColumn;
import com.voole.parrot.shared.entity.ctypelogmeta.CtypeLogModelGroupColumn;
import com.voole.parrot.shared.entity.ctypelogmeta.CtypeLogModelRootColumn;
import com.voole.parrot.shared.entity.ctypelogmeta.CtypeLogModelVersion;

@Service
@Transactional
public class CtypeLogModelVersionServiceImpl extends
		EntityServiceImpl<CtypeLogModelVersion> implements
		CtypeLogModelVersionService {

	@Autowired
	private ICtypeLogModelVersionDao CtypeLogModelVersionDao;

	public ICtypeLogModelVersionDao getEntityDao() {
		return CtypeLogModelVersionDao;
	}

	@Override
	public CtypeLogModelVersion modifyVersion(CtypeLogModelVersion version) {
		return CtypeLogModelVersionDao.modifyVersion(version);
	}

	@Override
	public CtypeLogModelRootColumn getVersionRootColumnWithChildren(
			CtypeLogModelVersion version) {
		version = CtypeLogModelVersionDao.refresh(version);
		initColumns(version.getRootColumn());
		return version.getRootColumn();
	}

	protected void initColumns(CtypeLogModelGroupColumn group) {
		if (group != null && group.getColumns() != null) {
			Hibernate.initialize(group.getColumns());
		}
		if (group.getColumns().size() > 0) {
			for (CtypeLogModelColumn column : group.getColumns()) {
				if (column instanceof CtypeLogModelGroupColumn) {
					initColumns((CtypeLogModelGroupColumn) column);
				}
			}
		}
	}

}
