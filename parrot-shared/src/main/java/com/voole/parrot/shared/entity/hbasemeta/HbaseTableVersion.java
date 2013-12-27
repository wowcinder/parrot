/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.parrot.shared.entity.hbasemeta;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.voole.parrot.shared.entity.EntityHasAutoId;
import com.voole.parrot.shared.entity.EntityWithOrderChildren;
import com.voole.parrot.shared.entity.logmeta.LogModelGroupColumn;

/**
 * @author XuehuiHe
 * @date 2013年12月25日
 */
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
public class HbaseTableVersion extends EntityHasAutoId implements
		EntityWithOrderChildren {
	private String version;
	private String desc;
	private HbaseTable table;
	private List<HbaseTableColumn> columns;
	private Integer pos;
	private List<LogModelGroupColumn> logModelGroupColumns;

	public HbaseTableVersion() {
		version = "0.0";
	}

	@Column(length = 20, nullable = false)
	@NotNull
	@Length(min = 0, max = 20)
	public String getVersion() {
		return version;
	}

	@Column(name = "description", columnDefinition = "text")
	public String getDesc() {
		return desc;
	}

	@ManyToOne(optional = false)
	@NotNull
	@JoinColumn(name = "`table`")
	public HbaseTable getTable() {
		return table;
	}

	@OneToMany(mappedBy = "version", cascade = { CascadeType.ALL })
	@OrderBy("pos")
	public List<HbaseTableColumn> getColumns() {
		return columns;
	}

	@OneToMany(mappedBy = "hbaseTableVersion")
	public List<LogModelGroupColumn> getLogModelGroupColumns() {
		return logModelGroupColumns;
	}

	public void setLogModelGroupColumns(
			List<LogModelGroupColumn> logModelGroupColumns) {
		this.logModelGroupColumns = logModelGroupColumns;
	}

	public Integer getPos() {
		return pos;
	}

	public void setPos(Integer pos) {
		this.pos = pos;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public void setTable(HbaseTable table) {
		this.table = table;
	}

	public void setColumns(List<HbaseTableColumn> columns) {
		this.columns = columns;
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
