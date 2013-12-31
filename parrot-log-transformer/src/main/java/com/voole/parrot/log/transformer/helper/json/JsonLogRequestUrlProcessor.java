/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.parrot.log.transformer.helper.json;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author XuehuiHe
 * @date 2013年12月31日
 */
public class JsonLogRequestUrlProcessor {
	public static void process(Map<String, Object> tempData) {
		Object requestObject = tempData.get("request");
		if (requestObject != null && requestObject instanceof String) {
			String request = (String) requestObject;
			if (request.length() > 0) {
				tempData.put("request", splitQuery(request));
			}
		}
	}

	public static Map<String, String> splitQuery(String query) {
		Map<String, String> query_pairs = new LinkedHashMap<String, String>();
		String[] pairs = query.split("&");
		for (String pair : pairs) {
			int idx = pair.indexOf("=");
			String key;
			String value;
			try {
				key = URLDecoder.decode(pair.substring(0, idx), "UTF-8");
				value = URLDecoder.decode(pair.substring(idx + 1), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				continue;
			}

			if ("null".equals(value)) {
				value = null;
			}
			query_pairs.put(key, value);
		}
		return query_pairs;
	}
}