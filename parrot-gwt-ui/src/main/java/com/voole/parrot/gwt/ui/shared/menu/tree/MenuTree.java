/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.parrot.gwt.ui.shared.menu.tree;

import java.util.List;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.sencha.gxt.data.shared.TreeStore;
import com.sencha.gxt.dnd.core.client.DND.Feedback;
import com.sencha.gxt.dnd.core.client.TreeDragSource;
import com.sencha.gxt.dnd.core.client.TreeDropTarget;
import com.sencha.gxt.widget.core.client.tree.Tree;
import com.voole.parrot.gwt.common.shared.RpcAsyncCallback;
import com.voole.parrot.gwt.common.shared.core.tree.FixedIconProvider;
import com.voole.parrot.gwt.common.shared.property.PropertyUtils;
import com.voole.parrot.gwt.common.shared.rpcservice.RpcServiceUtils;
import com.voole.parrot.shared.entity.menu.MenuGroup;
import com.voole.parrot.shared.entity.menu.MenuNode;

/**
 * @author XuehuiHe
 * @date 2013年8月23日
 */
public class MenuTree extends Tree<MenuNode, String> {
	public MenuTree() {
		super(new TreeStore<MenuNode>(PropertyUtils.MenuNodeProperty.key()),
				PropertyUtils.MenuNodeProperty.name());
		setIconProvider(new FixedIconProvider<MenuNode>(this) {
			@Override
			protected boolean isFolderModel(MenuNode model) {
				return model instanceof MenuGroup;
			}
		});
		initDND();
		setContextMenu(new GxtMenu(this));
	}

	private void initDND() {
		@SuppressWarnings("unused")
		TreeDragSource<MenuNode> source = new TreeDragSource<MenuNode>(this);
		TreeDropTarget<MenuNode> target = new MenuTreeDropTarget(this);
		target.setAllowSelfAsSource(true);
		target.setFeedback(Feedback.BOTH);
	}

	public void initData(List<MenuNode> result) {
		store.clear();
		for (MenuNode menuNode : result) {
			initData(null, menuNode);
		}

	}

	@Override
	protected void onAfterFirstAttach() {
		super.onAfterFirstAttach();
		Scheduler.get().scheduleDeferred(new ScheduledCommand() {

			@Override
			public void execute() {
				reset();
			}
		});
	}

	public void reset() {
		RpcServiceUtils.OpenAuthorizeRpcService
				.getUserMenus(new RpcAsyncCallback<MenuGroup>() {
					@Override
					protected void _onSuccess(MenuGroup result) {
						if (result != null) {
							initData(result.getNodes());
						}
					}
				});
	}

	protected void initData(MenuNode parent, MenuNode menuNode) {
		if (menuNode == null) {
			return;
		}
		if (parent == null) {
			store.add(menuNode);
		} else {
			store.add(parent, menuNode);
		}
		if (menuNode instanceof MenuGroup) {
			List<MenuNode> nodes = ((MenuGroup) menuNode).getNodes();
			if (nodes == null || nodes.size() == 0) {
				return;
			}
			for (MenuNode node : nodes) {
				initData(menuNode, node);
			}
		}
	}

}
