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
import com.voole.parrot.service.conditionprocessor.ConditionProcessor;
import com.voole.parrot.shared.condition.QueryCondition;
import com.voole.parrot.shared.grid.GwtListLoadConfigBean;
import com.voole.parrot.shared.grid.GwtPagingLoadConfigBean;

@Repository
public class SimpleDao extends BaseDao implements ISimpleDao {

	@Override
	public <E extends Serializable> E create(E e) {
		getCurrSession().persist(e);
		return e;
	}

	@Override
	public <E extends Serializable, C extends Collection<E>> C create(C list) {
		for (E e : list) {
			create(e);
		}
		return list;
	}

	@Override
	public <E extends Serializable> E update(E e) {
		getCurrSession().persist(e);
		return e;
	}

	@Override
	public <E extends Serializable, C extends Collection<E>> C update(C list) {
		for (E e : list) {
			update(e);
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

	@Override
	public <E extends Serializable> E get(E e) {
		e = refresh(e);
		return e;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <E extends Serializable> List<E> list(Class<E> clazz) {
		return (List<E>) getCurrSession().createCriteria(clazz)
				.setResultTransformer(CriteriaSpecification.ROOT_ENTITY).list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public <E extends Serializable, Condition extends QueryCondition> List<E> list(
			Class<E> clazz, Condition condition,
			QueryConditionAnalyzer<Condition> conditionAnalyzer) {
		Criteria criteria = getCurrSession().createCriteria(clazz);
		ConditionProcessor.process(criteria, condition, conditionAnalyzer);
		return (List<E>) criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <E extends Serializable, Condition extends QueryCondition> ListLoadResult<E> list(
			GwtListLoadConfigBean<Condition> configBean, Class<E> clazz,
			QueryConditionAnalyzer<Condition> conditionAnalyzer) {
		Criteria criteria = getCurrSession().createCriteria(clazz);
		ConditionProcessor.process(criteria, configBean, conditionAnalyzer);
		return new ListLoadResultBean<E>((List<E>) criteria.list());
	}

	@Override
	public <E extends Serializable, Condition extends QueryCondition> ListLoadResult<E> list(
			GwtListLoadConfigBean<Condition> configBean, Class<E> clazz) {
		return list(configBean, clazz,
				new EmptyQueryConditionAnalyzer<Condition>());
	}

	@SuppressWarnings("unchecked")
	@Override
	public <E extends Serializable, Condition extends QueryCondition> PagingLoadResult<E> paging(
			GwtPagingLoadConfigBean<Condition> configBean, Class<E> clazz,
			QueryConditionAnalyzer<Condition> conditionAnalyzer) {
		Criteria criteria = getCurrSession().createCriteria(clazz);
		ConditionProcessor.process(criteria, configBean, conditionAnalyzer);
		criteria.setFirstResult(configBean.getOffset());
		criteria.setMaxResults(configBean.getLimit());
		List<E> list = (List<E>) criteria.list();

		long rowCount = getTotalLength(criteria);
		PagingLoadResultBean<E> result = new PagingLoadResultBean<E>();
		result.setOffset(configBean.getOffset());
		result.setTotalLength((int) rowCount);
		result.setData(list);
		return result;
	}

	@Override
	public <E extends Serializable, Condition extends QueryCondition> PagingLoadResult<E> paging(
			GwtPagingLoadConfigBean<Condition> configBean, Class<E> clazz) {
		return paging(configBean, clazz,
				new EmptyQueryConditionAnalyzer<Condition>());
	}

}
