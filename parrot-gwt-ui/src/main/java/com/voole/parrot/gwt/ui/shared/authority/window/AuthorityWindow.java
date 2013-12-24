package com.voole.parrot.gwt.ui.shared.authority.window;

import java.util.ArrayList;
import java.util.List;

import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutPack;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.voole.parrot.gwt.common.shared.GwtCallBack;
import com.voole.parrot.gwt.common.shared.RpcAsyncCallback;
import com.voole.parrot.gwt.common.shared.VerticalLayoutDataUtil;
import com.voole.parrot.gwt.common.shared.core.window.FixedWindow;
import com.voole.parrot.gwt.common.shared.rpcservice.RpcServiceUtils;
import com.voole.parrot.gwt.ui.shared.authority.tree.AuthorityCheckTree;
import com.voole.parrot.shared.entity.EntityHasAutoId;
import com.voole.parrot.shared.entity.authority.Authority;

public class AuthorityWindow extends FixedWindow {
	private final AuthorityCheckTree checkTree;
	protected final VerticalLayoutContainer layoutContainer;
	private final TextButton okBt;

	public AuthorityWindow(Authority authority) {
		this.checkTree = new AuthorityCheckTree(authority);
		layoutContainer = new VerticalLayoutContainer();
		layoutContainer.setHeight(400);
		layoutContainer.setWidth(500);
		layoutContainer.add(checkTree, VerticalLayoutDataUtil.mainVd);
		this.setWidget(layoutContainer);
		okBt = new TextButton("OK");
		this.addButton(okBt);
		this.setButtonAlign(BoxLayoutPack.END);

		okBt.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				okBt.disable();
				save(checkTree.getCheckedSelection());
			}
		});
	}

	protected void save(List<EntityHasAutoId> checkNodes) {
		List<Authority> authorities = new ArrayList<Authority>();
		for (EntityHasAutoId entityHasAutoId : checkNodes) {
			if (entityHasAutoId instanceof Authority) {
				authorities.add((Authority) entityHasAutoId);
			}
		}
		GwtCallBack<Void> callBack = getSaveCallBack();
		RpcServiceUtils.AuthorityRpcService.setDependencies(
				checkTree.getAuthority(), authorities,
				RpcAsyncCallback.dealWith(callBack));
	}

	protected GwtCallBack<Void> getSaveCallBack() {
		return new GwtCallBack<Void>() {
			@Override
			protected void _succeed() {
				AuthorityWindow.this.hide();
			}

			@Override
			protected void post() {
				super.post();
				okBt.enable();
			}

			@Override
			protected void _cancel() {
				super._cancel();
				AuthorityWindow.this.hide();
			}
		};
	}
}
