/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.parrot.gwt.common.shared.core.grid;

import com.sencha.gxt.data.shared.TreeStore;
import com.sencha.gxt.data.shared.event.StoreUpdateEvent;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.GridView.GridAppearance;
import com.sencha.gxt.widget.core.client.tree.Tree.TreeAppearance;
import com.sencha.gxt.widget.core.client.treegrid.TreeGrid;

/**
 * @author XuehuiHe
 * @date 2013年12月25日
 */
public class FixedTreeGrid<M> extends TreeGrid<M> {
	public static class FixedTreeGridNode<M> extends
			com.sencha.gxt.widget.core.client.treegrid.TreeGrid.TreeGridNode<M> {
		private M model;

		protected FixedTreeGridNode(String id, M m) {
			super(id, m);
			this.model = m;
		}

		@Override
		public M getModel() {
			return model;
		}

		public void setModel(M model) {
			this.model = model;
		}
	}

	public FixedTreeGrid(TreeStore<M> store, ColumnModel<M> cm,
			ColumnConfig<M, ?> treeColumn, GridAppearance appearance,
			TreeAppearance treeAppearance) {
		super(store, cm, treeColumn, appearance, treeAppearance);
	}

	public FixedTreeGrid(TreeStore<M> store, ColumnModel<M> cm,
			ColumnConfig<M, ?> treeColumn) {
		super(store, cm, treeColumn);
	}

	public FixedTreeGrid(TreeStore<M> store, ColumnModel<M> cm,
			ColumnConfig<M, ?> treeColumn, GridAppearance appearance) {
		super(store, cm, treeColumn, appearance);
	}

	protected void onUpdate(StoreUpdateEvent<M> se) {
		for (M m : se.getItems()) {
			if (store.findModel(m) != null) {
				store.update(m);
				FixedTreeGridNode<M> node = (FixedTreeGridNode<M>) findNode(m);
				if (node.getModel() != m) {
					node.setModel(m);
				}
			}
		}
	}

	protected String register(M m) {
		String id = generateModelId(m);
		if (!nodes.containsKey(id)) {
			nodes.put(id, new FixedTreeGridNode<M>(id, m));
		}
		return id;
	}
}
