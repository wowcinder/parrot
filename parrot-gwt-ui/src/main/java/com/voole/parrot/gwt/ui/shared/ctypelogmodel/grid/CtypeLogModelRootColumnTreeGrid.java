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
import com.voole.parrot.shared.entity.logmeta.LogModelColumn;
import com.voole.parrot.shared.entity.logmeta.LogModelGroupColumn;
import com.voole.parrot.shared.entity.logmeta.LogModelLeafColumn;
import com.voole.parrot.shared.entity.logmeta.LogModelRootColumn;
import com.voole.parrot.shared.entity.logmeta.LogModelVersion;

/**
 * @author XuehuiHe
 * @date 2013年12月26日
 */
public class CtypeLogModelRootColumnTreeGrid extends
		FixedTreeGrid<LogModelColumn> {

	private static ColumnModel<LogModelColumn> cm;
	private static ColumnConfig<LogModelColumn, String> name;

	static {
		List<ColumnConfig<LogModelColumn, ?>> l = new ArrayList<ColumnConfig<LogModelColumn, ?>>();
		name = CtypeLogModelColumnColumnConfig.name();
		ColumnConfig<LogModelColumn, String> type = new ColumnConfig<LogModelColumn, String>(
				new TreeValueProvider<LogModelColumn, String>() {

					@Override
					protected String _getValue(LogModelColumn object) {
						if (object instanceof LogModelGroupColumn) {
							return null;
						}
						LogModelLeafColumn leaf = (LogModelLeafColumn) object;
						if (leaf.getType() != null) {
							return leaf.getType().name();
						}
						return null;

					}
				}, 200, "type");
		ColumnConfig<LogModelColumn, String> table = new ColumnConfig<LogModelColumn, String>(
				new TreeValueProvider<LogModelColumn, String>() {

					@Override
					protected String _getValue(LogModelColumn object) {
						if (object instanceof LogModelGroupColumn) {
							LogModelGroupColumn group = (LogModelGroupColumn) object;
							if (group.getHbaseTableVersion() != null
									&& group.getHbaseTableVersion().getTable() != null) {
								return group.getHbaseTableVersion().getTable()
										.getName();
							}
							return null;
						} else {
							LogModelLeafColumn leaf = (LogModelLeafColumn) object;
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
		ColumnConfig<LogModelColumn, String> version = new ColumnConfig<LogModelColumn, String>(
				new TreeValueProvider<LogModelColumn, String>() {

					@Override
					protected String _getValue(LogModelColumn object) {
						if (object instanceof LogModelGroupColumn) {
							LogModelGroupColumn group = (LogModelGroupColumn) object;
							if (group.getHbaseTableVersion() != null) {
								return group.getHbaseTableVersion()
										.getVersion();
							}
							return null;
						} else {
							LogModelLeafColumn leaf = (LogModelLeafColumn) object;
							if (leaf.getHbaseTableColumn() != null
									&& leaf.getHbaseTableColumn().getVersion() != null) {
								return leaf.getHbaseTableColumn().getVersion()
										.getVersion();
							}

						}
						return null;
					}
				}, 200, "version");
		ColumnConfig<LogModelColumn, String> column = new ColumnConfig<LogModelColumn, String>(
				new TreeValueProvider<LogModelColumn, String>() {

					@Override
					protected String _getValue(LogModelColumn object) {
						if (object instanceof LogModelGroupColumn) {
							return null;
						}
						LogModelLeafColumn leaf = (LogModelLeafColumn) object;
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

		cm = new ColumnModel<LogModelColumn>(l);
	}

	private final LogModelVersion version;

	public CtypeLogModelRootColumnTreeGrid(LogModelVersion version) {
		super(new TreeStore<LogModelColumn>(
				PropertyUtils.CtypeLogModelColumnProperty.key()), cm, name);
		this.version = version;
		setContextMenu(new CtypeLogModelRootColumnTreeGridMenu(this));
		setIconProvider(new FixedTreeGridIconProvider<LogModelColumn>(this) {
			@Override
			protected boolean isFolderModel(LogModelColumn model) {
				if (model instanceof LogModelGroupColumn) {
					return true;
				}
				return false;
			}
		});
		getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

		new TreeGridDragSource<LogModelColumn>(this);
		FixedTreeGridTargetLeafDrop<LogModelColumn> target = new FixedTreeGridTargetLeafDrop<LogModelColumn>(
				this) {

			@Override
			protected void appendModel(final LogModelColumn p,
					List<?> items, int index) {
				if (items.size() == 0)
					return;
				final Integer pos = index;
				@SuppressWarnings("unchecked")
				List<TreeStore.TreeNode<LogModelColumn>> nodes = (List<TreeStore.TreeNode<LogModelColumn>>) items;
				LogModelColumn column = nodes.get(0).getData();
				column.setParent((LogModelGroupColumn) p);
				column.setPos(pos);
				RpcServiceUtils.CtypeLogModelRpcService.changeColumnsPos(
						column, new RpcAsyncCallback<LogModelColumn>() {
							@Override
							protected void _onSuccess(LogModelColumn result) {
								if (result instanceof LogModelLeafColumn) {
									getWidget().getTreeStore().insert(p, pos,
											result);
								} else {
									LogModelGroupColumn group = (LogModelGroupColumn) result;
									TreeStore<LogModelColumn> treeStore = getWidget()
											.getTreeStore();
									treeStore.insert(p, pos, group);
									for (LogModelColumn item : group
											.getColumns()) {
										initData(group, item);
									}

								}
							}
						});

			}

			@SuppressWarnings("unused")
			protected void update(LogModelColumn p,
					List<TreeStore.TreeNode<LogModelColumn>> nodes,
					int index) {
				getWidget().getTreeStore().addSubTree(p, index, nodes);
			}

			@Override
			protected boolean isDropOnLeafEnabled(LogModelColumn m) {
				if (m instanceof LogModelGroupColumn) {
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
								new RpcAsyncCallback<LogModelRootColumn>() {
									@Override
									protected void _onSuccess(
											LogModelRootColumn result) {
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

	private void initData(LogModelRootColumn root) {
		initData(null, root);
		expandAll();
	}

	private void initData(LogModelGroupColumn group,
			LogModelColumn column) {
		if (group == null) {
			treeStore.add(column);
		} else {
			treeStore.add(group, column);
		}
		if (column instanceof LogModelGroupColumn) {
			LogModelGroupColumn itemGroup = (LogModelGroupColumn) column;
			for (LogModelColumn itemColumn : itemGroup.getColumns()) {
				initData(itemGroup, itemColumn);
			}
		}
	}
}
