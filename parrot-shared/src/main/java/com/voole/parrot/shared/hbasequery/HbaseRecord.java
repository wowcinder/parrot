/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.parrot.shared.hbasequery;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author XuehuiHe
 * @date 2013年8月15日
 */
public class HbaseRecord<K extends Serializable> implements Serializable {
	private static final long serialVersionUID = -8590935689080675384L;

	private K key;
	private Map<String, Object> data;

	public HbaseRecord() {
		data = new HashMap<String, Object>();
	}

	public K getKey() {
		return key;
	}

	public void setKey(K key) {
		this.key = key;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}

}
