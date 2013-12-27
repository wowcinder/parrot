/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.parrot.shared.entity.logmeta;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import com.voole.parrot.shared.entity.EntityWithOrderChildren;
import com.voole.parrot.shared.entity.hbasemeta.HbaseTableVersion;

/**
 * @author XuehuiHe
 * @date 2013年12月25日
 */
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
public class LogModelGroupColumn extends LogModelColumn implements
		EntityWithOrderChildren {
	private List<LogModelColumn> columns;
	private HbaseTableVersion hbaseTableVersion;

	@OneToMany(mappedBy = "parent", cascade = { CascadeType.PERSIST,
			CascadeType.DETACH })
	@OrderBy("pos")
	public List<LogModelColumn> getColumns() {
		return columns;
	}

	@ManyToOne
	public HbaseTableVersion getHbaseTableVersion() {
		return hbaseTableVersion;
	}

	public void setColumns(List<LogModelColumn> columns) {
		this.columns = columns;
	}

	public void setHbaseTableVersion(HbaseTableVersion hbaseTableVersion) {
		this.hbaseTableVersion = hbaseTableVersion;
	}

	@Override
	public void sortChildren() {
		int size = 0;
		if (columns == null || (size = columns.size()) == 0) {
			return;
		}
		for (int i = 0; i < size; i++) {
			columns.get(i).setPos(i);
		}
	}

}
