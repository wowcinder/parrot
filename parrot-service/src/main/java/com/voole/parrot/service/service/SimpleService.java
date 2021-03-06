package com.voole.parrot.service.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.sencha.gxt.data.shared.loader.ListLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.voole.parrot.service.dao.EntityUpdater;
import com.voole.parrot.service.dao.ISimpleDao.QueryConditionAnalyzer;
import com.voole.parrot.shared.condition.QueryCondition;
import com.voole.parrot.shared.grid.GwtListLoadConfigBean;
import com.voole.parrot.shared.grid.GwtPagingLoadConfigBean;

public interface SimpleService {
	public <E extends Serializable> E persist(E e);

	public <E extends Serializable, C extends Collection<E>> C persist(C list);

	public <E extends Serializable> E update(E e, EntityUpdater<E> updater);

	public <E extends Serializable, C extends Collection<E>> C update(C list,
			EntityUpdater<E> updater);

	public <E extends Serializable> void delete(E e);

	public <E extends Serializable> void delete(Collection<E> list);

	public <E extends Serializable> List<E> list(Class<E> clazz);

	public <E extends Serializable, Condition extends QueryCondition> List<E> list(
			Class<E> clazz, Condition condition,
			QueryConditionAnalyzer<Condition> conditionAnalyzer);

	public <E extends Serializable> E get(E e);

	public <E extends Serializable, Condition extends QueryCondition> ListLoadResult<E> list(
			GwtListLoadConfigBean<Condition> configBean, Class<E> clazz,
			QueryConditionAnalyzer<Condition> conditionAnalyzer);

	public <E extends Serializable, Condition extends QueryCondition> ListLoadResult<E> list(
			GwtListLoadConfigBean<Condition> configBean, Class<E> clazz);

	public <E extends Serializable, Condition extends QueryCondition> PagingLoadResult<E> paging(
			GwtPagingLoadConfigBean<Condition> configBean, Class<E> clazz,
			QueryConditionAnalyzer<Condition> conditionAnalyzer);

	public <E extends Serializable, Condition extends QueryCondition> PagingLoadResult<E> paging(
			GwtPagingLoadConfigBean<Condition> configBean, Class<E> clazz);

}
