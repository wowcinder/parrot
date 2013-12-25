/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.parrot.shared.entity.hbasemeta;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
public class HbaseTable extends EntityHasAutoId implements
		EntityWithOrderChildren {
	private String name;
	private String shortname;
	private String desc;
	private List<HbaseTableVersion> versions;

	@Column(length = 60, unique = true)
	@NotNull
	@Length(min = 1, max = 60)
	public String getName() {
		return name;
	}

	@Column(length = 60)
	@NotNull
	@Length(min = 1, max = 60)
	public String getShortname() {
		return shortname;
	}

	@Column(name = "description", columnDefinition = "text")
	public String getDesc() {
		return desc;
	}

	@OneToMany(mappedBy = "table", cascade = CascadeType.ALL)
	@OrderBy("pos")
	public List<HbaseTableVersion> getVersions() {
		return versions;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setShortname(String shortname) {
		this.shortname = shortname;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public void setVersions(List<HbaseTableVersion> versions) {
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

}
