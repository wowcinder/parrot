package com.voole.parrot.shared.grid;

import com.voole.parrot.shared.condition.QueryCondition;

public interface ConditionLoadConfig<Condition extends QueryCondition> {
	public void setCondition(Condition condition);
	public Condition getCondition();
}
