package com.voole.parrot.gwt.ui.shared.role;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.cell.core.client.TextButtonCell;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;
import com.voole.parrot.gwt.common.shared.GwtCallBack;
import com.voole.parrot.gwt.common.shared.RpcAsyncCallback;
import com.voole.parrot.gwt.common.shared.VerticalLayoutDataUtil;
import com.voole.parrot.gwt.common.shared.core.cell.FixedTextValueProvider;
import com.voole.parrot.gwt.common.shared.core.event.EditEvent;
import com.voole.parrot.gwt.common.shared.core.grid.GridBuilder;
import com.voole.parrot.gwt.common.shared.core.grid.GridBuilder.RpcPagingProxyLoad;
import com.voole.parrot.gwt.common.shared.gridcolumn.RoleColumnConfig;
import com.voole.parrot.gwt.common.shared.property.PropertyUtils;
import com.voole.parrot.gwt.common.shared.rpcservice.RpcServiceUtils;
import com.voole.parrot.gwt.ui.shared.role.editor.RoleEditor;
import com.voole.parrot.gwt.ui.shared.role.window.RoleAuthoritiesWindow;
import com.voole.parrot.gwt.ui.shared.role.window.RoleUsersWindow;
import com.voole.parrot.shared.condition.QueryCondition;
import com.voole.parrot.shared.entity.authority.Role;
import com.voole.parrot.shared.grid.GwtPagingLoadConfigBean;

public class RoleView extends VerticalLayoutContainer {
	private ToolBar headbar;
	private TextButton addBt;
	private Grid<Role> grid;
	private RoleEditor roleEditor;

	public RoleView() {
		initHeadbar();

		GridBuilder<Role> gb = initGrid();
		this.add(headbar, VerticalLayoutDataUtil.smallVd);
		this.add(grid, VerticalLayoutDataUtil.smallVd);
		this.add(gb.getPagingToolBar(grid), VerticalLayoutDataUtil.smallVd);

	}

	protected GridBuilder<Role> initGrid() {
		GridBuilder<Role> gb = new GridBuilder<Role>(
				PropertyUtils.RoleProperty.key());
		gb.disableMultiSelect();
		List<ColumnConfig<Role, ?>> columns = new ArrayList<ColumnConfig<Role, ?>>();
		columns.add(RoleColumnConfig.id());
		columns.add(RoleColumnConfig.name());
		columns.add(getModifyNameCc());
		columns.add(getDeleteCc());
		columns.add(getAuthoritiesCc());
		columns.add(getUsersCc());
		gb.addColumns(columns);
		gb.setPagingProxyLoad(new RpcPagingProxyLoad<Role, QueryCondition, PagingLoadResult<Role>>() {
			@Override
			public void load(
					GwtPagingLoadConfigBean<QueryCondition> loadConfig,
					AsyncCallback<PagingLoadResult<Role>> callback) {
				RpcServiceUtils.RoleRpcService.paging(loadConfig, callback);
			}
		});

		grid = gb.create();
		return gb;
	}

	protected ColumnConfig<Role, String> getDeleteCc() {
		ColumnConfig<Role, String> cc = new ColumnConfig<Role, String>(
				new FixedTextValueProvider<Role>("删除"), 200, "删除");
		cc.setSortable(false);
		TextButtonCell bt = new TextButtonCell();
		cc.setCell(bt);
		bt.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				final Role role = getRow(event);
				grid.mask("删除中...");
				RpcServiceUtils.RoleRpcService.deleteRole(role,
						new RpcAsyncCallback<Void>() {
							@Override
							protected void _onSuccess(Void result) {
								grid.getStore().remove(role);
							}

							@Override
							public void post() {
								super.post();
								grid.unmask();
							}
						});
			}
		});
		return cc;
	}

	protected ColumnConfig<Role, String> getAuthoritiesCc() {
		ColumnConfig<Role, String> cc = new ColumnConfig<Role, String>(
				new FixedTextValueProvider<Role>("权限管理"), 200, "权限管理");
		cc.setSortable(false);
		TextButtonCell bt = new TextButtonCell();
		cc.setCell(bt);
		bt.addSelectHandler(new SelectHandler() {

			@Override
			public void onSelect(SelectEvent event) {
				Role role = getRow(event);
				RoleAuthoritiesWindow window = new RoleAuthoritiesWindow(role);
				window.show();
			}
		});
		return cc;
	}

	protected ColumnConfig<Role, String> getUsersCc() {
		ColumnConfig<Role, String> cc = new ColumnConfig<Role, String>(
				new FixedTextValueProvider<Role>("用户管理"), 200, "用户管理");
		cc.setSortable(false);
		TextButtonCell bt = new TextButtonCell();
		cc.setCell(bt);
		bt.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				Role role = getRow(event);
				RoleUsersWindow window = new RoleUsersWindow(role);
				window.show();
			}
		});
		return cc;
	}

	protected ColumnConfig<Role, String> getModifyNameCc() {
		ColumnConfig<Role, String> cc = new ColumnConfig<Role, String>(
				new FixedTextValueProvider<Role>("修改名称"), 200, "修改名称");
		cc.setSortable(false);
		TextButtonCell bt = new TextButtonCell();
		cc.setCell(bt);

		bt.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				Role role = getRow(event);
				getRoleEditor().fireEditEvent(
						new EditEvent<Role>(role, new GwtCallBack<Role>() {

							@Override
							protected void _succeed() {
								grid.getStore().update(getResult());
							}
						}, true));
			}
		});

		return cc;
	}

	protected Role getRow(SelectEvent event) {
		return grid.getStore().get(event.getContext().getIndex());
	}

	protected void initHeadbar() {
		headbar = new ToolBar();
		addBt = new TextButton("添加");
		headbar.add(addBt);

		addBt.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				final Role role = new Role();
				getRoleEditor().fireEditEvent(
						new EditEvent<Role>(role, new GwtCallBack<Role>() {
							@Override
							protected void _succeed() {
								grid.getStore().add(0, getResult());
							}
						}));
			}
		});
	}

	public RoleEditor getRoleEditor() {
		if (roleEditor == null) {
			roleEditor = new RoleEditor();
		}
		return roleEditor;
	}
}
