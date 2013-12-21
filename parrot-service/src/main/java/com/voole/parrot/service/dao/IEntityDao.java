package com.voole.parrot.service.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.sencha.gxt.data.shared.loader.ListLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.voole.parrot.service.dao.ISimpleDao.QueryConditionAnalyzer;
import com.voole.parrot.shared.grid.GwtListLoadConfigBean;
import com.voole.parrot.shared.grid.GwtPagingLoadConfigBean;
import com.voole.parrot.shared.grid.QueryCondition;

public interface IEntityDao<E extends Serializable> extends IBaseDao {
	public E persist(E e);

	public <C extends Collection<E>> C persist(C list);

	public void delete(E e);

	public void delete(Collection<E> list);

	public List<E> get();

	public E get(E e);

	public <Condition extends QueryCondition> ListLoadResult<E> list(
			GwtListLoadConfigBean<Condition> condition,
			QueryConditionAnalyzer<Condition> conditionAnalyzer);

	public <Condition extends QueryCondition> ListLoadResult<E> list(
			GwtListLoadConfigBean<Condition> condition);

	public <Condition extends QueryCondition> PagingLoadResult<E> paging(
			GwtPagingLoadConfigBean<Condition> condition,
			QueryConditionAnalyzer<Condition> conditionAnalyzer);

	public <Condition extends QueryCondition> PagingLoadResult<E> paging(
			GwtPagingLoadConfigBean<Condition> condition);

	public ISimpleDao getSimpleDao();

	public Class<E> getRawType();
}
