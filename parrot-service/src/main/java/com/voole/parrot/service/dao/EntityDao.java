package com.voole.parrot.service.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.voole.parrot.shared.EntityWithOrderChildren;

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
		if (t instanceof EntityWithOrderChildren) {
			((EntityWithOrderChildren) t).sortChildren();
		}
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

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
