/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.parrot.gwt.ui.shared.menu.tree;

import java.util.ArrayList;
import java.util.List;

import com.sencha.gxt.data.shared.TreeStore;
import com.voole.parrot.gwt.common.shared.RpcAsyncCallback;
import com.voole.parrot.gwt.common.shared.core.tree.FixedTreeDropTarget;
import com.voole.parrot.gwt.common.shared.rpcservice.RpcServiceUtils;
import com.voole.parrot.shared.entity.menu.MenuGroup;
import com.voole.parrot.shared.entity.menu.MenuNode;

/**
 * @author XuehuiHe
 * @date 2013年8月23日
 */
public class MenuTreeDropTarget extends FixedTreeDropTarget<MenuNode> {

	public MenuTreeDropTarget(MenuTree tree) {
		super(tree);
		setAllowDropOnLeaf(true);
	}

	protected void appendModel(final MenuNode p, List<?> items, final int index) {
		if (items.size() == 0)
			return;
		@SuppressWarnings("unchecked")
		final List<TreeStore.TreeNode<MenuNode>> nodes = (List<TreeStore.TreeNode<MenuNode>>) items;
		final List<MenuNode> menuNodes = new ArrayList<MenuNode>();
		for (TreeStore.TreeNode<MenuNode> treeNode : nodes) {
			menuNodes.add(treeNode.getData());
		}
		RpcServiceUtils.MenuNodeRpcService.move(p, menuNodes, index,
				new RpcAsyncCallback<List<MenuNode>>() {

					@Override
					protected void _onSuccess(List<MenuNode> result) {
						TreeStore<MenuNode> store = getWidget().getStore();
						update(p, nodes, index);
						for (MenuNode menuNode : result) {
							store.update(menuNode);
						}
					}

					public void _onFailure(Throwable caught) {
						super._onFailure(caught);
						((MenuTree) getWidget()).reset();
					}
				});
	}

	protected void update(MenuNode p, List<TreeStore.TreeNode<MenuNode>> nodes,
			int index) {
		if (p == null) {
			getWidget().getStore().addSubTree(index, nodes);
		} else {
			getWidget().getStore().addSubTree(p, index, nodes);
		}
	}

	@Override
	protected boolean isDropOnLeafEnabled(MenuNode m) {
		if (m instanceof MenuGroup) {
			return true;
		}
		return false;
	}
}
