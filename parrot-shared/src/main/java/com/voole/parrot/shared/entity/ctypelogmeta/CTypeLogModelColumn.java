/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.parrot.shared.entity.ctypelogmeta;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
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
public class CTypeLogModelColumn extends EntityHasAutoId {
	private Integer pos;
	private String name;
	private CTypeLogModelGroupColumn parent;

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
	public CTypeLogModelGroupColumn getParent() {
		return parent;
	}

	public void setPos(Integer pos) {
		this.pos = pos;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setParent(CTypeLogModelGroupColumn parent) {
		this.parent = parent;
	}

}
