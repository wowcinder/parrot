/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.log.transformer;

import java.util.Calendar;
import java.util.Date;

import com.voole.log.transformer.exception.LogTransformException;
import com.voole.parrot.shared.entity.logmeta.LogModelVersion;
import com.voole.parrot.shared.hbasequery.HbaseLogRecordCollection;

/**
 * @author XuehuiHe
 * @date 2013年12月31日
 */
public abstract class LogTransformer {

	private final LogModelVersion version;

	public LogTransformer(LogModelVersion version) {
		this.version = version;
	}

	public LogModelVersion getVersion() {
		return version;
	}

	public HbaseLogRecordCollection transform(String log)
			throws LogTransformException {
		int index = log.indexOf('#');
		if (index == -1) {
			throw createLogTransformException("stamp not found", log);
		}
		Date stamp = getStamp(log, index);
		if (stamp == null) {
			throw createLogTransformException("stamp is wrong", log);
		}
		String logWithoutStamp = log.substring(index + 1);
		HbaseLogRecordCollection c = null;
		try {
			c = transform(stamp, logWithoutStamp);
		} catch (LogTransformException e) {
			e.setLog(log);
			e.setVersion(getVersion().getRootColumn().getHbaseTableVersion());
			throw e;
		}
		return c;
	}

	protected LogTransformException createLogTransformException(String msg,
			String log) {
		LogTransformException e = new LogTransformException(msg);
		e.setLog(log);
		e.setVersion(getVersion().getRootColumn().getHbaseTableVersion());
		return e;
	}

	protected abstract HbaseLogRecordCollection transform(Date stamp, String log)
			throws LogTransformException;

	private Date getStamp(String log, int index) {
		String stampStr = log.substring(0, index);
		Date stamp = getStamp(stampStr);
		return stamp;
	}

	public static Date getStamp(String stamp) {
		if (!stamp.matches("^\\d+$")) {
			return null;
		}
		try {
			Long t = Long.parseLong(stamp) * 1000;
			Calendar c = Calendar.getInstance();
			c.setTimeInMillis(t);
			return c.getTime();
		} catch (Exception e) {
		}
		return null;
	}

	public static String generateKey(Date stamp) {
		if (stamp == null) {
			stamp = new Date();
		}
		return (10000000000000l - stamp.getTime()) / 1000 + "_" + getRandom();
	}

	public static String generateKey(String parentKey, Integer index) {
		if (index < 10) {
			return parentKey + "_00" + index;
		} else if (index < 100) {
			return parentKey + "_0" + index;
		} else {
			return parentKey + "_" + index;
		}
	}

	/**
	 * @return 10000~99999
	 */
	protected static Long getRandom() {
		return (long) Math.ceil(10000 + 90000 * Math.random());
	}

}
