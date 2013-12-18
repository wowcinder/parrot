package com.voole.parrot.service.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.hibernate.Session;

import com.sencha.gxt.data.shared.loader.ListLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.voole.parrot.shared.grid.GwtListLoadConfigBean;
import com.voole.parrot.shared.grid.GwtPagingLoadConfigBean;
import com.voole.parrot.shared.grid.QueryCondition;

public interface IEntityDao<T extends Serializable> {
	public T persist(T t);

	public <P extends Collection<T>> P persist(P p);

	public void delete(T t);

	public void delete(Collection<T> p);

	public Session getCurrSession();

	public List<T> get();

	public <Condition extends QueryCondition> ListLoadResult<T> list(
			GwtListLoadConfigBean<Condition> condition);

	public <Condition extends QueryCondition> PagingLoadResult<T> paging(
			GwtPagingLoadConfigBean<Condition> condition);
}
