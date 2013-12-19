package com.voole.parrot.service.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
		Type type = pt.getActualTypeArguments()[0];
		if (type instanceof TypeVariable) {
			TypeVariable<?> typeVariable = (TypeVariable<?>) type;
			innerClass = (Class<T>) typeVariable.getBounds()[0];
		} else {
			innerClass = (Class<T>) pt.getActualTypeArguments()[0];
		}
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

	public <C extends Serializable> C persist(C t) {
		getCurrSession().persist(t);
		return t;
	}

	public <C extends Serializable, P extends Collection<C>> P persist(P p) {
		for (C t : p) {
			persist(t);
		}
		return p;
	}

	public <C extends Serializable> void delete(C t) {
		getCurrSession().refresh(t);
		getCurrSession().delete(t);
	}

	public <C extends Serializable> void delete(Collection<C> p) {
		for (C t : p) {
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
		addOrder(criteria, condition);
		criteria.setFirstResult(condition.getOffset());
		criteria.setMaxResults(condition.getLimit());
		criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
		List<T> list = (List<T>) criteria.list();

		long rowCount = getTotalLength(criteria);
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
		createAlias(criteria, sortInfos);
		for (SortInfoBean sortInfo : sortInfos) {
			criteria.addOrder(getOrder(sortInfo));
		}
	}

	protected void createAlias(Criteria criteria, List<SortInfoBean> sortInfos) {
		Set<String> aliases = new HashSet<String>();
		for (SortInfoBean sortInfo : sortInfos) {
			String propertyName = sortInfo.getSortField();
			List<String> paths = getAliases(propertyName);
			for (String path : paths) {
				if (!aliases.contains(path)) {
					criteria.createAlias(path, path, Criteria.LEFT_JOIN);
					aliases.add(path);
				}

			}
		}
	}

	protected List<String> getAliases(String path) {
		List<String> aliases = new ArrayList<String>();
		if (path.indexOf(".") != -1) {
			String[] paths = path.split("\\.");
			int len = paths.length;
			String prev = null;
			for (int i = 0; i < len - 1; i++) {
				String itemPath = paths[i];
				if (i == 0) {
					aliases.add(itemPath);
				} else {
					aliases.add(prev + "." + itemPath);
				}
				prev = itemPath;
			}
		}
		return aliases;
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
