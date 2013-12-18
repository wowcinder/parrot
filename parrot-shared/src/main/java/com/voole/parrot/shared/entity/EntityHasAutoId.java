/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.parrot.shared.entity;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.AccessType;

/**
 * @author XuehuiHe
 * @date 2013年11月27日
 */
@MappedSuperclass
@AccessType("property")
public abstract class EntityHasAutoId implements Serializable {
	private static final long serialVersionUID = 3648624850242085135L;
	private Long id;

	public EntityHasAutoId() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
