package com.voole.parrot.service.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.engine.SessionImplementor;

import com.sencha.gxt.data.shared.SortDir;
import com.sencha.gxt.data.shared.SortInfoBean;
import com.sencha.gxt.data.shared.loader.ListLoadConfigBean;

public abstract class BaseDao implements IBaseDao {
	@Resource(name = "parrotSf")
	private SessionFactory sessionFactory;

	@Override
	public Session getCurrSession() {
		return getSessionFactory().getCurrentSession();
	}

	public void flush() {
		getCurrSession().flush();
		getCurrSession().clear();
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <E extends Serializable> E refresh(E e, boolean force) {
		Session session = getCurrSession();
		if (force || !session.contains(e)) {
			Serializable id = session.getSessionFactory()
					.getClassMetadata(e.getClass()).getIdentifier(e, (SessionImplementor)session);
			e = (E) session.load(e.getClass(), id);
			return e;
		}
		return e;
	}

	@Override
	public <E extends Serializable> E refresh(E e) {
		return refresh(e, false);
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

	protected int getTotalLength(Criteria criteria) {
		long rowCount = (Long) criteria.setProjection(Projections.rowCount())
				.uniqueResult();
		criteria.setProjection(null);
		return (int) rowCount;
	}

}
