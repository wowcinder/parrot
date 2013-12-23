package com.voole.parrot.service.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.reflect.TypeToken;
import com.sencha.gxt.data.shared.loader.ListLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.voole.parrot.service.dao.ISimpleDao.QueryConditionAnalyzer;
import com.voole.parrot.shared.condition.QueryCondition;
import com.voole.parrot.shared.grid.GwtListLoadConfigBean;
import com.voole.parrot.shared.grid.GwtPagingLoadConfigBean;

public abstract class EntityDao<E extends Serializable> extends BaseDao
		implements IEntityDao<E> {

	@SuppressWarnings("serial")
	protected TypeToken<E> typeToken = new TypeToken<E>(getClass()) {
	};

	@Autowired
	private ISimpleDao simpleDao;

	@Override
	public E create(E e) {
		return simpleDao.create(e);
	}

	@Override
	public <C extends Collection<E>> C create(C list) {
		for (E e : list) {
			create(e);
		}
		return list;
	}

	@Override
	public void delete(E e) {
		simpleDao.delete(e);
	}

	@Override
	public void delete(Collection<E> list) {
		for (E e : list) {
			delete(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<E> list() {
		return simpleDao.list((Class<E>) typeToken.getRawType());
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

	@Override
	public E update(E e) {
		return simpleDao.update(e);
	}

	@Override
	public <C extends Collection<E>> C update(C list) {
		for (E e : list) {
			update(e);
		}
		return list;
	}

	@Override
	public <Condition extends QueryCondition> List<E> list(Condition condition,
			QueryConditionAnalyzer<Condition> conditionAnalyzer) {
		return simpleDao.list(getRawType(), condition, conditionAnalyzer);
	}
}
