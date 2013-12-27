/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.parrot.shared.entity.logmeta;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.voole.parrot.shared.entity.EntityHasAutoId;
import com.voole.parrot.shared.entity.EntityWithOrderChildren;
import com.voole.parrot.shared.entity.kafka.KafkaTopicFixedModelVersion;

/**
 * @author XuehuiHe
 * @date 2013年12月25日
 */
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "version",
		"model" }) })
public class LogModelVersion extends EntityHasAutoId implements
		EntityWithOrderChildren {
	private String version;
	private String desc;
	private LogModel model;
	private Integer pos;
	private LogModelRootColumn rootColumn;
	private Set<KafkaTopicFixedModelVersion> fixedTopics;

	public LogModelVersion() {
		version = "0.0";
	}

	@Column(name = "description", columnDefinition = "text")
	public String getDesc() {
		return desc;
	}

	@ManyToOne(optional = false)
	@NotNull
	public LogModel getModel() {
		return model;
	}

	@Column(nullable = false, length = 32)
	@NotNull
	@Length(max = 32)
	public String getVersion() {
		return version;
	}

	public Integer getPos() {
		return pos;
	}

	@OneToOne(cascade = { CascadeType.ALL })
	public LogModelRootColumn getRootColumn() {
		return rootColumn;
	}

	@OneToMany(mappedBy = "version", cascade = { CascadeType.REMOVE })
	public Set<KafkaTopicFixedModelVersion> getFixedTopics() {
		return fixedTopics;
	}

	public void setFixedTopics(Set<KafkaTopicFixedModelVersion> fixedTopics) {
		this.fixedTopics = fixedTopics;
	}

	public void setRootColumn(LogModelRootColumn rootColumn) {
		this.rootColumn = rootColumn;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public void setPos(Integer pos) {
		this.pos = pos;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public void setModel(LogModel model) {
		this.model = model;
	}

	@Override
	public void sortChildren() {
		if (rootColumn == null) {
			rootColumn = new LogModelRootColumn();
			rootColumn.setVersion(this);
		}
	}

}
