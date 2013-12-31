/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.parrot.shared.hbasequery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author XuehuiHe
 * @date 2013年12月31日
 */
public class HbaseLogRecordCollection extends
		HashMap<String, List<HbaseLogRecord>> {
	public HbaseLogRecordCollection() {
	}

	public void add(String key, HbaseLogRecord record) {
		if (!this.containsKey(key)) {
			this.put(key, new ArrayList<HbaseLogRecord>());
		}
		this.get(key).add(record);
	}

	public void add(String key, List<HbaseLogRecord> records) {
		if (!this.containsKey(key)) {
			put(key, records);
		} else {
			this.get(key).addAll(records);
		}
	}

	public void merge(HbaseLogRecordCollection another) {
		for (java.util.Map.Entry<String, List<HbaseLogRecord>> entry : another
				.entrySet()) {
			this.add(entry.getKey(), entry.getValue());
		}
	}
}
