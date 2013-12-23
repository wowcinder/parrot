package com.voole.parrot.shared.grid;

import com.sencha.gxt.data.shared.loader.PagingLoadConfigBean;
import com.voole.parrot.shared.condition.QueryCondition;

public class GwtPagingLoadConfigBean<Condition extends QueryCondition> extends
		PagingLoadConfigBean implements ConditionLoadConfig<Condition> {
	private static final long serialVersionUID = 8925370681075443720L;

	public GwtPagingLoadConfigBean() {
		this(0, 50);
	}

	public GwtPagingLoadConfigBean(int offset, int limit) {
		super(offset, limit);
	}

	private Condition condition;

	public void setCondition(Condition condition) {
		this.condition = condition;
	}

	public Condition getCondition() {
		return condition;
	}
}
