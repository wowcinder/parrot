/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.parrot.shared.entity.ctypelogmeta;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

import com.voole.parrot.shared.entity.EntityWithOrderChildren;
import com.voole.parrot.shared.entity.hbasemeta.ColumnType;
import com.voole.parrot.shared.entity.hbasemeta.HbaseTableColumn;

/**
 * @author XuehuiHe
 * @date 2013年12月25日
 */
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
public class CtypeLogModelLeafColumn extends CtypeLogModelColumn implements
		EntityWithOrderChildren {
	private HbaseTableColumn hbaseTableColumn;
	private ColumnType type;

	@ManyToOne
	public HbaseTableColumn getHbaseTableColumn() {
		return hbaseTableColumn;
	}

	public void setHbaseTableColumn(HbaseTableColumn hbaseTableColumn) {
		this.hbaseTableColumn = hbaseTableColumn;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "column_type", nullable = false, length = 20)
	public ColumnType getType() {
		return type;
	}

	public void setType(ColumnType type) {
		this.type = type;
	}

	@Override
	public void sortChildren() {
		if (hbaseTableColumn == null
				|| hbaseTableColumn.getVersion() == null
				|| getParent() == null
				|| getParent().getHbaseTableVersion() == null
				|| getParent().getHbaseTableVersion().getId() != hbaseTableColumn
						.getVersion().getId()
				|| hbaseTableColumn.getType() != this.getType()) {
			hbaseTableColumn = null;
		}
	}
}
