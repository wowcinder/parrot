/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.parrot.gwt.ui.shared.ctypelogmodel.grid;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.sencha.gxt.core.client.Style.SelectionMode;
import com.sencha.gxt.data.shared.TreeStore;
import com.sencha.gxt.dnd.core.client.DND.Feedback;
import com.sencha.gxt.dnd.core.client.TreeGridDragSource;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.voole.parrot.gwt.common.shared.RpcAsyncCallback;
import com.voole.parrot.gwt.common.shared.core.cell.TreeValueProvider;
import com.voole.parrot.gwt.common.shared.core.dnd.FixedTreeGridTargetLeafDrop;
import com.voole.parrot.gwt.common.shared.core.grid.FixedTreeGrid;
import com.voole.parrot.gwt.common.shared.core.tree.FixedTreeGridIconProvider;
import com.voole.parrot.gwt.common.shared.gridcolumn.CtypeLogModelColumnColumnConfig;
import com.voole.parrot.gwt.common.shared.property.PropertyUtils;
import com.voole.parrot.gwt.common.shared.rpcservice.RpcServiceUtils;
import com.voole.parrot.shared.entity.ctypelogmeta.CtypeLogModelColumn;
import com.voole.parrot.shared.entity.ctypelogmeta.CtypeLogModelGroupColumn;
import com.voole.parrot.shared.entity.ctypelogmeta.CtypeLogModelLeafColumn;
import com.voole.parrot.shared.entity.ctypelogmeta.CtypeLogModelRootColumn;
import com.voole.parrot.shared.entity.ctypelogmeta.CtypeLogModelVersion;

/**
 * @author XuehuiHe
 * @date 2013年12月26日
 */
