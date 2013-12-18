package com.voole.parrot.shared.grid;

public interface ConditionLoadConfig<Condition extends QueryCondition> {
	public void setCondition(Condition condition);
	public Condition getCondition();
}
