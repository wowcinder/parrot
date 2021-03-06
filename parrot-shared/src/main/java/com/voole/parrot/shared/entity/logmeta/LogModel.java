/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.parrot.shared.entity.logmeta;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.voole.parrot.shared.entity.EntityHasAutoId;
import com.voole.parrot.shared.entity.EntityWithOrderChildren;

/**
 * @author XuehuiHe
 * @date 2013年12月25日
 */
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
public class LogModel extends EntityHasAutoId implements
		EntityWithOrderChildren {
	private String name;
	private String desc;
	private List<LogModelVersion> versions;
	private LogModelType type;

	@NotNull
	@Length(min = 1, max = 100)
	@Column(nullable = false, unique = true, length = 100)
	public String getName() {
		return name;
	}

	@Column(name = "description", columnDefinition = "text")
	public String getDesc() {
		return desc;
	}

	@OneToMany(mappedBy = "model", cascade = { CascadeType.ALL })
	@OrderBy("pos")
	public List<LogModelVersion> getVersions() {
		return versions;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "model_type", nullable = false, length = 20)
	public LogModelType getType() {
		return type;
	}

	public void setType(LogModelType type) {
		this.type = type;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public void setVersions(List<LogModelVersion> versions) {
		this.versions = versions;
	}

	@Override
	public void sortChildren() {
		int size = 0;
		if (versions == null || (size = versions.size()) == 0) {
			return;
		}
		for (int i = 0; i < size; i++) {
			versions.get(i).setPos(i);
		}
	}

	public static enum LogModelType {
		JSON, CTYPE;
	}

}
