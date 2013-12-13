package com.voole.parrot.service.dao.authority;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.voole.parrot.service.dao.EntityDao;
import com.voole.parrot.shared.authority.Authority;
import com.voole.parrot.shared.authority.AuthorityEntrance;

public class AuthorityEntranceDao extends EntityDao<AuthorityEntrance>
		implements IAuthorityEntranceDao {
	@Autowired
	private IAuthorityDao authorityDao;

	@SuppressWarnings("unchecked")
	@Override
	public void delete(AuthorityEntrance t) {
		List<Authority> authorities = getCurrentSession()
				.createCriteria(Authority.class)
				.add(Restrictions.eq("entrance", t)).list();
		authorityDao.delete(authorities);
		flush();
		super.delete(t);
	}

}
