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
import com.voole.parrot.shared.condition.QueryCondition;
import com.voole.parrot.shared.grid.GwtListLoadConfigBean;
import com.voole.parrot.shared.grid.GwtPagingLoadConfigBean;

@Service
@Transactional
public class SimpleServiceImpl implements SimpleService {
	@Autowired
	private ISimpleDao simpleDao;

	@Override
	public <E extends Serializable> E create(E e) {
		return simpleDao.create(e);
	}

	@Override
	public <E extends Serializable, C extends Collection<E>> C create(C list) {
		return simpleDao.create(list);
	}

	@Override
	public <E extends Serializable> E update(E e) {
		return simpleDao.update(e);
	}

	@Override
	public <E extends Serializable, C extends Collection<E>> C update(C list) {
		return simpleDao.update(list);
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
	public <E extends Serializable> List<E> list(Class<E> clazz) {
		return simpleDao.list(clazz);
	}

	@Override
	public <E extends Serializable, Condition extends QueryCondition> List<E> list(
			Class<E> clazz, Condition condition,
			QueryConditionAnalyzer<Condition> conditionAnalyzer) {
		return simpleDao.list(clazz, condition, conditionAnalyzer);
	}

	@Override
	public <E extends Serializable> E get(E e) {
		return simpleDao.get(e);
	}

	@Override
	public <E extends Serializable, Condition extends QueryCondition> ListLoadResult<E> list(
			GwtListLoadConfigBean<Condition> configBean, Class<E> clazz,
			QueryConditionAnalyzer<Condition> conditionAnalyzer) {
		return simpleDao.list(configBean, clazz, conditionAnalyzer);
	}

	@Override
	public <E extends Serializable, Condition extends QueryCondition> ListLoadResult<E> list(
			GwtListLoadConfigBean<Condition> configBean, Class<E> clazz) {
		return simpleDao.list(configBean, clazz);
	}

	@Override
	public <E extends Serializable, Condition extends QueryCondition> PagingLoadResult<E> paging(
			GwtPagingLoadConfigBean<Condition> configBean, Class<E> clazz,
			QueryConditionAnalyzer<Condition> conditionAnalyzer) {
		return simpleDao.paging(configBean, clazz, conditionAnalyzer);
	}

	@Override
	public <E extends Serializable, Condition extends QueryCondition> PagingLoadResult<E> paging(
			GwtPagingLoadConfigBean<Condition> configBean, Class<E> clazz) {
		return simpleDao.paging(configBean, clazz);
	}

}
