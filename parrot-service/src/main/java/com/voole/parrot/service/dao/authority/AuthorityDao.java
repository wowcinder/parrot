package com.voole.parrot.service.dao.authority;

import org.springframework.stereotype.Repository;

import com.voole.parrot.service.dao.EntityDao;
import com.voole.parrot.service.dao.EntityUpdater;
import com.voole.parrot.shared.entity.authority.Authority;
import com.voole.parrot.shared.entity.authority.AuthorityEntrance;

@Repository
public class AuthorityDao extends EntityDao<Authority> implements IAuthorityDao {
	@Override
	public Authority persist(Authority t) {
		AuthorityEntrance entrance = findParent(t);
		t.setEntrance(entrance);
		if (t.getPos() != null && entrance.getAuthorities().size() > t.getPos()) {
			entrance.getAuthorities().add(t.getPos(), t);
		} else {
			entrance.getAuthorities().add(t);
		}
		getSimpleDao().persist(entrance);
		return t;
	}

	protected AuthorityEntrance findParent(Authority t) {
		if (t.getEntrance() == null) {
			throw new RuntimeException("entrance is empty");
		}
		AuthorityEntrance entrance = t.getEntrance();
		entrance = refresh(entrance);
		for (Authority authority : entrance.getAuthorities()) {
			authority.setEntrance(entrance);
		}
		return entrance;
	}

	@Override
	public void modifyAuthorityRoles(Authority authority) {
		update(authority, new EntityUpdater<Authority>() {

			@Override
			public void invoke(Authority old, Authority e) {
				old.setRoles(e.getRoles());
			}
		});
	}

	@Override
	public void modifyAuthorityUsers(Authority authority) {
		update(authority, new EntityUpdater<Authority>() {

			@Override
			public void invoke(Authority old, Authority e) {
				old.setUsers(e.getUsers());
			}
		});

	}

}
