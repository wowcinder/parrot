package com.voole.parrot.service.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.reflect.TypeToken;
import com.sencha.gxt.data.shared.loader.ListLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.voole.parrot.service.dao.ISimpleDao.QueryConditionAnalyzer;
import com.voole.parrot.shared.grid.GwtListLoadConfigBean;
import com.voole.parrot.shared.grid.GwtPagingLoadConfigBean;
import com.voole.parrot.shared.grid.QueryCondition;

public class EntityDao<E extends Serializable> extends BaseDao implements
		IEntityDao<E> {

	@SuppressWarnings("serial")
	protected TypeToken<E> typeToken = new TypeToken<E>(getClass()) {
	};

	@Autowired
	private ISimpleDao simpleDao;

	@Override
	public E persist(E e) {
		return simpleDao.persist(e);
	}

	@Override
	public <C extends Collection<E>> C persist(C list) {
		return simpleDao.persist(list);
	}

	@Override
	public void delete(E e) {
		simpleDao.delete(e);
	}

	@Override
	public void delete(Collection<E> list) {
		simpleDao.delete(list);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<E> get() {
		return simpleDao.get((Class<E>) typeToken.getRawType());
	}

	@Override
	public E get(E e) {
		return simpleDao.get(e);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <Condition extends QueryCondition> ListLoadResult<E> list(
			GwtListLoadConfigBean<Condition> condition,
			QueryConditionAnalyzer<Condition> conditionAnalyzer) {
		return simpleDao.list(condition, (Class<E>) typeToken.getRawType(),
				conditionAnalyzer);
	}

	@Override
	public <Condition extends QueryCondition> ListLoadResult<E> list(
			GwtListLoadConfigBean<Condition> condition) {
		return list(condition, new EmptyQueryConditionAnalyzer<Condition>());
	}

	@SuppressWarnings("unchecked")
	@Override
	public <Condition extends QueryCondition> PagingLoadResult<E> paging(
			GwtPagingLoadConfigBean<Condition> condition,
			QueryConditionAnalyzer<Condition> conditionAnalyzer) {
		return simpleDao.paging(condition, (Class<E>) typeToken.getRawType(),
				conditionAnalyzer);
	}

	@Override
	public <Condition extends QueryCondition> PagingLoadResult<E> paging(
			GwtPagingLoadConfigBean<Condition> condition) {
		return paging(condition, new EmptyQueryConditionAnalyzer<Condition>());
	}

	@Override
	public ISimpleDao getSimpleDao() {
		return simpleDao;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Class<E> getRawType() {
		return (Class<E>) typeToken.getRawType();
	}
}
