package com.voole.parrot.gwt.common.shared.core.grid;

import com.sencha.gxt.data.shared.loader.DataProxy;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoader;
import com.voole.parrot.shared.condition.QueryCondition;
import com.voole.parrot.shared.grid.GwtPagingLoadConfigBean;

public class GwtPagingLoader<T, PC extends QueryCondition, PR extends PagingLoadResult<T>>
		extends PagingLoader<GwtPagingLoadConfigBean<PC>, PR> {
	private PC condition;

	public void setCondition(PC condition) {
		this.condition = condition;
	}

	public PC getCondition() {
		return condition;
	}

	public GwtPagingLoader(DataProxy<GwtPagingLoadConfigBean<PC>, PR> proxy) {
		super(proxy);
	}

	public boolean load(GwtPagingLoadConfigBean<PC> loadConfig) {
		loadConfig.setCondition(getCondition());
		return super.load(loadConfig);
	}

	protected GwtPagingLoadConfigBean<PC> newLoadConfig() {
		return new GwtPagingLoadConfigBean<PC>();
	}
}
