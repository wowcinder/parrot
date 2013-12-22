package com.voole.parrot.gwt.ui.shared.authority.tree;

import java.util.List;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.TreeStore;
import com.sencha.gxt.widget.core.client.tree.Tree;
import com.voole.parrot.gwt.common.shared.RpcAsyncCallback;
import com.voole.parrot.gwt.common.shared.rpcservice.RpcServiceUtils;
import com.voole.parrot.shared.entity.EntityHasAutoId;
import com.voole.parrot.shared.entity.authority.Authority;
import com.voole.parrot.shared.entity.authority.AuthorityEntrance;

public class AuthorityTree extends Tree<EntityHasAutoId, String> {

	public AuthorityTree() {
		super(new TreeStore<EntityHasAutoId>(
				new ModelKeyProvider<EntityHasAutoId>() {

					@Override
					public String getKey(EntityHasAutoId item) {
						if (item instanceof Authority) {
							return "authority_" + item.getId();
						} else {
							return "entrence_" + item.getId();
						}
					}
				}), new ValueProvider<EntityHasAutoId, String>() {

			@Override
			public String getValue(EntityHasAutoId item) {
				if (item instanceof Authority) {
					return ((Authority) item).getName();
				} else {
					return ((AuthorityEntrance) item).getName();
				}
			}

			@Override
			public void setValue(EntityHasAutoId object, String value) {

			}

			@Override
			public String getPath() {
				return "name";
			}
		});
		this.setContextMenu(new AuthorityTreeMenu(this));
	}

	@Override
	protected void onAfterFirstAttach() {
		super.onAfterFirstAttach();
		init();
	}

	public void init() {
		Scheduler.get().scheduleDeferred(new ScheduledCommand() {

			@Override
			public void execute() {
				RpcServiceUtils.AuthorityRpcService
						.getEntrances(new RpcAsyncCallback<List<AuthorityEntrance>>() {
							@Override
							protected void _onSuccess(
									List<AuthorityEntrance> result) {
								initData(result);
							}
						});
			}
		});
	}

	public void initData(List<AuthorityEntrance> entrances) {
		store.clear();
		for (AuthorityEntrance entrance : entrances) {
			initData(null, entrance);
		}
	}

	protected void initData(AuthorityEntrance parent,
			EntityHasAutoId entraceNode) {
		if (entraceNode == null) {
			return;
		}
		if (parent == null) {
			store.add(entraceNode);
		} else {
			store.add(parent, entraceNode);
		}
		if (entraceNode instanceof AuthorityEntrance) {
			List<Authority> nodes = ((AuthorityEntrance) entraceNode)
					.getAuthorities();
			if (nodes == null || nodes.size() == 0) {
				return;
			}
			for (Authority node : nodes) {
				initData((AuthorityEntrance) entraceNode, node);
			}
		}
	}

}
