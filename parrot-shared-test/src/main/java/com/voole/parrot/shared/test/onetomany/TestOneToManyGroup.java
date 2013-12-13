/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.parrot.shared.test.onetomany;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.voole.parrot.shared.test.BaseEntity;

/**
 * @author XuehuiHe
 * @date 2013年11月28日
 */
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
public class TestOneToManyGroup extends BaseEntity {
	private static final long serialVersionUID = -7480597023915975796L;

	private List<TestOneToManyEntity> entities;

	@OneToMany(mappedBy = "group", cascade = { CascadeType.ALL })
	public List<TestOneToManyEntity> getEntities() {
		return entities;
	}

	public void setEntities(List<TestOneToManyEntity> entities) {
		this.entities = entities;
	}

}
