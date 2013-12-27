/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.parrot.gwt.common.shared.rpcservice;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.voole.parrot.shared.entity.logmeta.LogModel;
import com.voole.parrot.shared.entity.logmeta.LogModelColumn;
import com.voole.parrot.shared.entity.logmeta.LogModelRootColumn;
import com.voole.parrot.shared.entity.logmeta.LogModelVersion;
import com.voole.parrot.shared.exception.SharedException;

/**
 * @author XuehuiHe
 * @date 2013年12月26日
 */
@RemoteServiceRelativePath("rpc/ctype_log_model.rpc")
public interface LogModelRpcService extends RemoteService {
	// model
	LogModel createModel(LogModel model) throws SharedException;

	LogModel modifyModel(LogModel model) throws SharedException;

	void deleteModel(LogModel model) throws SharedException;

	List<LogModel> listModelWithVersions() throws SharedException;

	// version
	LogModelVersion createVersion(LogModelVersion version)
			throws SharedException;

	LogModelVersion modifyVersion(LogModelVersion version)
			throws SharedException;

	void deleteVersion(LogModelVersion version) throws SharedException;

	LogModelVersion duplicateVerion(LogModelVersion duplicate,
			LogModelVersion from) throws SharedException;

	LogModelRootColumn getVersionRootColumnWithChildren(
			LogModelVersion version) throws SharedException;

	// column
	LogModelColumn createColumn(LogModelColumn column)
			throws SharedException;

	LogModelColumn modifyColumn(LogModelColumn column)
			throws SharedException;

	void deleteColumn(LogModelColumn column) throws SharedException;

	LogModelColumn changeColumnsPos(LogModelColumn column) throws SharedException;
}
