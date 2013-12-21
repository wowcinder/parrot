package com.voole.parrot.service.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sencha.gxt.data.shared.loader.ListLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.voole.parrot.service.dao.ISimpleDao;
import com.voole.parrot.service.dao.ISimpleDao.QueryConditionAnalyzer;
import com.voole.parrot.shared.grid.GwtListLoadConfigBean;
import com.voole.parrot.shared.grid.GwtPagingLoadConfigBean;
import com.voole.parrot.shared.grid.QueryCondition;

@Service
@Transactional
public class SimpleServiceImpl implements SimpleService {
	@Autowired
	private ISimpleDao simpleDao;

	@Override
	public <E extends Serializable> E persist(E e) {
		return simpleDao.persist(e);
	}

	@Override
	public <E extends Serializable, C extends Collection<E>> C persist(C list) {
		return simpleDao.persist(list);
	}

	@Override
	public <E extends Serializable> void delete(E e) {
		simpleDao.delete(e);
	}

	@Override
	public <E extends Serializable> void delete(Collection<E> list) {
		simpleDao.delete(list);
	}

	@Override
	public <E extends Serializable> List<E> get(Class<E> clazz) {
		return simpleDao.get(clazz);
	}

	@Override
	public <E extends Serializable> E get(E e) {
		return simpleDao.get(e);
	}

	@Override
	public <E extends Serializable, Condition extends QueryCondition> ListLoadResult<E> list(
			GwtListLoadConfigBean<Condition> condition, Class<E> clazz,
			QueryConditionAnalyzer<Condition> conditionAnalyzer) {
		return simpleDao.list(condition, clazz, conditionAnalyzer);
	}

	@Override
	public <E extends Serializable, Condition extends QueryCondition> ListLoadResult<E> list(
			GwtListLoadConfigBean<Condition> condition, Class<E> clazz) {
		return simpleDao.list(condition, clazz);
	}

	@Override
	public <E extends Serializable, Condition extends QueryCondition> PagingLoadResult<E> paging(
			GwtPagingLoadConfigBean<Condition> condition, Class<E> clazz,
			QueryConditionAnalyzer<Condition> conditionAnalyzer) {
		return simpleDao.paging(condition, clazz, conditionAnalyzer);
	}

	@Override
	public <E extends Serializable, Condition extends QueryCondition> PagingLoadResult<E> paging(
			GwtPagingLoadConfigBean<Condition> condition, Class<E> clazz) {
		return simpleDao.paging(condition, clazz);
	}

}
