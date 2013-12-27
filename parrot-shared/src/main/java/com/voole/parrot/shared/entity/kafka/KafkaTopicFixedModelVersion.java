/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.parrot.shared.entity.kafka;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.voole.parrot.shared.entity.logmeta.LogModelVersion;

/**
 * @author XuehuiHe
 * @date 2013年10月8日
 */
@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class KafkaTopicFixedModelVersion extends KafkaTopic {
	private static final long serialVersionUID = -2542549323037087469L;
	private LogModelVersion version;

	public KafkaTopicFixedModelVersion() {
	}

	@ManyToOne(optional = false)
	public LogModelVersion getVersion() {
		return version;
	}

	public void setVersion(LogModelVersion version) {
		this.version = version;
	}
}
