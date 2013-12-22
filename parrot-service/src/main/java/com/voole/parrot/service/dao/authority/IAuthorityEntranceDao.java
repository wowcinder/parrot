package com.voole.parrot.service.dao.authority;

import com.voole.parrot.service.dao.IEntityDao;
import com.voole.parrot.shared.entity.authority.AuthorityEntrance;

public interface IAuthorityEntranceDao extends IEntityDao<AuthorityEntrance> {
	public AuthorityEntrance changeName(AuthorityEntrance e);
}
