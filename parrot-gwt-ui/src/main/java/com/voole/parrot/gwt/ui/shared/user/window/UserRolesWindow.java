package com.voole.parrot.gwt.ui.shared.user.window;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.data.shared.loader.ListLoadResult;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutPack;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.voole.parrot.gwt.common.shared.RpcAsyncCallback;
import com.voole.parrot.gwt.common.shared.core.grid.GridBuilder;
import com.voole.parrot.gwt.common.shared.core.grid.GridBuilder.RpcListProxyLoad;
import com.voole.parrot.gwt.common.shared.core.window.FixedWindow;
import com.voole.parrot.gwt.common.shared.gridcolumn.RoleColumnConfig;
import com.voole.parrot.gwt.common.shared.property.PropertyUtils;
import com.voole.parrot.gwt.common.shared.rpcservice.RpcServiceUtils;
import com.voole.parrot.shared.condition.QueryCondition;
import com.voole.parrot.shared.entity.authority.Role;
import com.voole.parrot.shared.entity.user.User;
import com.voole.parrot.shared.grid.GwtListLoadConfigBean;

public class UserRolesWindow extends FixedWindow {
	private final User user;
	private final Grid<Role> grid;
	private final TextButton tb;

	public UserRolesWindow(User user2) {
		this.user = user2;

		GridBuilder<Role> gb = new GridBuilder<Role>(
				PropertyUtils.RoleProperty.key());
		gb.addColumn(RoleColumnConfig.id());
		gb.addColumn(RoleColumnConfig.name());
		gb.setListProxyLoad(new RpcListProxyLoad<Role, QueryCondition, ListLoadResult<Role>>() {
			@Override
			public void load(GwtListLoadConfigBean<QueryCondition> loadConfig,
					AsyncCallback<ListLoadResult<Role>> callback) {
				RpcServiceUtils.RoleRpcService.list(loadConfig, callback);
			}

			@Override
			protected void postSuccess() {
				super.postSuccess();
				Scheduler.get().scheduleDeferred(new ScheduledCommand() {
					@Override
					public void execute() {
						getUserRoles();
					}
				});

			}
		});

		grid = gb.create();
		grid.setHeight(400);
		setWidget(grid);

		tb = new TextButton("提交");
		addButton(tb);
		setButtonAlign(BoxLayoutPack.CENTER);

		tb.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				List<Role> list = grid.getSelectionModel().getSelectedItems();
				Set<Role> set = new HashSet<Role>();
				set.addAll(list);
				user.setRoles(set);

				tb.disable();
				RpcServiceUtils.UserRpcService.modifyUserRoles(user,
						new RpcAsyncCallback<Void>() {
							@Override
							protected void _onSuccess(Void result) {
								UserRolesWindow.this.hide();
							}

							@Override
							public void post() {
								super.post();
								tb.enable();
							}
						});
			}
		});
	}

	protected void getUserRoles() {
		grid.mask();
		RpcServiceUtils.UserRpcService.getUserRoles(user,
				new RpcAsyncCallback<Set<Role>>() {
					@Override
					protected void _onSuccess(Set<Role> result) {
						if (result != null && result.size() > 0) {
							final List<Role> roles = new ArrayList<Role>();
							for (Role role : result) {
								roles.add(grid.getStore().findModel(role));
							}
							grid.getSelectionModel().select(
									roles, true);
						}
					}

					@Override
					public void post() {
						super.post();
						grid.unmask();
					}
				});
	}
}
