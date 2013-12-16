package com.voole.parrot.service.dao;

import java.io.Serializable;
import java.util.Collection;

public interface IEntityDao<T extends Serializable> {
	public T persist(T t);

	public <P extends Collection<T>> P persist(P p);

	public void delete(T t);

	public void delete(Collection<T> p);
}
