package com.voole.parrot.gwt.common.shared.rpcservice;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.sencha.gxt.data.shared.loader.ListLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.voole.parrot.shared.condition.QueryCondition;
import com.voole.parrot.shared.grid.GwtListLoadConfigBean;
import com.voole.parrot.shared.grid.GwtPagingLoadConfigBean;

public interface EntityRpcService<E extends Serializable> {
	public E persist(E e);

	public Collection<E> persist(Collection<E> list);

	public void delete(E e);

	public void delete(Collection<E> list);

	public List<E> get();

	public E get(E e);

	public <Condition extends QueryCondition> ListLoadResult<E> list(
			GwtListLoadConfigBean<Condition> condition);

	public <Condition extends QueryCondition> PagingLoadResult<E> paging(
			GwtPagingLoadConfigBean<Condition> condition);

}
