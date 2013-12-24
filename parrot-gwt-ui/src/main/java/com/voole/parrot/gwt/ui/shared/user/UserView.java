package com.voole.parrot.gwt.ui.shared.user;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.cell.core.client.TextButtonCell;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.widget.core.client.box.PromptMessageBox;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.event.HideEvent;
import com.sencha.gxt.widget.core.client.event.HideEvent.HideHandler;
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
import com.voole.parrot.gwt.common.shared.gridcolumn.UserColumnConfig;
import com.voole.parrot.gwt.common.shared.property.PropertyUtils;
import com.voole.parrot.gwt.common.shared.rpcservice.RpcServiceUtils;
import com.voole.parrot.gwt.ui.shared.user.editor.UserEditor;
import com.voole.parrot.gwt.ui.shared.user.window.UserAuthoritiesWindow;
import com.voole.parrot.gwt.ui.shared.user.window.UserRolesWindow;
import com.voole.parrot.shared.condition.QueryCondition;
import com.voole.parrot.shared.entity.user.User;
import com.voole.parrot.shared.grid.GwtPagingLoadConfigBean;

public class UserView extends VerticalLayoutContainer {
	private ToolBar headbar;
	private TextButton addUserBt;
	private Grid<User> grid;
	private UserEditor userEditor;
	private TextButtonCell modifyPasswordBt;
	private TextButtonCell modifyRolesBt;
	private TextButtonCell modifyAuthoritiesBt;
	private TextButtonCell deleteBt;

	public UserView() {
		initHeadbar();
		GridBuilder<User> gb = initGrid();
		this.add(headbar, VerticalLayoutDataUtil.smallVd);
		this.add(grid, VerticalLayoutDataUtil.smallVd);
		this.add(gb.getPagingToolBar(grid), VerticalLayoutDataUtil.smallVd);

	}

	protected GridBuilder<User> initGrid() {
		GridBuilder<User> gb = new GridBuilder<User>(
				PropertyUtils.UserProperty.key());
		List<ColumnConfig<User, ?>> columns = new ArrayList<ColumnConfig<User, ?>>();
		columns.add(UserColumnConfig.id());
		columns.add(UserColumnConfig.name());
		columns.add(getModifyPasswordCc());
		columns.add(getModifyRolesCc());
		columns.add(getModifyAuthoritiesCc());
		columns.add(getDeleteCc());
		gb.addColumns(columns);
		gb.setPagingProxyLoad(new RpcPagingProxyLoad<User, QueryCondition, PagingLoadResult<User>>() {
			@Override
			public void load(
					GwtPagingLoadConfigBean<QueryCondition> loadConfig,
					AsyncCallback<PagingLoadResult<User>> callback) {
				RpcServiceUtils.UserRpcService.paging(loadConfig, callback);
			}
		});
		gb.disableMultiSelect();

		grid = gb.create();
		return gb;
	}

	protected void initHeadbar() {
		headbar = new ToolBar();
		addUserBt = new TextButton("添加用户");
		headbar.add(addUserBt);
		addUserBt.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				User user = new User();
				getUserEditor().fireEditEvent(
						new EditEvent<User>(user, new GwtCallBack<User>() {
							@Override
							protected void _succeed() {
								grid.getStore().add(0, getResult());
							}
						}));
			}
		});
	}

	private ColumnConfig<User, String> getDeleteCc() {
		ColumnConfig<User, String> cc = new ColumnConfig<User, String>(
				new FixedTextValueProvider<User>("删除"), 200, "删除");
		cc.setSortable(false);
		deleteBt = new TextButtonCell();
		cc.setCell(deleteBt);
		deleteBt.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				int row = event.getContext().getIndex();
				final User user = grid.getStore().get(row);
				grid.mask("删除中...");
				RpcServiceUtils.UserRpcService.delete(user,
						new RpcAsyncCallback<Void>() {
							@Override
							protected void _onSuccess(Void result) {
								grid.getStore().remove(user);
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

	private ColumnConfig<User, String> getModifyAuthoritiesCc() {
		ColumnConfig<User, String> cc = new ColumnConfig<User, String>(
				new FixedTextValueProvider<User>("修改权限"), 200, "修改权限");
		cc.setSortable(false);
		modifyAuthoritiesBt = new TextButtonCell();
		cc.setCell(modifyAuthoritiesBt);
		modifyAuthoritiesBt.addSelectHandler(new SelectHandler() {

			@Override
			public void onSelect(SelectEvent event) {
				final User user = grid.getStore().get(
						event.getContext().getIndex());
				UserAuthoritiesWindow w = new UserAuthoritiesWindow(user);
				w.show();
			}
		});
		return cc;
	}

	private ColumnConfig<User, String> getModifyRolesCc() {
		ColumnConfig<User, String> cc = new ColumnConfig<User, String>(
				new FixedTextValueProvider<User>("修改角色"), 200, "修改角色");
		cc.setSortable(false);
		modifyRolesBt = new TextButtonCell();
		cc.setCell(modifyRolesBt);

		modifyRolesBt.addSelectHandler(new SelectHandler() {

			@Override
			public void onSelect(SelectEvent event) {
				final User user = grid.getStore().get(
						event.getContext().getIndex());
				UserRolesWindow rolesWindow = new UserRolesWindow(user);
				rolesWindow.show();
			}
		});

		return cc;
	}

	private ColumnConfig<User, String> getModifyPasswordCc() {
		ColumnConfig<User, String> cc = new ColumnConfig<User, String>(
				new FixedTextValueProvider<User>("修改密码"), 200, "修改密码");
		cc.setSortable(false);
		modifyPasswordBt = new TextButtonCell();
		cc.setCell(modifyPasswordBt);

		modifyPasswordBt.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				int row = event.getContext().getIndex();
				final User user = grid.getStore().get(row);
				final PromptMessageBox box = new PromptMessageBox("password",
						"Please enter  new password:");
				box.addHideHandler(new HideHandler() {
					@Override
					public void onHide(HideEvent event) {
						String password = box.getValue();
						user.setPassword(password);
						grid.mask("修改中...");
						RpcServiceUtils.UserRpcService.changePassword(user,
								new RpcAsyncCallback<Void>() {
									@Override
									protected void _onSuccess(Void result) {
									}

									@Override
									public void post() {
										super.post();
										grid.unmask();
									}
								});
					}
				});
				box.show();
			}
		});

		return cc;
	}

	protected UserEditor getUserEditor() {
		if (userEditor == null) {
			userEditor = new UserEditor();
		}
		return userEditor;
	}
}
