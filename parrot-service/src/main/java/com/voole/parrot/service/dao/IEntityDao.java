package com.voole.parrot.service.dao;

import java.io.Serializable;
import java.util.Collection;

public interface IEntityDao<T extends Serializable> {
	public T save(T t);

	public <P extends Collection<T>> P save(P p);

	public void delete(T t);

	public void delete(Collection<T> p);
}
