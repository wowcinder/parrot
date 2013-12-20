/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.client;

import java.io.Serializable;
import java.util.Date;

/**
 * @author XuehuiHe
 * @date 2013年12月20日
 */
public class TestBean implements Serializable {
	private static final long serialVersionUID = 968365349533775181L;
	private Long id;
	private Date now;

	public Long getId() {
		return id;
	}

	public Date getNow() {
		return now;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setNow(Date now) {
		this.now = now;
	}

}
