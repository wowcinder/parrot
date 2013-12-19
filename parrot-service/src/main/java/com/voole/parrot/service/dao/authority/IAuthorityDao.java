package com.voole.parrot.service.dao.authority;

import com.voole.parrot.service.dao.IEntityDao;
import com.voole.parrot.shared.entity.authority.Authority;

public interface IAuthorityDao extends IEntityDao<Authority> {
	public Authority save(Authority t);
}
