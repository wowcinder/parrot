package com.voole.parrot.gwt.ui.shared.role.window;

import java.util.Set;

import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.voole.parrot.gwt.common.shared.RpcAsyncCallback;
import com.voole.parrot.gwt.common.shared.rpcservice.RpcServiceUtils;
import com.voole.parrot.gwt.ui.shared.authority.window.AuthorityCheckTreeWindow;
import com.voole.parrot.shared.entity.authority.Authority;
import com.voole.parrot.shared.entity.authority.Role;

public class RoleAuthoritiesWindow extends AuthorityCheckTreeWindow<Role> {

	public RoleAuthoritiesWindow(Role t2) {
		super(t2);
	}

	@Override
	protected SelectHandler getTbSelectHandler() {
		return new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				Set<Authority> authorities = getCheckNodes();
				getT().setAuthorities(authorities);
				getTb().disable();
				RpcServiceUtils.RoleRpcService.modifyRoleAuthorities(getT(),
						new RpcAsyncCallback<Void>() {

							@Override
							protected void _onSuccess(Void result) {
								RoleAuthoritiesWindow.this.hide();
							}

							@Override
							public void post() {
								super.post();
								getTb().enable();
							}
						});
			}
		};
	}

	@Override
	protected void getSelectAuthorities() {
		getTree().mask("加载中...");
		RpcServiceUtils.RoleRpcService.getRoleAuthorities(getT(),
				new RpcAsyncCallback<Set<Authority>>() {
					@Override
					protected void _onSuccess(Set<Authority> result) {
						if (result != null && result.size() > 0) {
							checkNodes(result);
						}
					}

					@Override
					public void post() {
						super.post();
						getTree().unmask();
					}
				});
	}

}
