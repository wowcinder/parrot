package com.voole.parrot.service.dao.authority;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.voole.parrot.service.dao.EntityDao;
import com.voole.parrot.service.dao.organization.ITopOrganizationAuthorityDao;
import com.voole.parrot.shared.authority.Authority;
import com.voole.parrot.shared.organization.TopOrganizationAuthority;

public class AuthorityDao extends EntityDao<Authority> implements IAuthorityDao {
	@Autowired
	private ITopOrganizationAuthorityDao topOrganizationAuthorityDao;

	@SuppressWarnings("unchecked")
	@Override
	public void delete(Authority t) {
		List<TopOrganizationAuthority> tops = (List<TopOrganizationAuthority>) getCurrentSession()
				.createCriteria(TopOrganizationAuthority.class)
				.add(Restrictions.eq("authority", t)).list();
		topOrganizationAuthorityDao.delete(tops);
		super.delete(t);
	}
}
