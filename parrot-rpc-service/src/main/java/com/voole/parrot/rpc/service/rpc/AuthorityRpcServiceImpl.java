package com.voole.parrot.rpc.service.rpc;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.voole.parrot.gwt.common.shared.rpcservice.AuthorityRpcService;
import com.voole.parrot.service.service.authority.AuthorityEntranceService;
import com.voole.parrot.service.service.authority.AuthorityService;
import com.voole.parrot.shared.condition.QueryCondition;
import com.voole.parrot.shared.entity.authority.Authority;
import com.voole.parrot.shared.entity.authority.AuthorityEntrance;
import com.voole.parrot.shared.exception.SharedException;
import com.voole.parrot.shared.grid.GwtPagingLoadConfigBean;

@Service
@Transactional
public class AuthorityRpcServiceImpl implements AuthorityRpcService {
	@Autowired
	private AuthorityService authorityService;
	@Autowired
	private AuthorityEntranceService entranceService;

	@Override
	public List<AuthorityEntrance> getEntrances() throws SharedException {
		List<AuthorityEntrance> entrances = entranceService.list();
		for (AuthorityEntrance entrance : entrances) {
			Hibernate.initialize(entrance.getAuthorities());
		}
		return entrances;
	}

	@Override
	public Set<Authority> getDependencies(Authority authority)
			throws SharedException {
		authority = authorityService.get(authority);
		Hibernate.initialize(authority.getDependencies());
		Set<Authority> authorities = new HashSet<Authority>();
		authorities.addAll(authority.getDependencies());
		return authorities;
	}

	@Override
	public void setDependencies(Authority authority,
			final List<Authority> dependencies) throws SharedException {
		authority = authorityService.get(authority);
		authority.setDependencies(new HashSet<Authority>());
		authorityService.getEntityDao().getCurrSession().flush();

		List<Authority> dependencies2 = new ArrayList<Authority>();
		for (Authority authority2 : dependencies) {
			authority2 = authorityService.getEntityDao().refresh(authority2);
			dependencies2.add(authority2);
		}
		authority.getDependencies().addAll(dependencies2);
		authorityService.getEntityDao().create(authority);
	}

	@Override
	public <Condition extends QueryCondition> PagingLoadResult<Authority> paging(
			GwtPagingLoadConfigBean<Condition> condition) {
		return authorityService.paging(condition);
	}

}
