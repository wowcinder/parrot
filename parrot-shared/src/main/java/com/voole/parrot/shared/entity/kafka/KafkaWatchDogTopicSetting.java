/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.parrot.shared.entity.kafka;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.voole.parrot.shared.entity.EntityHasAutoId;

/**
 * @author XuehuiHe
 * @date 2013年9月26日
 */
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "server", "topic" }))
public class KafkaWatchDogTopicSetting extends EntityHasAutoId {
	private static final long serialVersionUID = 467001406891512377L;
	private KafkaWatchDog server;
	private KafkaTopic topic;
	private Integer threadNum;

	public KafkaWatchDogTopicSetting() {
		threadNum = 1;
	}

	@ManyToOne(optional = false)
	public KafkaWatchDog getServer() {
		return server;
	}

	@ManyToOne(optional = false)
	@JoinColumn(updatable = false)
	public KafkaTopic getTopic() {
		return topic;
	}

	@Column(nullable = false, name = "thread_num")
	public Integer getThreadNum() {
		return threadNum;
	}

	public void setServer(KafkaWatchDog server) {
		this.server = server;
	}

	public void setTopic(KafkaTopic topic) {
		this.topic = topic;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (obj instanceof KafkaWatchDogTopicSetting) {
			KafkaWatchDogTopicSetting that = (KafkaWatchDogTopicSetting) obj;
			return this.getServer().getId().equals(that.getServer().getId())
					&& this.getTopic().getId().equals(that.getTopic().getId());
		}
		return false;
	}

	@Override
	public int hashCode() {
		return getServer().getId().hashCode() + 3
				* getTopic().getId().hashCode();
	}

	public void setThreadNum(Integer threadNum) {
		this.threadNum = threadNum;
	}

}
