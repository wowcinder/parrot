package com.voole.parrot.gwt.common.shared.rpcservice;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.sencha.gxt.data.shared.loader.ListLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.voole.parrot.shared.condition.QueryCondition;
import com.voole.parrot.shared.exception.SharedException;
import com.voole.parrot.shared.grid.GwtListLoadConfigBean;
import com.voole.parrot.shared.grid.GwtPagingLoadConfigBean;

public interface EntityRpcService<E extends Serializable> {
	public E persist(E e) throws SharedException;

	public Collection<E> persist(Collection<E> list) throws SharedException;

	public void delete(E e) throws SharedException;

	public void delete(Collection<E> list) throws SharedException;

	public List<E> list() throws SharedException;

	public <Condition extends QueryCondition> List<E> list(Condition condition)
			throws SharedException;

	public E update(E e) throws SharedException;

	public <C extends Collection<E>> C update(C list) throws SharedException;

	public E get(E e) throws SharedException;

	public <Condition extends QueryCondition> ListLoadResult<E> list(
			GwtListLoadConfigBean<Condition> condition) throws SharedException;

	public <Condition extends QueryCondition> PagingLoadResult<E> paging(
			GwtPagingLoadConfigBean<Condition> condition)
			throws SharedException;

}
