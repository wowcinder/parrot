/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.log.transformer;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.voole.log.transformer.exception.LogTransformInitException;
import com.voole.parrot.shared.entity.logmeta.LogModel.LogModelType;
import com.voole.parrot.shared.entity.logmeta.LogModelVersion;

/**
 * @author XuehuiHe
 * @date 2013年12月31日
 */
public class LogTransformerManager {
	public final Map<Long, LogTransformer> transformerMap;

	public LogTransformerManager() {
		transformerMap = new ConcurrentHashMap<Long, LogTransformer>();
	}

	public void clear() {
		transformerMap.clear();
	}

	public LogTransformer getTransformer(LogModelVersion version)
			throws LogTransformInitException {
		if (!transformerMap.containsKey(version.getId())) {
			createTransformer(version);
		}
		return transformerMap.get(version);

	}

	public synchronized void createTransformer(LogModelVersion version)
			throws LogTransformInitException {
		if (transformerMap.containsKey(version.getId())) {
			return;
		}
		LogTransformer transformer = null;
		if (version.getModel().getType() == LogModelType.JSON) {
			transformer = new JsonLogTransformer(version);
		} else {
			transformer = new CtypeLogTransformer(version);
		}
		transformerMap.put(version.getId(), transformer);
	}

}
