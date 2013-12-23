/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.parrot.gwt.ui.shared.menu.editor;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutPack;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.voole.parrot.gwt.common.shared.VerticalLayoutDataUtil;
import com.voole.parrot.gwt.common.shared.core.grid.GridBuilder;
import com.voole.parrot.gwt.common.shared.core.grid.GridBuilder.RpcPagingProxyLoad;
import com.voole.parrot.gwt.common.shared.core.window.FixedWindow;
import com.voole.parrot.gwt.common.shared.gridcolumn.AuthorityColumnConfig;
import com.voole.parrot.gwt.common.shared.property.PropertyUtils;
import com.voole.parrot.gwt.common.shared.rpcservice.RpcServiceUtils;
import com.voole.parrot.shared.condition.QueryCondition;
import com.voole.parrot.shared.entity.authority.Authority;
import com.voole.parrot.shared.grid.GwtPagingLoadConfigBean;

/**
 * @author XuehuiHe
 * @date 2013年12月20日
 */
public class AuthorityPicker extends FixedWindow {
	private final Grid<Authority> grid;
	protected final VerticalLayoutContainer layoutContainer;
	private final TextButton cleanBt;

	public AuthorityPicker() {
		List<ColumnConfig<Authority, ?>> columns = new ArrayList<ColumnConfig<Authority, ?>>();
		columns.add(AuthorityColumnConfig.entrance_name());
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
				RpcServiceUtils.AuthorityRpcService
						.paging(loadConfig, callback);
			}
		});

		grid = gb.create();

		layoutContainer = new VerticalLayoutContainer();
		layoutContainer.add(grid, VerticalLayoutDataUtil.mainVd);
		layoutContainer.add(gb.getPagingToolBar(grid),
				VerticalLayoutDataUtil.smallVd);
		layoutContainer.setHeight(400);
		layoutContainer.setWidth(500);

		this.setWidget(layoutContainer);

		cleanBt = new TextButton("clear");

		this.addButton(cleanBt);
		this.setButtonAlign(BoxLayoutPack.END);
	}

	public Grid<Authority> getGrid() {
		return grid;
	}

	public TextButton getCleanBt() {
		return cleanBt;
	}

}
