/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.parrot.rpc.service.rpc;

import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.voole.parrot.gwt.common.shared.rpcservice.HbaseTableMetaRpcService;
import com.voole.parrot.service.service.hbasemeta.HbaseTableColumnService;
import com.voole.parrot.service.service.hbasemeta.HbaseTableService;
import com.voole.parrot.service.service.hbasemeta.HbaseTableVersionService;
import com.voole.parrot.shared.entity.hbasemeta.HbaseTable;
import com.voole.parrot.shared.entity.hbasemeta.HbaseTableColumn;
import com.voole.parrot.shared.entity.hbasemeta.HbaseTableVersion;
import com.voole.parrot.shared.exception.SharedException;

/**
 * @author XuehuiHe
 * @date 2013年12月25日
 */
@Service
public class HbaseTableMetaRpcServiceImpl implements HbaseTableMetaRpcService {
	@Autowired
	private HbaseTableService tableService;
	@Autowired
	private HbaseTableVersionService versionService;
	@Autowired
	private HbaseTableColumnService columnService;

	@Override
	public HbaseTable createTable(HbaseTable table) throws SharedException {
		return tableService.persist(table);
	}

	@Override
	public void deleteTable(HbaseTable table) throws SharedException {
		tableService.delete(table);
	}

	@Override
	public HbaseTableVersion createHbaseTableVersion(HbaseTableVersion version)
			throws SharedException {
		return tableService.createHbaseTableVersion(version);
	}

	@Override
	public void deleteHbaseTableVersion(HbaseTableVersion version)
			throws SharedException {
		versionService.delete(version);
	}

	@Override
	public HbaseTableColumn createHbaseTableColumn(HbaseTableColumn column)
			throws SharedException {
		return versionService.createHbaseTableColumn(column);
	}

	@Override
	public HbaseTableColumn modifyHbaseTableColumn(HbaseTableColumn column)
			throws SharedException {
		return columnService.modifyHbaseTableColumn(column);
	}

	@Override
	public void deleteHbaseTableColumns(List<HbaseTableColumn> columns)
			throws SharedException {
		columnService.delete(columns);
	}

	@Override
	@Transactional
	public List<HbaseTable> listHbaseTables() throws SharedException {
		List<HbaseTable> list = tableService.list();
		for (HbaseTable hbaseTable : list) {
			Hibernate.initialize(hbaseTable.getVersions());
		}
		return list;
	}

	@Override
	@Transactional
	public List<HbaseTableColumn> listHbaseTableColumns(
			HbaseTableVersion version) throws SharedException {
		version = versionService.get(version);
		Hibernate.initialize(version.getColumns());
		return version.getColumns();
	}

	@Override
	public HbaseTable modifyTable(HbaseTable table) throws SharedException {
		return tableService.modifyTable(table);
	}

	@Override
	public HbaseTableVersion modifyHbaseTableVersion(HbaseTableVersion version)
			throws SharedException {
		return versionService.modifyHbaseTableVersion(version);
	}

	@Override
	public HbaseTableVersion duplicateHbaseTableVerion(
			HbaseTableVersion duplicate, HbaseTableVersion from)
			throws SharedException {
		return versionService.duplicateHbaseTableVerion(duplicate, from);
	}

	@Override
	public void changeHbaseTableColumnsPos(List<HbaseTableColumn> column,
			Integer pos) throws SharedException {
		versionService.changeHbaseTableColumnsPos(column, pos);
	}

}
