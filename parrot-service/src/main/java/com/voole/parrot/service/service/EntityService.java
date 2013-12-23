package com.voole.parrot.service.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.sencha.gxt.data.shared.loader.ListLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.voole.parrot.service.dao.IEntityDao;
import com.voole.parrot.service.dao.ISimpleDao.QueryConditionAnalyzer;
import com.voole.parrot.shared.condition.QueryCondition;
import com.voole.parrot.shared.grid.GwtListLoadConfigBean;
import com.voole.parrot.shared.grid.GwtPagingLoadConfigBean;

public interface EntityService<E extends Serializable> {
	public  IEntityDao<E> getEntityDao();
	
	public E create(E e);

	public <C extends Collection<E>> C create(C list);

	public E update(E e);

	public <C extends Collection<E>> C update(C list);

	public void delete(E e);

	public void delete(Collection<E> list);

	public E get(E e);

	public List<E> list();

	public <Condition extends QueryCondition> List<E> list(Condition condition,
			QueryConditionAnalyzer<Condition> conditionAnalyzer);

	public <Condition extends QueryCondition> ListLoadResult<E> list(
			GwtListLoadConfigBean<Condition> configBean,
			QueryConditionAnalyzer<Condition> conditionAnalyzer);

	public <Condition extends QueryCondition> ListLoadResult<E> list(
			GwtListLoadConfigBean<Condition> configBean);

	public <Condition extends QueryCondition> PagingLoadResult<E> paging(
			GwtPagingLoadConfigBean<Condition> configBean,
			QueryConditionAnalyzer<Condition> conditionAnalyzer);

	public <Condition extends QueryCondition> PagingLoadResult<E> paging(
			GwtPagingLoadConfigBean<Condition> configBean);

	public Class<E> getRawType();
}
