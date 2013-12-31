/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.log.transformer;

import java.util.Collection;
import java.util.Date;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.voole.log.transformer.exception.LogTransformException;
import com.voole.log.transformer.exception.LogTransformInitException;
import com.voole.log.transformer.helper.json.JsonLogRequestUrlProcessor;
import com.voole.log.transformer.helper.json.JsonLogTransformerHelper;
import com.voole.log.transformer.helper.json.tree.JsonGroupNode;
import com.voole.log.transformer.helper.json.tree.JsonNodes;
import com.voole.parrot.shared.entity.logmeta.LogModel.LogModelType;
import com.voole.parrot.shared.entity.logmeta.LogModelVersion;
import com.voole.parrot.shared.hbasequery.HbaseLogRecordCollection;

/**
 * @author XuehuiHe
 * @date 2013年12月31日
 */
public class JsonLogTransformer extends LogTransformer {
	private final JsonGroupNode groupNode;
	private static Gson gson;
	static {
		GsonBuilder gb = new GsonBuilder();
		gson = gb.create();
	}

	public JsonLogTransformer(LogModelVersion version)
			throws LogTransformInitException {
		super(version);
		if (version.getModel().getType() != LogModelType.JSON) {
			throw new LogTransformInitException(version,
					"version is not a json version");
		}
		this.groupNode = JsonNodes.create(version.getRootColumn());
	}

	@Override
	protected HbaseLogRecordCollection transform(Date stamp, String log)
			throws LogTransformException {
		log = log.replaceAll("\\[\\]", "null");
		Object tempData = null;
		try {
			tempData = gson.fromJson(log, Object.class);
		} catch (Exception e) {
			throw new LogTransformException("Json Syntax Exception", e);
		}
		dealRequestUrl(tempData);
		JsonLogTransformerHelper helper = new JsonLogTransformerHelper(stamp);
		helper.analyze(tempData, groupNode);
		return helper.getCollection();
	}

	@SuppressWarnings("unchecked")
	protected void dealRequestUrl(Object tempData) {
		if (tempData instanceof Map) {
			JsonLogRequestUrlProcessor.process((Map<String, Object>) tempData);
		} else if (tempData instanceof Collection) {
			for (Object item : (Collection<?>) tempData) {
				if (item instanceof Map) {
					JsonLogRequestUrlProcessor
							.process((Map<String, Object>) item);
				}
			}
		}
	}

	public JsonGroupNode getGroupNode() {
		return this.groupNode;
	}
}
