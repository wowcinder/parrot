package com.voole.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.text.shared.AbstractSafeHtmlRenderer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.cell.core.client.SimpleSafeHtmlCell;
import com.sencha.gxt.cell.core.client.form.DateCell;
import com.sencha.gxt.data.shared.loader.ListLoadResult;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.DateField;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.voole.parrot.gwt.common.shared.GwtCallBack;
import com.voole.parrot.gwt.common.shared.RpcAsyncCallback;
import com.voole.parrot.gwt.common.shared.core.event.EditEvent;
import com.voole.parrot.gwt.common.shared.core.grid.GridBuilder;
import com.voole.parrot.gwt.common.shared.core.grid.GridBuilder.RpcListProxyLoad;
import com.voole.parrot.gwt.common.shared.gridcolumn.MenuColumnConfig;
import com.voole.parrot.gwt.common.shared.property.PropertyUtils;
import com.voole.parrot.gwt.common.shared.rpcservice.RpcServiceUtils;
import com.voole.parrot.gwt.ui.shared.authority.tree.AuthorityTree;
import com.voole.parrot.gwt.ui.shared.menu.LeftMenuView;
import com.voole.parrot.gwt.ui.shared.menu.editor.AuthorityField;
import com.voole.parrot.gwt.ui.shared.menu.editor.MenuEditor;
import com.voole.parrot.shared.entity.authority.Authority;
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
				// AuthorityPicker picker = new AuthorityPicker();
				// picker.show();

				// testField1();

				// testField2();

				// MenuTree menuView = new MenuTree(new TreeStore<MenuNode>(
				// PropertyUtils.MenuNodeProperty.key()));
				AuthorityTree tree = new AuthorityTree();
				RootPanel.get().add(tree);
//				RootPanel.get().add(menuView);

				// testDateField();
				// showTestField();
				// createMenus();
				// showGrid();

				// getMenus();
				// showMenuView();
			}

			private void testField2() {
				TextButton tb = new TextButton("add");
				RootPanel.get().add(tb);
				tb.addSelectHandler(new SelectHandler() {

					@Override
					public void onSelect(SelectEvent event) {
						MenuEditor editor = new MenuEditor();
						editor.fireEditEvent(new EditEvent<Menu>(new Menu(),
								new GwtCallBack<Menu>() {

									@Override
									protected void _succeed() {
										Window.alert(getResult().getId() + "");
									}
								}));
					}
				});
			}

			private void testField1() {
				final AuthorityField field = new AuthorityField();
				RootPanel.get().add(field);

				TextButton tb = new TextButton("test");
				tb.addSelectHandler(new SelectHandler() {

					@Override
					public void onSelect(SelectEvent event) {
						field.flush();
						Authority authority = field.getValue();
						Window.alert(authority == null ? "null" : "not null");
						Window.alert(authority == null ? "null" : authority
								.getName());

					}
				});
				RootPanel.get().add(tb);
			}

		});

	}

	private void testDateField() {
		RootPanel.get().add(new DateField(new DateCell() {
			@Override
			protected void onNavigationKey(
					com.google.gwt.cell.client.Cell.Context context,
					Element parent, Date value, NativeEvent event,
					ValueUpdater<Date> valueUpdater) {
				System.out.println(event.getKeyCode());
				super.onNavigationKey(context, parent, value, event,
						valueUpdater);
			}
		}));
	}

	public static void main(String[] args) {
	}

	/**
	 * 
	 */
	protected void showTestField() {
		TestBeanEditor e = new TestBeanEditor();
		RootPanel.get().add(e);
	}

	protected void showMenuView() {
		LeftMenuView mv = new LeftMenuView();
		RootPanel.get().add(mv);
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

		columns.add(MenuColumnConfig.parent_name());
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
		RpcServiceUtils.MenuNodeRpcService.create(mg,
				new RpcAsyncCallback<MenuGroup>() {

					@Override
					protected void _onSuccess(MenuGroup result) {
					}
				});
	}
}
