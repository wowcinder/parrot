/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.parrot.shared.entity.logmeta;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

/**
 * @author XuehuiHe
 * @date 2013年12月26日
 */
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
public class LogModelRootColumn extends LogModelGroupColumn {
	private LogModelVersion version;

	public LogModelRootColumn() {
		setName("root");
	}

	@OneToOne(mappedBy = "rootColumn")
	public LogModelVersion getVersion() {
		return version;
	}

	public void setVersion(LogModelVersion version) {
		this.version = version;
	}

}
