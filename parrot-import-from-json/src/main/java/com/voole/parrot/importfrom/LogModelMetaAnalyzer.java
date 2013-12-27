/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.parrot.importfrom;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.voole.importfrom.ctype.CTypeTest;
import com.voole.parrot.shared.entity.kafka.KafkaTopic.KafkaTopicCharset;

/**
 * @author XuehuiHe
 * @date 2013年12月27日
 */
public abstract class LogModelMetaAnalyzer {
	private String jsonFile;
	private KafkaTopicCharset charset;

	public LogModelMetaAnalyzer() {
		this.charset = KafkaTopicCharset.UTF8;
	}

	public void setJsonFile(String jsonFile) {
		this.jsonFile = jsonFile;
	}

	public void setCharset(KafkaTopicCharset charset) {
		this.charset = charset;
	}

	public String getJsonFile() {
		return jsonFile;
	}

	public KafkaTopicCharset getCharset() {
		return charset;
	}

	public static String readJsonFile(String name) throws IOException {
		InputStream in = CTypeTest.class.getClassLoader().getResourceAsStream(
				name);

		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		String line = null;
		StringBuilder sb = new StringBuilder();
		while ((line = reader.readLine()) != null) {
			sb.append(line);
		}
		reader.close();
		return sb.toString();

	}
}
