/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.parrot.gwt.common.shared.rpcservice;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.voole.parrot.shared.entity.hbasemeta.HbaseTable;
import com.voole.parrot.shared.entity.hbasemeta.HbaseTableColumn;
import com.voole.parrot.shared.entity.hbasemeta.HbaseTableVersion;
import com.voole.parrot.shared.exception.SharedException;

/**
 * @author XuehuiHe
 * @date 2013年12月25日
 */
@RemoteServiceRelativePath("rpc/hbase_table_meta.rpc")
public interface HbaseTableMetaRpcService extends RemoteService {
	HbaseTable createTable(HbaseTable table) throws SharedException;

	HbaseTable modifyTable(HbaseTable table) throws SharedException;

	void deleteTable(HbaseTable table) throws SharedException;

	HbaseTableVersion createHbaseTableVersion(HbaseTableVersion version)
			throws SharedException;

	HbaseTableVersion modifyHbaseTableVersion(HbaseTableVersion version)
			throws SharedException;

	void deleteHbaseTableVersion(HbaseTableVersion version)
			throws SharedException;

	HbaseTableColumn createHbaseTableColumn(HbaseTableColumn column)
			throws SharedException;

	HbaseTableColumn modifyHbaseTableColumn(HbaseTableColumn column)
			throws SharedException;

	void deleteHbaseTableColumns(List<HbaseTableColumn> columns)
			throws SharedException;

	List<HbaseTable> listHbaseTables() throws SharedException;

	List<HbaseTableColumn> listHbaseTableColumns(HbaseTableVersion version)
			throws SharedException;

	HbaseTableVersion duplicateHbaseTableVerion(HbaseTableVersion duplicate,
			HbaseTableVersion from) throws SharedException;

	void changeHbaseTableColumnsPos(List<HbaseTableColumn> column, Integer pos)
			throws SharedException;
}
