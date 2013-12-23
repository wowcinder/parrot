/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.parrot.service.dao.conditionprocessor;

import org.hibernate.Criteria;

import com.voole.parrot.service.dao.ISimpleDao.QueryConditionAnalyzer;
import com.voole.parrot.shared.condition.QueryCondition;
import com.voole.parrot.shared.grid.GwtPagingLoadConfigBean;

/**
 * @author XuehuiHe
 * @date 2013年12月23日
 */
public class GwtPagingConditionProcessor<Condition extends QueryCondition>
		extends ConditionProcessor<Condition> {

	private final GwtPagingLoadConfigBean<Condition> configBean;
	private final QueryConditionAnalyzer<Condition> conditionAnalyzer;

	public GwtPagingConditionProcessor(Criteria criteria,
			GwtPagingLoadConfigBean<Condition> configBean,
			QueryConditionAnalyzer<Condition> conditionAnalyzer) {
		super(criteria, configBean.getCondition(), conditionAnalyzer);
		this.configBean = configBean;
		this.conditionAnalyzer = conditionAnalyzer;
	}

	@Override
	public void process() {
		addOrder(configBean);
		super.process();
	}

	public GwtPagingLoadConfigBean<Condition> getConfigBean() {
		return configBean;
	}

	public QueryConditionAnalyzer<Condition> getConditionAnalyzer() {
		return conditionAnalyzer;
	}

}
