/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.parrot.shared.entity.ctypelogmeta;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 * @author XuehuiHe
 * @date 2013年12月25日
 */
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
public class CtypeLogModelVersion extends CtypeLogModelGroupColumn {
	private String desc;
	private CtypeLogModel model;

	@Column(name = "description", columnDefinition = "text")
	public String getDesc() {
		return desc;
	}

	@ManyToOne(optional = false)
	@NotNull
	public CtypeLogModel getModel() {
		return model;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public void setModel(CtypeLogModel model) {
		this.model = model;
	}

}
