package com.voole.parrot.service.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.hibernate.Criteria;

import com.sencha.gxt.data.shared.loader.ListLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.voole.parrot.service.dao.conditionprocessor.ConditionProcessor;
import com.voole.parrot.shared.condition.QueryCondition;
import com.voole.parrot.shared.grid.GwtListLoadConfigBean;
import com.voole.parrot.shared.grid.GwtPagingLoadConfigBean;

public interface ISimpleDao extends IBaseDao {
	public <E extends Serializable> E create(E e);

	public <E extends Serializable, C extends Collection<E>> C create(C list);

	public <E extends Serializable> E update(E e);

	public <E extends Serializable, C extends Collection<E>> C update(C list);

	public <E extends Serializable> void delete(E e);

	public <E extends Serializable> void delete(Collection<E> list);

	public <E extends Serializable> E get(E e);

	public <E extends Serializable> List<E> list(Class<E> clazz);

	public <E extends Serializable, Condition extends QueryCondition> List<E> list(
			Class<E> clazz, Condition condition,
			QueryConditionAnalyzer<Condition> conditionAnalyzer);

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
		public void analyze(Criteria criteria, Condition condition,
				ConditionProcessor<Condition> conditionProcessor);
	}

}
