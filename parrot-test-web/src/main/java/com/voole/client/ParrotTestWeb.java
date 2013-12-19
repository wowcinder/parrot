package com.voole.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.cell.client.Cell;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.text.shared.AbstractSafeHtmlRenderer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.cell.core.client.SimpleSafeHtmlCell;
import com.sencha.gxt.data.shared.loader.ListLoadResult;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.voole.parrot.gwt.common.shared.RpcAsyncCallback;
import com.voole.parrot.gwt.common.shared.core.grid.GridBuilder;
import com.voole.parrot.gwt.common.shared.core.grid.GridBuilder.RpcListProxyLoad;
import com.voole.parrot.gwt.common.shared.gridcolumn.MenuColumnConfig;
import com.voole.parrot.gwt.common.shared.property.PropertyUtils;
import com.voole.parrot.gwt.common.shared.rpcservice.RpcServiceUtils;
import com.voole.parrot.shared.entity.menu.Menu;
import com.voole.parrot.shared.entity.menu.MenuGroup;
import com.voole.parrot.shared.entity.menu.MenuNode;
import com.voole.parrot.shared.grid.GwtListLoadConfigBean;
import com.voole.parrot.shared.grid.QueryCondition;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class ParrotTestWeb implements EntryPoint {

	public void onModuleLoad() {
		Scheduler.get().scheduleDeferred(new ScheduledCommand() {
			@Override
			public void execute() {
				// createMenus();
				// showGrid();

				getMenus();
			}
		});

	}

	/**
	 * 
	 */
	protected void getMenus() {
		RpcServiceUtils.OpenAuthorizeRpcService
				.getUserMenus(new AsyncCallback<MenuGroup>() {

					@Override
					public void onFailure(Throwable caught) {

					}

					@Override
					public void onSuccess(MenuGroup result) {
						System.out.println(result.getName().length());
					}
				});
	}

	protected void showGrid() {
		GridBuilder<Menu> gb = new GridBuilder<Menu>(
				PropertyUtils.MenuProperty.key());
		List<ColumnConfig<Menu, ?>> columns = new ArrayList<ColumnConfig<Menu, ?>>();
		columns.add(MenuColumnConfig.id());
		columns.add(MenuColumnConfig.name());
		columns.add(MenuColumnConfig.token());
		ColumnConfig<Menu, MenuGroup> mg = MenuColumnConfig.parent();
		// mg.setCell(new SimpleHt);
		mg.setHeader("test");
		Cell<MenuGroup> cell = new SimpleSafeHtmlCell<MenuGroup>(
				new AbstractSafeHtmlRenderer<MenuGroup>() {

					@Override
					public SafeHtml render(MenuGroup object) {
						if (object != null) {
							return SafeHtmlUtils.fromString(object.getName());
						}
						return null;
					}
				});
		mg.setCell(cell);
		columns.add(mg);

		ColumnConfig<Menu, String> parentName = new ColumnConfig<Menu, String>(
				PropertyUtils.MenuProperty.parentName(), 200, "parentName");
		columns.add(parentName);
		gb.addColumns(columns);
		// gb.enableMultiSelect();
		// gb.disableMultiSelect();
		// gb.setShowDataImmediately(false);
		gb.setPageSize(2);
		// gb.setPagingProxyLoad(new RpcPagingProxyLoad<Menu, QueryCondition,
		// PagingLoadResult<Menu>>() {
		//
		// @Override
		// public void load(
		// GwtPagingLoadConfigBean<QueryCondition> loadConfig,
		// final AsyncCallback<PagingLoadResult<Menu>> callback) {
		// RpcServiceUtils.MenuNodeRpcService.paging(loadConfig, callback);
		// }
		// });
		gb.setListProxyLoad(new RpcListProxyLoad<Menu, QueryCondition, ListLoadResult<Menu>>() {

			@Override
			public void load(GwtListLoadConfigBean<QueryCondition> loadConfig,
					AsyncCallback<ListLoadResult<Menu>> callback) {
				RpcServiceUtils.MenuNodeRpcService.list(loadConfig, callback);
			}
		});
		Grid<Menu> grid = gb.create();
		RootPanel.get().add(grid);
		// RootPanel.get().add(gb.getPagingToolBar(grid));
	}

	private void createMenus() {
		List<MenuNode> list = new ArrayList<MenuNode>();
		MenuGroup mg = new MenuGroup();
		mg.setName("test-mg");
		mg.setNodes(list);

		for (int i = 0; i < 10; i++) {
			Menu menu = new Menu();
			menu.setName("test-name-" + i);
			menu.setToken("test-token-" + i);
			menu.setParent(mg);
			list.add(menu);
		}
		RpcServiceUtils.MenuNodeRpcService.save(mg,
				new RpcAsyncCallback<MenuGroup>() {

					@Override
					protected void _onSuccess(MenuGroup result) {
					}
				});
	}
}
