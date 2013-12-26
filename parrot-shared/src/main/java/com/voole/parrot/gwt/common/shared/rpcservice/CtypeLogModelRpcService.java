/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.parrot.gwt.common.shared.rpcservice;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.voole.parrot.shared.entity.ctypelogmeta.CtypeLogModel;
import com.voole.parrot.shared.entity.ctypelogmeta.CtypeLogModelColumn;
import com.voole.parrot.shared.entity.ctypelogmeta.CtypeLogModelRootColumn;
import com.voole.parrot.shared.entity.ctypelogmeta.CtypeLogModelVersion;
import com.voole.parrot.shared.exception.SharedException;

/**
 * @author XuehuiHe
 * @date 2013年12月26日
 */
@RemoteServiceRelativePath("rpc/ctype_log_model.rpc")
public interface CtypeLogModelRpcService extends RemoteService {
	// model
	CtypeLogModel createModel(CtypeLogModel model) throws SharedException;

	CtypeLogModel modifyModel(CtypeLogModel model) throws SharedException;

	void deleteModel(CtypeLogModel model) throws SharedException;

	List<CtypeLogModel> listModelWithVersions() throws SharedException;

	// version
	CtypeLogModelVersion createVersion(CtypeLogModelVersion version)
			throws SharedException;

	CtypeLogModelVersion modifyVersion(CtypeLogModelVersion version)
			throws SharedException;

	void deleteVersion(CtypeLogModelVersion version) throws SharedException;

	CtypeLogModelVersion duplicateVerion(CtypeLogModelVersion duplicate,
			CtypeLogModelVersion from) throws SharedException;

	CtypeLogModelRootColumn getVersionRootColumnWithChildren(CtypeLogModelVersion version)
			throws SharedException;

	// column
	CtypeLogModelColumn createColumn(CtypeLogModelColumn column)
			throws SharedException;

	CtypeLogModelColumn modifyColumn(CtypeLogModelColumn column)
			throws SharedException;

	void deleteColumn(CtypeLogModelColumn column) throws SharedException;

	void changeColumnsPos(List<CtypeLogModelColumn> column, Integer pos)
			throws SharedException;
}
