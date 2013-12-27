package com.voole.parrot.importfrom.json.model;

import java.util.Map;

public class TopJson {
	private String topic;
	private String table;
	private Map<String, JsonCtypeColumn> data;

	public String getTopic() {
		return topic;
	}

	public String getTable() {
		return table;
	}

	public Map<String, JsonCtypeColumn> getData() {
		return data;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public void setData(Map<String, JsonCtypeColumn> data) {
		this.data = data;
	}

}
