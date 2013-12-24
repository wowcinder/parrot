package com.voole.parrot.gwt.ui.shared.authority.window;

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
import com.voole.parrot.gwt.common.shared.gridcolumn.UserColumnConfig;
import com.voole.parrot.gwt.common.shared.property.PropertyUtils;
import com.voole.parrot.gwt.common.shared.rpcservice.RpcServiceUtils;
import com.voole.parrot.shared.condition.QueryCondition;
import com.voole.parrot.shared.entity.authority.Authority;
import com.voole.parrot.shared.entity.user.User;
import com.voole.parrot.shared.grid.GwtListLoadConfigBean;

public class AuthorityUsersWindow extends FixedWindow {
	private final Authority authority;
	private final Grid<User> grid;
	private final TextButton tb;

	public AuthorityUsersWindow(Authority authority2) {
		authority = authority2;
		GridBuilder<User> gb = new GridBuilder<User>(
				PropertyUtils.UserProperty.key());
		gb.addColumn(UserColumnConfig.id());
		gb.addColumn(UserColumnConfig.name());
		gb.setListProxyLoad(new RpcListProxyLoad<User, QueryCondition, ListLoadResult<User>>() {

			@Override
			public void load(GwtListLoadConfigBean<QueryCondition> loadConfig,
					AsyncCallback<ListLoadResult<User>> callback) {
				RpcServiceUtils.UserRpcService.list(loadConfig, callback);
			}

			@Override
			protected void postSuccess() {
				super.postSuccess();
				Scheduler.get().scheduleDeferred(new ScheduledCommand() {
					@Override
					public void execute() {
						selectRoleUsers();
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
				tb.disable();
				List<User> list = grid.getSelectionModel().getSelectedItems();
				Set<User> set = new HashSet<User>();
				set.addAll(list);
				authority.setUsers(set);
				RpcServiceUtils.AuthorityRpcService.modifyAuthorityUsers(
						authority, new RpcAsyncCallback<Void>() {
							@Override
							protected void _onSuccess(Void result) {
								AuthorityUsersWindow.this.hide();
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

	protected void selectRoleUsers() {
		grid.mask("加载中...");
		RpcServiceUtils.AuthorityRpcService.getAuthorityUsers(authority,
				new RpcAsyncCallback<Set<User>>() {

					@Override
					protected void _onSuccess(Set<User> result) {
						List<User> list = new ArrayList<User>();
						for (User user : result) {
							list.add(grid.getStore().findModel(user));
						}
						grid.getSelectionModel().select(list, true);
					}

					@Override
					public void post() {
						super.post();
						grid.unmask();
					}
				});
	}
}
