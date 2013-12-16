package com.voole.parrot.service.dao.organization;

import org.springframework.stereotype.Repository;

import com.voole.parrot.service.dao.EntityDao;
import com.voole.parrot.shared.organization.Organization;
@Repository
public class OrganizationDao extends EntityDao<Organization> implements
		IOrganizationDao {
}
