/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.parrot.rpc.service.rpc;

import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.voole.parrot.gwt.common.shared.rpcservice.LogModelRpcService;
import com.voole.parrot.service.service.logmeta.LogModelColumnService;
import com.voole.parrot.service.service.logmeta.LogModelGroupColumnService;
import com.voole.parrot.service.service.logmeta.LogModelLeafColumnService;
import com.voole.parrot.service.service.logmeta.LogModelService;
import com.voole.parrot.service.service.logmeta.LogModelVersionService;
import com.voole.parrot.shared.entity.logmeta.LogModel;
import com.voole.parrot.shared.entity.logmeta.LogModelColumn;
import com.voole.parrot.shared.entity.logmeta.LogModelGroupColumn;
import com.voole.parrot.shared.entity.logmeta.LogModelLeafColumn;
import com.voole.parrot.shared.entity.logmeta.LogModelRootColumn;
import com.voole.parrot.shared.entity.logmeta.LogModelVersion;
import com.voole.parrot.shared.exception.SharedException;

/**
 * @author XuehuiHe
 * @date 2013年12月26日
 */
@Service
public class LogModelRpcServiceImpl implements LogModelRpcService {
	@Autowired
	private LogModelService modelService;
	@Autowired
	private LogModelVersionService versionService;
	@Autowired
	private LogModelColumnService columnService;
	@Autowired
	private LogModelGroupColumnService groupColumnService;
	@Autowired
	private LogModelLeafColumnService leafColumnService;

	@Override
	public LogModel createModel(LogModel model)
			throws SharedException {
		return modelService.persist(model);
	}

	@Override
	public LogModel modifyModel(LogModel model)
			throws SharedException {
		return modelService.modifyModel(model);
	}

	@Override
	public void deleteModel(LogModel model) throws SharedException {
		modelService.delete(model);
	}

	@Override
	@Transactional
	public List<LogModel> listModelWithVersions() throws SharedException {
		List<LogModel> models = modelService.list();
		for (LogModel model : models) {
			Hibernate.initialize(model.getVersions());
		}
		return models;
	}

	@Override
	public LogModelVersion createVersion(LogModelVersion version)
			throws SharedException {
		return modelService.createVersion(version);
	}

	@Override
	public LogModelVersion modifyVersion(LogModelVersion version)
			throws SharedException {
		return versionService.modifyVersion(version);
	}

	@Override
	public void deleteVersion(LogModelVersion version)
			throws SharedException {
		versionService.delete(version);
	}

	@Override
	public LogModelVersion duplicateVerion(LogModelVersion duplicate,
			LogModelVersion from) throws SharedException {
		return versionService.duplicateVerion(duplicate, from);
	}

	@Override
	public LogModelRootColumn getVersionRootColumnWithChildren(
			LogModelVersion version) throws SharedException {
		return versionService.getVersionRootColumnWithChildren(version);
	}

	@Override
	public LogModelColumn createColumn(LogModelColumn column)
			throws SharedException {
		return groupColumnService.createColumn(column);
	}

	@Override
	public LogModelColumn modifyColumn(LogModelColumn column)
			throws SharedException {
		if (column instanceof LogModelLeafColumn) {
			return leafColumnService
					.modifyColumn((LogModelLeafColumn) column);
		} else if (column instanceof LogModelGroupColumn) {
			return groupColumnService
					.modifyColumn((LogModelGroupColumn) column);
		}
		return null;
	}

	@Override
	public void deleteColumn(LogModelColumn column) throws SharedException {
		columnService.delete(column);
	}

	@Override
	public LogModelColumn changeColumnsPos(LogModelColumn column)
			throws SharedException {
		return groupColumnService.changeColumnsPos(column);
	}

}
