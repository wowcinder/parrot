/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.parrot.service.dao.conditionprocessor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;

import com.sencha.gxt.data.shared.SortDir;
import com.sencha.gxt.data.shared.SortInfoBean;
import com.sencha.gxt.data.shared.loader.ListLoadConfigBean;
import com.voole.parrot.service.dao.ISimpleDao.QueryConditionAnalyzer;
import com.voole.parrot.shared.condition.QueryCondition;

/**
 * @author XuehuiHe
 * @date 2013年12月23日
 */
public class ConditionProcessor<Condition extends QueryCondition> {
	private final Criteria criteria;
	private final Set<String> aliases;
	private final Condition condition;
	private final QueryConditionAnalyzer<Condition> queryConditionAnalyzer;

	public ConditionProcessor(Criteria criteria, Condition condition,
			QueryConditionAnalyzer<Condition> queryConditionAnalyzer) {
		this.criteria = criteria;
		this.condition = condition;
		this.queryConditionAnalyzer = queryConditionAnalyzer;
		this.aliases = new HashSet<String>();
	}

	public void process() {
		queryConditionAnalyzer.analyze(criteria, condition, this);
	}

	public Criteria getCriteria() {
		return criteria;
	}

	public void createAlias(String path, int joinType) {
		if (!aliases.contains(path)) {
			this.criteria.createAlias(path, path, joinType);
			aliases.add(path);
		}
	}

	public void createAlias(String path) {
		createAlias(path, Criteria.LEFT_JOIN);
	}

	public Condition getCondition() {
		return condition;
	}

	protected void addOrder(ListLoadConfigBean configBean) {
		List<SortInfoBean> sortInfos = configBean.getSortInfo();
		createAlias(criteria, sortInfos);
		for (SortInfoBean sortInfo : sortInfos) {
			criteria.addOrder(getOrder(sortInfo));
		}
	}

	protected void createAlias(Criteria criteria, List<SortInfoBean> sortInfos) {
		for (SortInfoBean sortInfo : sortInfos) {
			String propertyName = sortInfo.getSortField();
			List<String> paths = getAliases(propertyName);
			for (String path : paths) {
				createAlias(path);
			}
		}
	}

	protected List<String> getAliases(String path) {
		List<String> aliases = new ArrayList<String>();
		if (path.indexOf(".") != -1) {
			String[] paths = path.split("\\.");
			int len = paths.length;
			String prev = null;
			for (int i = 0; i < len - 1; i++) {
				String itemPath = paths[i];
				if (i == 0) {
					aliases.add(itemPath);
				} else {
					aliases.add(prev + "." + itemPath);
				}
				prev = itemPath;
			}
		}
		return aliases;
	}

	protected Order getOrder(SortInfoBean sortInfo) {
		String propertyName = sortInfo.getSortField();
		if (isAsc(sortInfo.getSortDir())) {
			return Order.asc(propertyName);
		} else {
			return Order.desc(propertyName);
		}
	}

	protected boolean isAsc(SortDir sortDir) {
		if (sortDir != null) {
			if (sortDir == SortDir.ASC) {
				return true;
			}
			return false;
		}
		return true;
	}
}
