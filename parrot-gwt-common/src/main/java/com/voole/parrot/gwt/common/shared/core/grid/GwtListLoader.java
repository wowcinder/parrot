package com.voole.parrot.gwt.common.shared.core.grid;

import com.sencha.gxt.data.shared.loader.DataProxy;
import com.sencha.gxt.data.shared.loader.DataReader;
import com.sencha.gxt.data.shared.loader.ListLoadResult;
import com.sencha.gxt.data.shared.loader.ListLoader;
import com.voole.parrot.shared.condition.QueryCondition;
import com.voole.parrot.shared.grid.GwtListLoadConfigBean;

public class GwtListLoader<T, PC extends QueryCondition, PR extends ListLoadResult<T>>
		extends ListLoader<GwtListLoadConfigBean<PC>, PR> {
	private PC condition;

	public GwtListLoader(DataProxy<GwtListLoadConfigBean<PC>, PR> proxy) {
		super(proxy);
	}

	public GwtListLoader(DataProxy<GwtListLoadConfigBean<PC>, T> proxy,
			DataReader<PR, T> reader) {
		super(proxy, reader);
	}

	public boolean load(GwtListLoadConfigBean<PC> loadConfig) {
		loadConfig.setCondition(getCondition());
		return super.load(loadConfig);
	}

	protected GwtListLoadConfigBean<PC> newLoadConfig() {
		return new GwtListLoadConfigBean<PC>();
	}

	public void setCondition(PC condition) {
		this.condition = condition;
	}

	public PC getCondition() {
		return condition;
	}

}
