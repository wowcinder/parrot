/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.parrot.log.transformer.exception;

import com.voole.parrot.shared.entity.hbasemeta.HbaseTableVersion;

/**
 * @author XuehuiHe
 * @date 2013年9月26日
 */
public class LogTransformException extends Exception {
	private String log;
	private HbaseTableVersion version;

	private static final long serialVersionUID = -5372795647196270506L;

	public LogTransformException() {
		super();
	}

	public LogTransformException(String message) {
		super(message);
	}

	public LogTransformException(String message, Throwable cause) {
		super(message, cause);
	}

	public LogTransformException(Throwable cause) {
		super(cause);
	}

	public String getLog() {
		return log;
	}

	public HbaseTableVersion getVersion() {
		return version;
	}

	public void setLog(String log) {
		this.log = log;
	}

	public void setVersion(HbaseTableVersion version) {
		this.version = version;
	}

}
