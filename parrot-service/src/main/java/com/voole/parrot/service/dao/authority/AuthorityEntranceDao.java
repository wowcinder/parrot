package com.voole.parrot.service.dao.authority;

import org.springframework.stereotype.Repository;

import com.voole.parrot.service.dao.EntityDao;
import com.voole.parrot.shared.entity.authority.AuthorityEntrance;

@Repository
public class AuthorityEntranceDao extends EntityDao<AuthorityEntrance>
		implements IAuthorityEntranceDao {
	@Override
	public AuthorityEntrance changeName(AuthorityEntrance e) {
		AuthorityEntrance old = refresh(e);
		old.setName(e.getName());
		return super.persist(old);
	}
}
