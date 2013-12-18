package com.voole.parrot.shared.grid;

import com.sencha.gxt.data.shared.loader.ListLoadConfigBean;

public class GwtListLoadConfigBean<Condition extends QueryCondition> extends
		ListLoadConfigBean implements ConditionLoadConfig<Condition> {
	private static final long serialVersionUID = -8865897211248041688L;
	private Condition condition;

	public void setCondition(Condition condition) {
		this.condition = condition;
	}

	public Condition getCondition() {
		return condition;
	}
}
