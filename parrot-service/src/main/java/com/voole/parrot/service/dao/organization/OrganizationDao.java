package com.voole.parrot.service.dao.organization;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.voole.parrot.service.dao.EntityDao;
import com.voole.parrot.shared.authority.Role;
import com.voole.parrot.shared.organization.Leader;
import com.voole.parrot.shared.organization.Member;
import com.voole.parrot.shared.organization.Organization;
import com.voole.parrot.shared.organization.SubOrganization;
import com.voole.parrot.shared.organization.TopOrganization;
import com.voole.parrot.shared.organization.TopOrganizationAuthority;

public class OrganizationDao extends EntityDao<Organization> implements
		IOrganizationDao {
	@Autowired
	private ILeaderDao leaderDao;
	@Autowired
	private IMemberDao memberDao;
	@Autowired
	private IRoleDao roleDao;
	@Autowired
	private ITopOrganizationAuthorityDao topOrganizationAuthorityDao;

	@SuppressWarnings("unchecked")
	@Override
	public void delete(Organization t) {
		List<Leader> leaders = getCurrentSession().createCriteria(Leader.class)
				.add(Restrictions.eq("organization", t)).list();
		leaderDao.delete(leaders);

		List<Member> members = getCurrentSession().createCriteria(Member.class)
				.add(Restrictions.eq("organization", t)).list();
		memberDao.delete(members);

		List<Role> roles = getCurrentSession().createCriteria(Role.class)
				.add(Restrictions.eq("organization", t)).list();
		roleDao.delete(roles);

		if (t instanceof TopOrganization) {
			TopOrganization top = (TopOrganization) t;
			List<TopOrganizationAuthority> authorities = getCurrentSession()
					.createCriteria(TopOrganizationAuthority.class)
					.add(Restrictions.eq("organization", top)).list();
			topOrganizationAuthorityDao.delete(authorities);
		}

		List<Organization> subs = getCurrentSession()
				.createCriteria(SubOrganization.class)
				.add(Restrictions.eq("parentOrganization", t)).list();
		delete(subs);

		super.delete(t);
	}
}
