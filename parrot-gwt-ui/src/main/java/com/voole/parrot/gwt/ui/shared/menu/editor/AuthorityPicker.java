/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.parrot.gwt.ui.shared.menu.editor;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.voole.parrot.gwt.common.shared.core.grid.GridBuilder;
import com.voole.parrot.gwt.common.shared.core.grid.GridBuilder.RpcPagingProxyLoad;
import com.voole.parrot.gwt.common.shared.core.window.FixedWindow;
import com.voole.parrot.gwt.common.shared.gridcolumn.AuthorityColumnConfig;
import com.voole.parrot.gwt.common.shared.property.PropertyUtils;
import com.voole.parrot.shared.entity.authority.Authority;
import com.voole.parrot.shared.grid.GwtPagingLoadConfigBean;
import com.voole.parrot.shared.grid.QueryCondition;

/**
 * @author XuehuiHe
 * @date 2013年12月20日
 */
public class AuthorityPicker extends FixedWindow {
	private final Grid<Authority> grid;

	public AuthorityPicker() {
		List<ColumnConfig<Authority, ?>> columns = new ArrayList<ColumnConfig<Authority, ?>>();
		columns.add(new ColumnConfig<Authority, String>(
				PropertyUtils.AuthorityProperty.entranceName()));
		columns.add(AuthorityColumnConfig.name());

		GridBuilder<Authority> gb = new GridBuilder<Authority>(
				PropertyUtils.AuthorityProperty.key());
		gb.disableMultiSelect();
		gb.addColumns(columns);

		gb.setPagingProxyLoad(new RpcPagingProxyLoad<Authority, QueryCondition, PagingLoadResult<Authority>>() {

			@Override
			public void load(
					GwtPagingLoadConfigBean<QueryCondition> loadConfig,
					AsyncCallback<PagingLoadResult<Authority>> callback) {

			}
		});

		grid = gb.create();

		this.add(grid);
		this.add(gb.getPagingToolBar(grid));

	}
}
