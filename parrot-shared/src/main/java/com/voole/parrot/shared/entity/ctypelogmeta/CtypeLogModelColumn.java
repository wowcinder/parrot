/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.parrot.shared.entity.ctypelogmeta;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.voole.parrot.shared.entity.EntityHasAutoId;

/**
 * @author XuehuiHe
 * @date 2013年12月25日
 */
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Inheritance(strategy = InheritanceType.JOINED)
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "parent", "name" }) })
public class CtypeLogModelColumn extends EntityHasAutoId {
	private Integer pos;
	private String name;
	private String desc;
	private CtypeLogModelGroupColumn parent;

	public Integer getPos() {
		return pos;
	}

	@NotNull
	@Column(nullable = false, length = 40)
	@Length(min = 1, max = 40)
	public String getName() {
		return name;
	}

	@ManyToOne
	public CtypeLogModelGroupColumn getParent() {
		return parent;
	}

	@Column(name = "description", columnDefinition = "text")
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public void setPos(Integer pos) {
		this.pos = pos;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setParent(CtypeLogModelGroupColumn parent) {
		this.parent = parent;
	}

}
