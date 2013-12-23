package com.voole.parrot.rpc.service.rpc;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.voole.parrot.gwt.common.shared.rpcservice.AuthorityRpcService;
import com.voole.parrot.service.service.authority.AuthorityEntranceService;
import com.voole.parrot.service.service.authority.AuthorityService;
import com.voole.parrot.shared.entity.authority.Authority;
import com.voole.parrot.shared.entity.authority.AuthorityEntrance;
import com.voole.parrot.shared.exception.SharedException;

@Service
@Transactional
public class AuthorityRpcServiceImpl extends EntityRpcServiceImpl<Authority>
		implements AuthorityRpcService {
	@Autowired
	private AuthorityService authorityDao;
	@Autowired
	private AuthorityEntranceService simpleDao;

	@Override
	public Authority persist(Authority e) {
		return authorityDao.create(e);
	}

	@Override
	public List<AuthorityEntrance> getEntrances() throws SharedException {
		List<AuthorityEntrance> entrances = simpleDao.list();
		for (AuthorityEntrance entrance : entrances) {
			Hibernate.initialize(entrance.getAuthorities());
		}
		return entrances;
	}

	@Override
	public Set<Authority> getDependencies(Authority authority)
			throws SharedException {
		authority = authorityDao.get(authority);
		Hibernate.initialize(authority.getDependencies());
		Set<Authority> authorities = new HashSet<Authority>();
		authorities.addAll(authority.getDependencies());
		return authorities;
	}

	@Override
	public void setDependencies(Authority authority,
			final List<Authority> dependencies) throws SharedException {
		authority = authorityDao.get(authority);
		authority.setDependencies(new HashSet<Authority>());
		authorityDao.getEntityDao().getCurrSession().flush();

		List<Authority> dependencies2 = new ArrayList<Authority>();
		for (Authority authority2 : dependencies) {
			authority2 = authorityDao.getEntityDao().refresh(authority2);
			dependencies2.add(authority2);
		}
		authority.getDependencies().addAll(dependencies2);
		authorityDao.getEntityDao().create(authority);
	}

}
