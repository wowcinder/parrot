package com.voole.parrot.service.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.engine.SessionImplementor;

public abstract class BaseDao implements IBaseDao {
	@Resource(name = "parrotSf")
	private SessionFactory sessionFactory;

	@Override
	public Session getCurrSession() {
		return getSessionFactory().getCurrentSession();
	}

	public void flush() {
		getCurrSession().flush();
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
					.getClassMetadata(e.getClass())
					.getIdentifier(e, (SessionImplementor) session);
			e = (E) session.get(e.getClass(), id);
			return e;
		}
		return e;
	}

	@Override
	public <E extends Serializable> E refresh(E e) {
		return refresh(e, false);
	}

	protected int getTotalLength(Criteria criteria) {
		long rowCount = (Long) criteria.setProjection(Projections.rowCount())
				.uniqueResult();
		criteria.setProjection(null);
		return (int) rowCount;
	}

	@Override
	public <E extends Serializable, C extends Collection<E>> void refresh(C list) {
		refresh(list, false);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <E extends Serializable, C extends Collection<E>> void refresh(
			C list, boolean force) {
		Session session = getCurrSession();
		List<E> list2 = new ArrayList<E>();
		for (E e : list) {
			if (force || !session.contains(e)) {
				Serializable id = session.getSessionFactory()
						.getClassMetadata(e.getClass())
						.getIdentifier(e, (SessionImplementor) session);
				E e2 = (E) session.get(e.getClass(), id);
				list2.add(e2);
			} else {
				list2.add(e);
			}
		}
		list.clear();
		list.addAll(list2);

	}

}
