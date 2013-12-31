/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.parrot.shared.hbasequery;

import java.util.Date;

/**
 * @author XuehuiHe
 * @date 2013年12月31日
 */
public class HbaseLogRecord extends HbaseRecord<String> {

	public HbaseLogRecord() {
	}

	public HbaseLogRecord(Date stamp, String version) {
		this();
		getData().put("stamp", stamp);
		getData().put("version", version);
	}
}
