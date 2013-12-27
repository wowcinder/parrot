/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.parrot.importfrom.json.model;

import java.util.Map;

/**
 * @author XuehuiHe
 * @date 2013年12月27日
 */
public class JsonAttachmentsCtypeColumn implements JsonCtypeColumn {
	private String table;
	private Map<String, JsonSimpleCtypeColumn> data;

	public String getTable() {
		return table;
	}

	public Map<String, JsonSimpleCtypeColumn> getData() {
		return data;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public void setData(Map<String, JsonSimpleCtypeColumn> data) {
		this.data = data;
	}

}
