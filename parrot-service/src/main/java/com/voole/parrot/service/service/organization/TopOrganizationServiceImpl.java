package com.voole.parrot.service.service.organization;

import com.voole.parrot.service.dao.organization.ITopOrganizationDao;
import com.voole.parrot.service.service.EntityServiceImpl;
import com.voole.parrot.shared.entity.organization.TopOrganization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TopOrganizationServiceImpl extends
		EntityServiceImpl<TopOrganization> implements TopOrganizationService {

	@Autowired
	private ITopOrganizationDao TopOrganizationDao;

	public ITopOrganizationDao getEntityDao() {
		return TopOrganizationDao;
	}

}
