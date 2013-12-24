package com.voole.parrot.gwt.ui.shared.user.window;

import java.util.Set;

import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.voole.parrot.gwt.common.shared.RpcAsyncCallback;
import com.voole.parrot.gwt.common.shared.rpcservice.RpcServiceUtils;
import com.voole.parrot.gwt.ui.shared.authority.window.AuthorityCheckTreeWindow;
import com.voole.parrot.shared.entity.authority.Authority;
import com.voole.parrot.shared.entity.user.User;

public class UserAuthoritiesWindow extends AuthorityCheckTreeWindow<User> {

	public UserAuthoritiesWindow(User t2) {
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
				RpcServiceUtils.UserRpcService.modifyUserAuthorities(getT(),
						new RpcAsyncCallback<Void>() {

							@Override
							protected void _onSuccess(Void result) {
								UserAuthoritiesWindow.this.hide();
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
		RpcServiceUtils.UserRpcService.getUserAuthorities(getT(),
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
