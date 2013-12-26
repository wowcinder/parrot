/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.parrot.shared.entity.ctypelogmeta;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

/**
 * @author XuehuiHe
 * @date 2013年12月26日
 */
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
public class CtypeLogModelRootColumn extends CtypeLogModelGroupColumn {
	private CtypeLogModelVersion version;

	public CtypeLogModelRootColumn() {
		setName("root");
	}

	@OneToOne(mappedBy = "rootColumn")
	public CtypeLogModelVersion getVersion() {
		return version;
	}

	public void setVersion(CtypeLogModelVersion version) {
		this.version = version;
	}

}
