package com.voole.parrot.service.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class EntityDao<T extends Serializable> implements
		IEntityDao<T> {
	private final Class<T> innerClass;
	@PersistenceContext(unitName = "parrotUnit")
	protected EntityManager em;

	@SuppressWarnings("unchecked")
	public EntityDao() {
		ParameterizedType pt = (ParameterizedType) this.getClass()
				.getGenericSuperclass();
		innerClass = (Class<T>) pt.getActualTypeArguments()[0];
	}

	public void flush() {
		em.flush();
		em.clear();
	}

	public Class<T> getInnerClass() {
		return innerClass;
	}

	public T save(T t) {
		em.persist(t);
		return t;
	}

	public <P extends Collection<T>> P save(P p) {
		for (T t : p) {
			save(t);
		}
		return p;
	}

	public void delete(T t) {
		t = em.merge(t);
		em.refresh(t);
		em.remove(t);
	}

	public void delete(Collection<T> p) {
		for (T t : p) {
			delete(t);
		}
	}

	public T update(T t) {
		em.merge(t);
		return t;
	}

	public <P extends Collection<T>> P update(P p) {
		for (T t : p) {
			update(t);
		}
		return p;
	}

}
