/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.parrot.importfrom.ctype.model;

import java.util.List;
import java.util.Map;

/**
 * @author XuehuiHe
 * @date 2013年12月27日
 */
public class TopCtype {
	private String topic;
	private String table;
	private Map<String, CtypeColumn> data;
	private List<String> orders;

	public String getTopic() {
		return topic;
	}

	public String getTable() {
		return table;
	}

	public Map<String, CtypeColumn> getData() {
		return data;
	}

	public List<String> getOrders() {
		return orders;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public void setData(Map<String, CtypeColumn> data) {
		this.data = data;
	}

	public void setOrders(List<String> orders) {
		this.orders = orders;
	}

}
