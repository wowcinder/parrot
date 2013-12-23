/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.parrot.service.conditionprocessor;

import org.hibernate.Criteria;

import com.sencha.gxt.data.shared.loader.ListLoadConfigBean;
import com.voole.parrot.service.dao.ISimpleDao.QueryConditionAnalyzer;
import com.voole.parrot.shared.condition.QueryCondition;
import com.voole.parrot.shared.grid.ConditionLoadConfig;

/**
 * @author XuehuiHe
 * @date 2013年12月23日
 */
public class GwtListLoadConfigBeanConditionProcessor<Condition extends QueryCondition, T extends ListLoadConfigBean & ConditionLoadConfig<Condition>>
		extends ConditionProcessor<Condition> {
	private final T configBean;
	private final QueryConditionAnalyzer<Condition> conditionAnalyzer;

	protected GwtListLoadConfigBeanConditionProcessor(Criteria criteria,
			T configBean, QueryConditionAnalyzer<Condition> conditionAnalyzer) {
		super(criteria, configBean.getCondition(), conditionAnalyzer);
		this.configBean = configBean;
		this.conditionAnalyzer = conditionAnalyzer;
	}

	@Override
	public void process() {
		addOrder(configBean);
		super.process();
	}

	public T getConfigBean() {
		return configBean;
	}

	public QueryConditionAnalyzer<Condition> getConditionAnalyzer() {
		return conditionAnalyzer;
	}

}
