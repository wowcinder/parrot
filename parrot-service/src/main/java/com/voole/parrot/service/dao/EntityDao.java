package com.voole.parrot.service.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public abstract class EntityDao<T extends Serializable> implements
		IEntityDao<T> {
	private SessionFactory sessionFactory;
	private final Class<T> innerClass;

	@SuppressWarnings("unchecked")
	public EntityDao() {
		ParameterizedType pt = (ParameterizedType) this.getClass()
				.getGenericSuperclass();
		innerClass = (Class<T>) pt.getActualTypeArguments()[0];
	}

	public void flush() {
		getCurrentSession().flush();
		getCurrentSession().clear();
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Class<T> getInnerClass() {
		return innerClass;
	}

	public Session getCurrentSession() {
		return getSessionFactory().getCurrentSession();
	}

	public T save(T t) {
		getCurrentSession().save(t);
		return t;
	}

	public <P extends Collection<T>> P save(P p) {
		for (T t : p) {
			save(t);
		}
		return p;
	}

	public void delete(T t) {
		getCurrentSession().delete(t);
	}

	public void delete(Collection<T> p) {
		for (T t : p) {
			delete(t);
		}
	}

}
