/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.parrot.importfrom.ctype;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.voole.parrot.importfrom.LogModelMetaAnalyzer;
import com.voole.parrot.importfrom.ctype.model.AttachmentsCtypeColumn;
import com.voole.parrot.importfrom.ctype.model.CtypeColumn;
import com.voole.parrot.importfrom.ctype.model.SimpleCtypeColumn;
import com.voole.parrot.importfrom.ctype.model.TopCtype;
import com.voole.parrot.service.dao.SimpleDao;
import com.voole.parrot.service.service.kafka.KafkaTopicFixedModelVersionService;
import com.voole.parrot.service.service.logmeta.LogModelGroupColumnService;
import com.voole.parrot.service.service.logmeta.LogModelService;
import com.voole.parrot.service.service.logmeta.LogModelVersionService;
import com.voole.parrot.shared.entity.hbasemeta.HbaseTableColumn;
import com.voole.parrot.shared.entity.hbasemeta.HbaseTableVersion;
import com.voole.parrot.shared.entity.kafka.KafkaTopicFixedModelVersion;
import com.voole.parrot.shared.entity.logmeta.LogModel;
import com.voole.parrot.shared.entity.logmeta.LogModel.LogModelType;
import com.voole.parrot.shared.entity.logmeta.LogModelGroupColumn;
import com.voole.parrot.shared.entity.logmeta.LogModelLeafColumn;
import com.voole.parrot.shared.entity.logmeta.LogModelRootColumn;
import com.voole.parrot.shared.entity.logmeta.LogModelVersion;

/**
 * @author XuehuiHe
 * @date 2013年12月27日
 */
@org.springframework.stereotype.Service
public class CtypeLogModelMetaAnalyzer extends LogModelMetaAnalyzer {
	@Autowired
	private SimpleDao simpleDao;
	@Autowired
	private LogModelService modelService;
	@Autowired
	private LogModelVersionService versionService;
	@Autowired
	private LogModelGroupColumnService groupColumnService;
	@Autowired
	private KafkaTopicFixedModelVersionService fixedModelVersionService;

	public CtypeLogModelMetaAnalyzer() {
	}

	@Transactional
	public void analyze() {
		List<TopCtype> list = getTopCtypes();
		for (TopCtype topCtype : list) {
			analyze(topCtype);
		}
	}

	public HbaseTableVersion getHbaseTableVersion(String table) {
		HbaseTableVersion hbaseTableVersion = (HbaseTableVersion) simpleDao
				.getCurrSession().createCriteria(HbaseTableVersion.class)
				.createAlias("table", "table")
				.add(Restrictions.eq("version", "0.0"))
				.add(Restrictions.eq("table.name", table)).uniqueResult();
		Hibernate.initialize(hbaseTableVersion.getColumns());
		return hbaseTableVersion;

	}

	public void analyze(TopCtype topCtype) {
		LogModelRootColumn rootColumn = createTableAndVersion(topCtype
				.getTable());
		HbaseTableVersion hbaseTableVersion = rootColumn.getHbaseTableVersion();
		Map<String, HbaseTableColumn> hbaseColumnMap = getColumnsMap(hbaseTableVersion);
		create(topCtype.getOrders(), topCtype.getData(), rootColumn,
				hbaseColumnMap);

		KafkaTopicFixedModelVersion topic = new KafkaTopicFixedModelVersion();
		topic.setCharset(getCharset());
		topic.setName(topCtype.getTopic());
		topic.setVersion(rootColumn.getVersion());
		fixedModelVersionService.persist(topic);

		modelService.getEntityDao().getCurrSession().flush();
		modelService.getEntityDao().getCurrSession().clear();

	}

	private Map<String, HbaseTableColumn> getColumnsMap(
			HbaseTableVersion hbaseTableVersion) {
		Map<String, HbaseTableColumn> hbaseColumnMap = new HashMap<String, HbaseTableColumn>();
		for (HbaseTableColumn hbaseColumn : hbaseTableVersion.getColumns()) {
			hbaseColumnMap.put(hbaseColumn.getName(), hbaseColumn);
		}
		return hbaseColumnMap;
	}

