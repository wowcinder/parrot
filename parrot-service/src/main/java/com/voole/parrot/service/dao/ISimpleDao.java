package com.voole.parrot.service.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.hibernate.Criteria;

import com.sencha.gxt.data.shared.loader.ListLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.voole.parrot.shared.grid.GwtListLoadConfigBean;
import com.voole.parrot.shared.grid.GwtPagingLoadConfigBean;
import com.voole.parrot.shared.grid.QueryCondition;

public interface ISimpleDao extends IBaseDao {
	public <E extends Serializable> E persist(E e);

	public <E extends Serializable, C extends Collection<E>> C persist(C list);

	public <E extends Serializable> void delete(E e);

	public <E extends Serializable> void delete(Collection<E> list);

	public <E extends Serializable> List<E> get(Class<E> clazz);

	public <E extends Serializable> E get(E e);

	public <E extends Serializable, Condition extends QueryCondition> ListLoadResult<E> list(
			GwtListLoadConfigBean<Condition> condition, Class<E> clazz,
			QueryConditionAnalyzer<Condition> conditionAnalyzer);

	public <E extends Serializable, Condition extends QueryCondition> ListLoadResult<E> list(
			GwtListLoadConfigBean<Condition> condition, Class<E> clazz);

	public <E extends Serializable, Condition extends QueryCondition> PagingLoadResult<E> paging(
			GwtPagingLoadConfigBean<Condition> condition, Class<E> clazz,
			QueryConditionAnalyzer<Condition> conditionAnalyzer);

	public <E extends Serializable, Condition extends QueryCondition> PagingLoadResult<E> paging(
			GwtPagingLoadConfigBean<Condition> condition, Class<E> clazz);

	public interface QueryConditionAnalyzer<Condition extends QueryCondition> {
		public void analyze(Criteria criteria, Condition condition);
	}

}
