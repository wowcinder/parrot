package com.voole.parrot.service.dao.authority;

import org.springframework.stereotype.Repository;

import com.voole.parrot.service.dao.EntityDao;
import com.voole.parrot.shared.entity.authority.Authority;
import com.voole.parrot.shared.entity.authority.AuthorityEntrance;

@Repository
public class AuthorityDao extends EntityDao<Authority> implements IAuthorityDao {
	@Override
	public Authority save(Authority t) {
		AuthorityEntrance entrance = t.getEntrance();
		refresh(entrance);
		if (t.getPos() != null && entrance.getAuthorities().size() > t.getPos()) {
			entrance.getAuthorities().add(t.getPos(), t);

		} else {
			entrance.getAuthorities().add(t);
		}
		persist(entrance);
		return t;
	}
}
