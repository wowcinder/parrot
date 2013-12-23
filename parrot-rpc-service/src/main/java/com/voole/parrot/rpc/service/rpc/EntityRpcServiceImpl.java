package com.voole.parrot.rpc.service.rpc;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.reflect.TypeToken;
import com.sencha.gxt.data.shared.loader.ListLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.voole.parrot.gwt.common.shared.rpcservice.EntityRpcService;
import com.voole.parrot.service.dao.EmptyQueryConditionAnalyzer;
import com.voole.parrot.service.service.SimpleService;
import com.voole.parrot.shared.condition.QueryCondition;
import com.voole.parrot.shared.exception.SharedException;
import com.voole.parrot.shared.grid.GwtListLoadConfigBean;
import com.voole.parrot.shared.grid.GwtPagingLoadConfigBean;

public abstract class EntityRpcServiceImpl<E extends Serializable> implements
		EntityRpcService<E> {
	@SuppressWarnings({ "serial" })
	private TypeToken<E> typeToken = new TypeToken<E>(getClass()) {
	};

	public EntityRpcServiceImpl() {
	}

	@Autowired
	private SimpleService simpleService;

	@Override
	public E persist(E e) {
		return simpleService.persist(e);
	}

	@Override
	public Collection<E> persist(Collection<E> list) {
		return simpleService.persist(list);
	}

	@Override
	public void delete(E e) {
		simpleService.delete(e);
	}

	@Override
	public void delete(Collection<E> list) {
		simpleService.delete(list);
	}

	@Override
	public List<E> list() {
		return simpleService.list(getRawType());
	}

	@Override
	public E get(E e) {
		return simpleService.get(e);
	}

	@Override
	public <Condition extends QueryCondition> ListLoadResult<E> list(
			GwtListLoadConfigBean<Condition> condition) {
		return simpleService.list(condition, getRawType());
	}

	@Override
	public <Condition extends QueryCondition> PagingLoadResult<E> paging(
			GwtPagingLoadConfigBean<Condition> condition) {
		return simpleService.paging(condition, getRawType());
	}

	@SuppressWarnings("unchecked")
	public Class<E> getRawType() {
		return (Class<E>) typeToken.getRawType();
	}

	@Override
	public <Condition extends QueryCondition> List<E> list(Condition condition)
			throws SharedException {
		return simpleService.list(getRawType(), condition,
				new EmptyQueryConditionAnalyzer<Condition>());
	}

	@Override
	public E update(E e) {
		// return simpleService.update(e, updater);
		return null;
	}

	@Override
	public <C extends Collection<E>> C update(C list) {
		// return simpleService.update(list, updater);
		return null;
	}
}
