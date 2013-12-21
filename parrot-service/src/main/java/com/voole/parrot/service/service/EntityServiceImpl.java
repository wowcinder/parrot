package com.voole.parrot.service.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.sencha.gxt.data.shared.loader.ListLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.voole.parrot.service.dao.IEntityDao;
import com.voole.parrot.service.dao.ISimpleDao.QueryConditionAnalyzer;
import com.voole.parrot.shared.grid.GwtListLoadConfigBean;
import com.voole.parrot.shared.grid.GwtPagingLoadConfigBean;
import com.voole.parrot.shared.grid.QueryCondition;

public abstract class EntityServiceImpl<E extends Serializable> implements
		EntityService<E> {

	public abstract IEntityDao<E> getEntityDao();

	@Override
	public E persist(E e) {
		return getEntityDao().persist(e);
	}

	@Override
	public <C extends Collection<E>> C persist(C list) {
		return getEntityDao().persist(list);
	}

	@Override
	public void delete(E e) {
		getEntityDao().delete(e);
	}

	@Override
	public void delete(Collection<E> list) {
		getEntityDao().delete(list);
	}

	@Override
	public List<E> get() {
		return getEntityDao().get();
	}

	@Override
	public E get(E e) {
		return getEntityDao().get(e);
	}

	@Override
	public <Condition extends QueryCondition> ListLoadResult<E> list(
			GwtListLoadConfigBean<Condition> condition,
			QueryConditionAnalyzer<Condition> conditionAnalyzer) {
		return getEntityDao().list(condition, conditionAnalyzer);
	}

	@Override
	public <Condition extends QueryCondition> ListLoadResult<E> list(
			GwtListLoadConfigBean<Condition> condition) {
		return getEntityDao().list(condition);
	}

	@Override
	public <Condition extends QueryCondition> PagingLoadResult<E> paging(
			GwtPagingLoadConfigBean<Condition> condition,
			QueryConditionAnalyzer<Condition> conditionAnalyzer) {
		return getEntityDao().paging(condition, conditionAnalyzer);
	}

	@Override
	public <Condition extends QueryCondition> PagingLoadResult<E> paging(
			GwtPagingLoadConfigBean<Condition> condition) {
		return getEntityDao().paging(condition);
	}

	public Class<E> getRawType() {
		return getEntityDao().getRawType();
	}
}
