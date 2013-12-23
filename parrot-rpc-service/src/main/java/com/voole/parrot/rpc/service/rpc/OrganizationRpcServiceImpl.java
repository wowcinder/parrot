package com.voole.parrot.rpc.service.rpc;

import java.util.List;
import java.util.Set;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.voole.parrot.gwt.common.shared.rpcservice.OrganizationRpcService;
import com.voole.parrot.service.dao.EntityUpdater;
import com.voole.parrot.service.service.organization.SubOrganizationService;
import com.voole.parrot.service.service.organization.TopOrganizationService;
import com.voole.parrot.shared.entity.organization.Organization;
import com.voole.parrot.shared.entity.organization.SubOrganization;
import com.voole.parrot.shared.entity.organization.TopOrganization;
import com.voole.parrot.shared.exception.SharedException;

@Service
public class OrganizationRpcServiceImpl implements OrganizationRpcService {
	@Autowired
	private TopOrganizationService topOrganizationService;
	@Autowired
	private SubOrganizationService subOrganizationService;

	@Override
	@Transactional
	public List<TopOrganization> getTopOrganizations() throws SharedException {
		List<TopOrganization> list = topOrganizationService.list();
		for (TopOrganization topOrganization : list) {
			initOrganization(topOrganization);
		}
		return list;
	}

	public void initOrganization(Organization organization) {
		Set<SubOrganization> subOrganizations = organization
				.getSubOrganizations();
		if (subOrganizations != null) {
			Hibernate.initialize(subOrganizations);
			for (SubOrganization subOrganization : subOrganizations) {
				initOrganization(subOrganization);
			}
		}
	}

	@Override
	public TopOrganization createTopOrganization(TopOrganization top)
			throws SharedException {
		return topOrganizationService.persist(top);
	}

	@Override
	public TopOrganization changeTopName(TopOrganization top)
			throws SharedException {
		return topOrganizationService.update(top,
				new EntityUpdater<TopOrganization>() {
					@Override
					public void invoke(TopOrganization old, TopOrganization e) {
						old.setName(e.getName());
					}
				});
	}

	@Override
	@Transactional
	public SubOrganization createSubOrganization(SubOrganization sub)
			throws SharedException {
		Organization parent = subOrganizationService.getEntityDao().refresh(
				sub.getParentOrganization());
		sub.setParentOrganization(parent);
		return subOrganizationService.persist(sub);
	}

}
