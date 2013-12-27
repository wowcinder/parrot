/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.importfrom.ctype;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.voole.parrot.importfrom.ctype.CtypeLogModelMetaAnalyzer;
import com.voole.parrot.shared.entity.kafka.KafkaTopic.KafkaTopicCharset;

/**
 * @author XuehuiHe
 * @date 2013年12月27日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/spring-test.xml")
public class CTypeTest {
	@Autowired
	private CtypeLogModelMetaAnalyzer analyzer;

	@Test
	public void test() {
		analyzer.setCharset(KafkaTopicCharset.ISO88591);
		analyzer.setJsonFile("c.json");
		analyzer.analyze();
	}

}