	private void create(List<String> orders,
			Map<String, ? extends CtypeColumn> data,
			LogModelGroupColumn rootColumn,
			Map<String, HbaseTableColumn> hbaseColumnMap) {
		for (String logColumn : orders) {
			createColumn(data, rootColumn, hbaseColumnMap, logColumn);

		}
	}

	protected void createColumn(Map<String, ? extends CtypeColumn> data,
			LogModelGroupColumn rootColumn,
			Map<String, HbaseTableColumn> hbaseColumnMap, String logColumn) {
		CtypeColumn ctypeColumn = data.get(logColumn);
		if (ctypeColumn instanceof SimpleCtypeColumn) {
			String hbaseColumn = ((SimpleCtypeColumn) ctypeColumn)
					.getColumn();
			LogModelLeafColumn leafColumn = new LogModelLeafColumn();
			leafColumn.setName(logColumn);
			leafColumn.setDesc(logColumn);
			HbaseTableColumn hbaseTableColumn = hbaseColumnMap
					.get(hbaseColumn);
			leafColumn.setHbaseTableColumn(hbaseTableColumn);
			leafColumn.setType(hbaseTableColumn.getType());
			leafColumn.setParent(rootColumn);

			groupColumnService.createColumn(leafColumn);
		} else {
			AttachmentsCtypeColumn attachmentsCtypeColumn = (AttachmentsCtypeColumn) ctypeColumn;
			String name = attachmentsCtypeColumn.getTable();
			HbaseTableVersion hbaseTableVersion = getHbaseTableVersion(name);
			LogModelGroupColumn groupColumn = new LogModelGroupColumn();
			groupColumn.setDesc(logColumn);
			groupColumn.setName(logColumn);
			groupColumn.setHbaseTableVersion(hbaseTableVersion);
			groupColumn.setParent(rootColumn);

			groupColumn = (LogModelGroupColumn) groupColumnService
					.createColumn(groupColumn);
			modelService.getEntityDao().getCurrSession().flush();
			modelService.getEntityDao().getCurrSession().clear();
			create(attachmentsCtypeColumn.getOrders(),
					attachmentsCtypeColumn.getData(), groupColumn,
					getColumnsMap(hbaseTableVersion));

		}
		modelService.getEntityDao().getCurrSession().flush();
		modelService.getEntityDao().getCurrSession().clear();
	}

	public LogModelRootColumn createTableAndVersion(String name) {
		LogModel model = new LogModel();
		model.setName(name);
		model.setDesc(name);
		model.setType(LogModelType.CTYPE);
		model.setVersions(new ArrayList<LogModelVersion>());

		model = modelService.persist(model);

		modelService.getEntityDao().getCurrSession().clear();

		LogModelVersion version = new LogModelVersion();
		version.setModel(model);
		version.setVersion("0.0");
		version.setDesc("0.0");
		version.sortChildren();
		version.getRootColumn()
				.setHbaseTableVersion(getHbaseTableVersion(name));

		version = modelService.createVersion(version);

		modelService.getEntityDao().getCurrSession().clear();

		return version.getRootColumn();
	}

	@SuppressWarnings("unchecked")
	public List<TopCtype> getTopCtypes() {
		String json = "";
		try {
			json = readJsonFile(getJsonFile());
		} catch (IOException e) {
			e.printStackTrace();
		}
		GsonBuilder gb = new GsonBuilder();
		gb.registerTypeAdapterFactory(CtypeColumnTypeAdapter.FACTORY);
		Gson gson = gb.create();
		List<TopCtype> list = (List<TopCtype>) gson.fromJson(json,
				new TypeToken<List<TopCtype>>() {
				}.getType());
		return list;
	}

}
