package com.voole.parrot.service.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;

import com.sencha.gxt.data.shared.SortDir;
import com.sencha.gxt.data.shared.SortInfoBean;
import com.sencha.gxt.data.shared.loader.ListLoadConfigBean;
import com.sencha.gxt.data.shared.loader.ListLoadResult;
import com.sencha.gxt.data.shared.loader.ListLoadResultBean;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoadResultBean;
import com.voole.parrot.shared.grid.GwtListLoadConfigBean;
import com.voole.parrot.shared.grid.GwtPagingLoadConfigBean;
import com.voole.parrot.shared.grid.QueryCondition;

public abstract class EntityDao<T extends Serializable> implements
		IEntityDao<T> {
	private final Class<T> innerClass;
	@Resource(name = "parrotSf")
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public EntityDao() {
		ParameterizedType pt = (ParameterizedType) this.getClass()
				.getGenericSuperclass();
		innerClass = (Class<T>) pt.getActualTypeArguments()[0];
	}

	public void flush() {
		getCurrSession().flush();
		getCurrSession().clear();
	}

	public <P> void refresh(P p) {
		getCurrSession().refresh(p);
	}

	public Session getCurrSession() {
		return getSessionFactory().getCurrentSession();
	}

	public Class<T> getInnerClass() {
		return innerClass;
	}

	public T persist(T t) {
		getCurrSession().persist(t);
		return t;
	}

	public <P extends Collection<T>> P persist(P p) {
		for (T t : p) {
			persist(t);
		}
		return p;
	}

	public void delete(T t) {
		getCurrSession().refresh(t);
		getCurrSession().delete(t);
	}

	public void delete(Collection<T> p) {
		for (T t : p) {
			delete(t);
		}
	}

	@SuppressWarnings("unchecked")
	public List<T> get() {
		return (List<T>) getCurrSession().createCriteria(getInnerClass())
				.list();

	}

	@SuppressWarnings("unchecked")
	public <Condition extends QueryCondition> ListLoadResult<T> list(
			GwtListLoadConfigBean<Condition> condition) {
		Criteria criteria = getCurrSession().createCriteria(getInnerClass());
		addOrder(criteria, condition);
		criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
		return new ListLoadResultBean<T>((List<T>) criteria.list());
	}

	@SuppressWarnings("unchecked")
	public <Condition extends QueryCondition> PagingLoadResult<T> paging(
			GwtPagingLoadConfigBean<Condition> condition) {
		Criteria criteria = getCurrSession().createCriteria(getInnerClass());
		long rowCount = getTotalLength(criteria);
		addOrder(criteria, condition);
		criteria.setFirstResult(condition.getOffset());
		criteria.setMaxResults(condition.getLimit());
		criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
		List<T> list = (List<T>) criteria.list();

		PagingLoadResultBean<T> result = new PagingLoadResultBean<T>();
		result.setOffset(condition.getOffset());
		result.setTotalLength((int) rowCount);
		result.setData(list);
		return result;
	}

	private int getTotalLength(Criteria criteria) {
		long rowCount = (Long) criteria.setProjection(Projections.rowCount())
				.uniqueResult();
		criteria.setProjection(null);
		return (int) rowCount;
	}

	protected void addOrder(Criteria criteria, ListLoadConfigBean condition) {
		List<SortInfoBean> sortInfos = condition.getSortInfo();
		for (SortInfoBean sortInfo : sortInfos) {
			criteria.addOrder(getOrder(sortInfo));
		}
	}

	protected Order getOrder(SortInfoBean sortInfo) {
		String propertyName = sortInfo.getSortField();
		if (isAsc(sortInfo.getSortDir())) {
			return Order.asc(propertyName);
		} else {
			return Order.desc(propertyName);
		}
	}

	protected boolean isAsc(SortDir sortDir) {
		if (sortDir != null) {
			if (sortDir == SortDir.ASC) {
				return true;
			}
			return false;
		}
		return true;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
