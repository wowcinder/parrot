/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.parrot.gwt.common.shared.core.cell;

import com.sencha.gxt.core.client.ValueProvider;

/**
 * @author XuehuiHe
 * @date 2013年12月25日
 */
public abstract class TreeValueProvider<T, V> implements ValueProvider<T, V> {

	@Override
	public V getValue(T object) {
		if (object == null) {
			return null;
		}
		return _getValue(object);
	}

	protected abstract V _getValue(T object);

	@Override
	public void setValue(T object, V value) {

	}

	@Override
	public String getPath() {
		return null;
	}

}
