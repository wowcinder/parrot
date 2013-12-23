/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.parrot.service.dao.conditionprocessor;

import org.hibernate.Criteria;

import com.voole.parrot.service.dao.ISimpleDao.QueryConditionAnalyzer;
import com.voole.parrot.shared.condition.QueryCondition;
import com.voole.parrot.shared.grid.GwtListLoadConfigBean;

/**
 * @author XuehuiHe
 * @date 2013年12月23日
 */
public class GwtListConditionProcessor<Condition extends QueryCondition>
		extends ConditionProcessor<Condition> {
	private final GwtListLoadConfigBean<Condition> configBean;
	private final QueryConditionAnalyzer<Condition> conditionAnalyzer;

	public GwtListConditionProcessor(Criteria criteria,
			GwtListLoadConfigBean<Condition> configBean,
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

	public GwtListLoadConfigBean<Condition> getConfigBean() {
		return configBean;
	}

	public QueryConditionAnalyzer<Condition> getConditionAnalyzer() {
		return conditionAnalyzer;
	}

}
