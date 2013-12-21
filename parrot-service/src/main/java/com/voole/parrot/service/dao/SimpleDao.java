package com.voole.parrot.service.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaSpecification;
import org.springframework.stereotype.Repository;

import com.sencha.gxt.data.shared.loader.ListLoadResult;
import com.sencha.gxt.data.shared.loader.ListLoadResultBean;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoadResultBean;
import com.voole.parrot.shared.grid.GwtListLoadConfigBean;
import com.voole.parrot.shared.grid.GwtPagingLoadConfigBean;
import com.voole.parrot.shared.grid.QueryCondition;

@Repository
public class SimpleDao extends BaseDao implements ISimpleDao {

	@Override
	public <E extends Serializable> E persist(E e) {
		getCurrSession().persist(e);
		return e;
	}

	@Override
	public <E extends Serializable, C extends Collection<E>> C persist(C list) {
		for (E e : list) {
			persist(e);
		}
		return list;
	}

	@Override
	public <E extends Serializable> void delete(E e) {
		e = refresh(e);
		getCurrSession().delete(e);
	}

	@Override
	public <E extends Serializable> void delete(Collection<E> list) {
		for (E e : list) {
			delete(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public <E extends Serializable> List<E> get(Class<E> clazz) {
		return (List<E>) getCurrSession().createCriteria(clazz).list();
	}

	@Override
	public <E extends Serializable> E get(E e) {
		e = refresh(e);
		return e;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <E extends Serializable, Condition extends QueryCondition> ListLoadResult<E> list(
			GwtListLoadConfigBean<Condition> condition, Class<E> clazz,
			QueryConditionAnalyzer<Condition> conditionAnalyzer) {
		Criteria criteria = getCurrSession().createCriteria(clazz);
		conditionAnalyzer.analyze(criteria, condition.getCondition());
		addOrder(criteria, condition);
		criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
		return new ListLoadResultBean<E>((List<E>) criteria.list());
	}

	@Override
	public <E extends Serializable, Condition extends QueryCondition> ListLoadResult<E> list(
			GwtListLoadConfigBean<Condition> condition, Class<E> clazz) {
		return list(condition, clazz,
				new EmptyQueryConditionAnalyzer<Condition>());
	}

	@SuppressWarnings("unchecked")
	@Override
	public <E extends Serializable, Condition extends QueryCondition> PagingLoadResult<E> paging(
			GwtPagingLoadConfigBean<Condition> condition, Class<E> clazz,
			QueryConditionAnalyzer<Condition> conditionAnalyzer) {
		Criteria criteria = getCurrSession().createCriteria(clazz);
		conditionAnalyzer.analyze(criteria, condition.getCondition());
		addOrder(criteria, condition);
		criteria.setFirstResult(condition.getOffset());
		criteria.setMaxResults(condition.getLimit());
		criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
		List<E> list = (List<E>) criteria.list();

		long rowCount = getTotalLength(criteria);
		PagingLoadResultBean<E> result = new PagingLoadResultBean<E>();
		result.setOffset(condition.getOffset());
		result.setTotalLength((int) rowCount);
		result.setData(list);
		return result;
	}

	@Override
	public <E extends Serializable, Condition extends QueryCondition> PagingLoadResult<E> paging(
			GwtPagingLoadConfigBean<Condition> condition, Class<E> clazz) {
		return paging(condition, clazz,
				new EmptyQueryConditionAnalyzer<Condition>());
	}

}
