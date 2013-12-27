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
public class AttachmentsCtypeColumn implements CtypeColumn {
	private String table;
	private Map<String, SimpleCtypeColumn> data;
	private List<String> orders;

	public String getTable() {
		return table;
	}

	public Map<String, SimpleCtypeColumn> getData() {
		return data;
	}

	public List<String> getOrders() {
		return orders;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public void setData(Map<String, SimpleCtypeColumn> data) {
		this.data = data;
	}

	public void setOrders(List<String> orders) {
		this.orders = orders;
	}

}
