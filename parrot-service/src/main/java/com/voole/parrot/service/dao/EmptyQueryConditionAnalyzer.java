package com.voole.parrot.service.dao;

import org.hibernate.Criteria;

import com.voole.parrot.service.dao.ISimpleDao.QueryConditionAnalyzer;
import com.voole.parrot.shared.grid.QueryCondition;

public class EmptyQueryConditionAnalyzer<Condition extends QueryCondition> implements QueryConditionAnalyzer<Condition>{
	@Override
	public void analyze(Criteria criteria, Condition condition) {

	}
}
