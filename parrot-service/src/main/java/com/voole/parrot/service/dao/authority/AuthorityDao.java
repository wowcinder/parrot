package com.voole.parrot.service.dao.authority;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.voole.parrot.service.dao.EntityDao;
import com.voole.parrot.shared.authority.Authority;
import com.voole.parrot.shared.authority.AuthorityEntrance;

@Repository
public class AuthorityDao extends EntityDao<Authority> implements IAuthorityDao {
	@Autowired
	private AuthorityEntranceDao entranceDao;

	@Override
	public Authority persist(Authority t) {
		AuthorityEntrance entrance = t.getEntrance();
		entrance = em.merge(entrance);
		t.setEntrance(entrance);
		if (entrance.getAuthorities() == null) {
			entrance.setAuthorities(new ArrayList<Authority>());
		}
		if (t.getPos() == null) {
			entrance.getAuthorities().add(t);
		} else {
			entrance.getAuthorities().add(t.getPos(), t);
		}
		entranceDao.persist(entrance);
		return t;
	}
}
