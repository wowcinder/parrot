/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.log.transformer.exception;

import com.voole.parrot.shared.entity.logmeta.LogModelVersion;

/**
 * @author XuehuiHe
 * @date 2013年12月31日
 */
public class LogTransformInitException extends Exception {
	private LogModelVersion version;

	public LogTransformInitException(LogModelVersion version) {
		super();

	}

	public LogTransformInitException(LogModelVersion version, String message) {
		super(message);
		this.version = version;
	}

	public LogTransformInitException(String message, Throwable cause) {
		super(message, cause);
	}

	public LogTransformInitException(Throwable cause) {
		super(cause);
	}

	public LogModelVersion getVersion() {
		return version;
	}

	public void setVersion(LogModelVersion version) {
		this.version = version;
	}

}
