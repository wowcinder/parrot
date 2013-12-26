/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.parrot.rpc.service.rpc;

import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.voole.parrot.gwt.common.shared.rpcservice.CtypeLogModelRpcService;
import com.voole.parrot.service.service.ctypelogmeta.CtypeLogModelColumnService;
import com.voole.parrot.service.service.ctypelogmeta.CtypeLogModelGroupColumnService;
import com.voole.parrot.service.service.ctypelogmeta.CtypeLogModelLeafColumnService;
import com.voole.parrot.service.service.ctypelogmeta.CtypeLogModelService;
import com.voole.parrot.service.service.ctypelogmeta.CtypeLogModelVersionService;
import com.voole.parrot.shared.entity.ctypelogmeta.CtypeLogModel;
import com.voole.parrot.shared.entity.ctypelogmeta.CtypeLogModelColumn;
import com.voole.parrot.shared.entity.ctypelogmeta.CtypeLogModelGroupColumn;
import com.voole.parrot.shared.entity.ctypelogmeta.CtypeLogModelLeafColumn;
import com.voole.parrot.shared.entity.ctypelogmeta.CtypeLogModelRootColumn;
import com.voole.parrot.shared.entity.ctypelogmeta.CtypeLogModelVersion;
import com.voole.parrot.shared.exception.SharedException;

/**
 * @author XuehuiHe
 * @date 2013年12月26日
 */
@Service
public class CtypeLogModelRpcServiceImpl implements CtypeLogModelRpcService {
	@Autowired
	private CtypeLogModelService modelService;
	@Autowired
	private CtypeLogModelVersionService versionService;
	@Autowired
	private CtypeLogModelColumnService columnService;
	@Autowired
	private CtypeLogModelGroupColumnService groupColumnService;
	@Autowired
	private CtypeLogModelLeafColumnService leafColumnService;

	@Override
	public CtypeLogModel createModel(CtypeLogModel model)
			throws SharedException {
		return modelService.persist(model);
	}

	@Override
	public CtypeLogModel modifyModel(CtypeLogModel model)
			throws SharedException {
		return modelService.modifyModel(model);
	}

	@Override
	public void deleteModel(CtypeLogModel model) throws SharedException {
		modelService.delete(model);
	}

	@Override
	@Transactional
	public List<CtypeLogModel> listModelWithVersions() throws SharedException {
		List<CtypeLogModel> models = modelService.list();
		for (CtypeLogModel model : models) {
			Hibernate.initialize(model.getVersions());
		}
		return models;
	}

	@Override
	public CtypeLogModelVersion createVersion(CtypeLogModelVersion version)
			throws SharedException {
		return modelService.createVersion(version);
	}

	@Override
	public CtypeLogModelVersion modifyVersion(CtypeLogModelVersion version)
			throws SharedException {
		return versionService.modifyVersion(version);
	}

	@Override
	public void deleteVersion(CtypeLogModelVersion version)
			throws SharedException {
		versionService.delete(version);
	}

	@Override
	public CtypeLogModelVersion duplicateVerion(CtypeLogModelVersion duplicate,
			CtypeLogModelVersion from) throws SharedException {
		return versionService.duplicateVerion(duplicate, from);
	}

	@Override
	public CtypeLogModelRootColumn getVersionRootColumnWithChildren(
			CtypeLogModelVersion version) throws SharedException {
		return versionService.getVersionRootColumnWithChildren(version);
	}

	@Override
	public CtypeLogModelColumn createColumn(CtypeLogModelColumn column)
			throws SharedException {
		return groupColumnService.createColumn(column);
	}

	@Override
	public CtypeLogModelColumn modifyColumn(CtypeLogModelColumn column)
			throws SharedException {
		if (column instanceof CtypeLogModelLeafColumn) {
			return leafColumnService
					.modifyColumn((CtypeLogModelLeafColumn) column);
		} else if (column instanceof CtypeLogModelGroupColumn) {
			return groupColumnService
					.modifyColumn((CtypeLogModelGroupColumn) column);
		}
		return null;
	}

	@Override
	public void deleteColumn(CtypeLogModelColumn column) throws SharedException {
		columnService.delete(column);
	}

	@Override
	public void changeColumnsPos(List<CtypeLogModelColumn> column, Integer pos)
			throws SharedException {
		// TODO Auto-generated method stub

	}

}
