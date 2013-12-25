/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.parrot.shared.entity.hbasemeta;

import java.util.Date;

public enum ColumnType {
	SHORT(Short.class), INT(Integer.class), LONG(Long.class), FLOAT(
			Float.class), DOUBLE(Double.class), STRING(String.class), CHAR(
			Character.class), BOOLEAN(Boolean.class), DATE(Date.class), TIME(
			Date.class), DATETIME(Date.class), TEXT(String.class);
	Class<?> clazz;

	ColumnType(Class<?> clazz) {
		this.clazz = clazz;
	}

	public Class<?> getClazz() {
		return clazz;
	}

	public void setClazz(Class<?> clazz) {
		this.clazz = clazz;
	}

}