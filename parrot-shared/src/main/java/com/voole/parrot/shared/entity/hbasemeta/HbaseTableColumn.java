/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.parrot.shared.entity.hbasemeta;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.voole.parrot.shared.entity.EntityHasAutoId;
import com.voole.parrot.shared.entity.ctypelogmeta.CtypeLogModelLeafColumn;

/**
 * @author XuehuiHe
 * @date 2013年12月25日
 */
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
public class HbaseTableColumn extends EntityHasAutoId {
	private String name;
	private String shortname;
	private String desc;
	private ColumnType type;
	private HbaseTableVersion version;
	private Integer pos;
	private List<CtypeLogModelLeafColumn> ctypeLogModelLeafColumns;

	@Column(length = 40, nullable = false)
	@Length(min = 1, max = 40)
	@NotNull
	public String getName() {
		return name;
	}

	@Column(length = 40)
	@Length(min = 1, max = 40)
	public String getShortname() {
		return shortname;
	}

	@Column(name = "description", columnDefinition = "text")
	public String getDesc() {
		return desc;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "column_type", nullable = false, length = 20)
	public ColumnType getType() {
		return type;
	}

	@ManyToOne(optional = false)
	@NotNull
	public HbaseTableVersion getVersion() {
		return version;
	}

	@OneToMany(mappedBy = "hbaseTableColumn")
	public List<CtypeLogModelLeafColumn> getCtypeLogModelLeafColumns() {
		return ctypeLogModelLeafColumns;
	}

	public void setCtypeLogModelLeafColumns(
			List<CtypeLogModelLeafColumn> ctypeLogModelLeafColumns) {
		this.ctypeLogModelLeafColumns = ctypeLogModelLeafColumns;
	}

	public Integer getPos() {
		return pos;
	}

	public void setPos(Integer pos) {
		this.pos = pos;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setShortname(String shortname) {
		this.shortname = shortname;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public void setType(ColumnType type) {
		this.type = type;
	}

	public void setVersion(HbaseTableVersion version) {
		this.version = version;
	}
}