public class CtypeLogModelRootColumnTreeGrid extends
		FixedTreeGrid<CtypeLogModelColumn> {

	private static ColumnModel<CtypeLogModelColumn> cm;
	private static ColumnConfig<CtypeLogModelColumn, String> name;

	static {
		List<ColumnConfig<CtypeLogModelColumn, ?>> l = new ArrayList<ColumnConfig<CtypeLogModelColumn, ?>>();
		name = CtypeLogModelColumnColumnConfig.name();
		ColumnConfig<CtypeLogModelColumn, String> type = new ColumnConfig<CtypeLogModelColumn, String>(
				new TreeValueProvider<CtypeLogModelColumn, String>() {

					@Override
					protected String _getValue(CtypeLogModelColumn object) {
						if (object instanceof CtypeLogModelGroupColumn) {
							return null;
						}
						CtypeLogModelLeafColumn leaf = (CtypeLogModelLeafColumn) object;
						if (leaf.getType() != null) {
							return leaf.getType().name();
						}
						return null;

					}
				}, 200, "type");
		ColumnConfig<CtypeLogModelColumn, String> table = new ColumnConfig<CtypeLogModelColumn, String>(
				new TreeValueProvider<CtypeLogModelColumn, String>() {

					@Override
					protected String _getValue(CtypeLogModelColumn object) {
						if (object instanceof CtypeLogModelGroupColumn) {
							CtypeLogModelGroupColumn group = (CtypeLogModelGroupColumn) object;
							if (group.getHbaseTableVersion() != null
									&& group.getHbaseTableVersion().getTable() != null) {
								return group.getHbaseTableVersion().getTable()
										.getName();
							}
							return null;
						} else {
							CtypeLogModelLeafColumn leaf = (CtypeLogModelLeafColumn) object;
							if (leaf.getHbaseTableColumn() != null
									&& leaf.getHbaseTableColumn().getVersion() != null
									&& leaf.getHbaseTableColumn().getVersion()
											.getTable() != null) {
								return leaf.getHbaseTableColumn().getVersion()
										.getTable().getName();
							}

						}
						return null;
					}
				}, 200, "table");
		ColumnConfig<CtypeLogModelColumn, String> version = new ColumnConfig<CtypeLogModelColumn, String>(
				new TreeValueProvider<CtypeLogModelColumn, String>() {

					@Override
					protected String _getValue(CtypeLogModelColumn object) {
						if (object instanceof CtypeLogModelGroupColumn) {
							CtypeLogModelGroupColumn group = (CtypeLogModelGroupColumn) object;
							if (group.getHbaseTableVersion() != null) {
								return group.getHbaseTableVersion()
										.getVersion();
							}
							return null;
						} else {
							CtypeLogModelLeafColumn leaf = (CtypeLogModelLeafColumn) object;
							if (leaf.getHbaseTableColumn() != null
									&& leaf.getHbaseTableColumn().getVersion() != null) {
								return leaf.getHbaseTableColumn().getVersion()
										.getVersion();
							}

						}
						return null;
					}
				}, 200, "version");
		ColumnConfig<CtypeLogModelColumn, String> column = new ColumnConfig<CtypeLogModelColumn, String>(
				new TreeValueProvider<CtypeLogModelColumn, String>() {

					@Override
					protected String _getValue(CtypeLogModelColumn object) {
						if (object instanceof CtypeLogModelGroupColumn) {
							return null;
						}
						CtypeLogModelLeafColumn leaf = (CtypeLogModelLeafColumn) object;
						if (leaf.getHbaseTableColumn() != null) {
							return leaf.getHbaseTableColumn().getName();
						}
						return null;
					}
				}, 200, "column");
		l.add(name);
		l.add(CtypeLogModelColumnColumnConfig.desc());
		l.add(type);
		l.add(table);
		l.add(version);
		l.add(column);

		cm = new ColumnModel<CtypeLogModelColumn>(l);
	}

	private final CtypeLogModelVersion version;

	public CtypeLogModelRootColumnTreeGrid(CtypeLogModelVersion version) {
		super(new TreeStore<CtypeLogModelColumn>(
				PropertyUtils.CtypeLogModelColumnProperty.key()), cm, name);
		this.version = version;
		setContextMenu(new CtypeLogModelRootColumnTreeGridMenu(this));
		setIconProvider(new FixedTreeGridIconProvider<CtypeLogModelColumn>(this) {
			@Override
			protected boolean isFolderModel(CtypeLogModelColumn model) {
				if (model instanceof CtypeLogModelGroupColumn) {
					return true;
				}
				return false;
			}
		});
		getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

		new TreeGridDragSource<CtypeLogModelColumn>(this);
		FixedTreeGridTargetLeafDrop<CtypeLogModelColumn> target = new FixedTreeGridTargetLeafDrop<CtypeLogModelColumn>(
				this) {

			@Override
			protected void appendModel(final CtypeLogModelColumn p,
					List<?> items, int index) {
				if (items.size() == 0)
					return;
				final Integer pos = index;
				@SuppressWarnings("unchecked")
				List<TreeStore.TreeNode<CtypeLogModelColumn>> nodes = (List<TreeStore.TreeNode<CtypeLogModelColumn>>) items;
				CtypeLogModelColumn column = nodes.get(0).getData();
				column.setParent((CtypeLogModelGroupColumn) p);
				column.setPos(pos);
				RpcServiceUtils.CtypeLogModelRpcService.changeColumnsPos(
						column, new RpcAsyncCallback<CtypeLogModelColumn>() {
							@Override
							protected void _onSuccess(CtypeLogModelColumn result) {
								if (result instanceof CtypeLogModelLeafColumn) {
									getWidget().getTreeStore().insert(p, pos,
											result);
								} else {
									CtypeLogModelGroupColumn group = (CtypeLogModelGroupColumn) result;
									TreeStore<CtypeLogModelColumn> treeStore = getWidget()
											.getTreeStore();
									treeStore.insert(p, pos, group);
									for (CtypeLogModelColumn item : group
											.getColumns()) {
										initData(group, item);
									}

								}
							}
						});

			}

			@SuppressWarnings("unused")
			protected void update(CtypeLogModelColumn p,
					List<TreeStore.TreeNode<CtypeLogModelColumn>> nodes,
					int index) {
				getWidget().getTreeStore().addSubTree(p, index, nodes);
			}

			@Override
			protected boolean isDropOnLeafEnabled(CtypeLogModelColumn m) {
				if (m instanceof CtypeLogModelGroupColumn) {
					return true;
				}
				return false;
			}
		};
		target.setAllowDropOnLeaf(true);
		target.setAllowDropOnRoot(false);
		target.setAllowSelfAsSource(true);
		target.setFeedback(Feedback.BOTH);
	}

	@Override
	protected void onAfterFirstAttach() {
		super.onAfterFirstAttach();
		Scheduler.get().scheduleDeferred(new ScheduledCommand() {

			@Override
			public void execute() {
				mask("加载中...");
				RpcServiceUtils.CtypeLogModelRpcService
						.getVersionRootColumnWithChildren(
								version,
								new RpcAsyncCallback<CtypeLogModelRootColumn>() {
									@Override
									protected void _onSuccess(
											CtypeLogModelRootColumn result) {
										initData(result);
									}

									@Override
									public void post() {
										super.post();
										unmask();
									}
								});

			}
		});
	}

	private void initData(CtypeLogModelRootColumn root) {
		initData(null, root);
		expandAll();
	}

	private void initData(CtypeLogModelGroupColumn group,
			CtypeLogModelColumn column) {
		if (group == null) {
			treeStore.add(column);
		} else {
			treeStore.add(group, column);
		}
		if (column instanceof CtypeLogModelGroupColumn) {
			CtypeLogModelGroupColumn itemGroup = (CtypeLogModelGroupColumn) column;
			for (CtypeLogModelColumn itemColumn : itemGroup.getColumns()) {
				initData(itemGroup, itemColumn);
			}
		}
	}
}
