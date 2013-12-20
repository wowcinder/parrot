/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.parrot.gwt.common.shared.rpcservice;

import java.util.List;

import com.sencha.gxt.data.shared.loader.ListLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.voole.parrot.shared.exception.SharedException;
import com.voole.parrot.shared.grid.GwtListLoadConfigBean;
import com.voole.parrot.shared.grid.GwtPagingLoadConfigBean;

/**
 * @author XuehuiHe
 * @date 2013年12月20日
 */
public interface NormalRpcService<T> {
	T save(T t) throws SharedException;

	T update(T t) throws SharedException;

	void delete(T t) throws SharedException;

	List<T> save(List<T> list) throws SharedException;

	List<T> update(List<T> list) throws SharedException;

	void delete(List<T> list) throws SharedException;

	List<T> get() throws SharedException;

	public ListLoadResult<T> list(GwtListLoadConfigBean<?> condition);

	public PagingLoadResult<T> paging(GwtPagingLoadConfigBean<?> condition);

}
