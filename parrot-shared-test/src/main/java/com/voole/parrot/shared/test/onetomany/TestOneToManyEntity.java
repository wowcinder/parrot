/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.parrot.shared.test.onetomany;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.voole.parrot.shared.test.BaseEntity;

/**
 * @author XuehuiHe
 * @date 2013年11月28日
 */
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
public class TestOneToManyEntity extends BaseEntity {
	private static final long serialVersionUID = -6919558783349030502L;

	private TestOneToManyGroup group;

	@ManyToOne
	@JoinColumn(name = "`group`")
	public TestOneToManyGroup getGroup() {
		return group;
	}

	public void setGroup(TestOneToManyGroup group) {
		this.group = group;
	}

}
