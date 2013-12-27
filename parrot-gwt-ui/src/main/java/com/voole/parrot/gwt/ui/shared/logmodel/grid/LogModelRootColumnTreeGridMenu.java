/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.parrot.gwt.ui.shared.logmodel.grid;

import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.sencha.gxt.data.shared.TreeStore;
import com.sencha.gxt.widget.core.client.event.BeforeShowEvent;
import com.sencha.gxt.widget.core.client.event.BeforeShowEvent.BeforeShowHandler;
import com.sencha.gxt.widget.core.client.event.HideEvent;
import com.sencha.gxt.widget.core.client.event.HideEvent.HideHandler;
import com.sencha.gxt.widget.core.client.menu.Item;
import com.sencha.gxt.widget.core.client.menu.Menu;
import com.sencha.gxt.widget.core.client.menu.MenuItem;
import com.sencha.gxt.widget.core.client.menu.SeparatorMenuItem;
import com.voole.parrot.gwt.common.shared.GwtCallBack;
import com.voole.parrot.gwt.common.shared.RpcAsyncCallback;
import com.voole.parrot.gwt.common.shared.core.event.EditEvent;
import com.voole.parrot.gwt.common.shared.rpcservice.RpcServiceUtils;
import com.voole.parrot.gwt.ui.shared.logmodel.editor.LogModelGroupColumnEditor;
import com.voole.parrot.gwt.ui.shared.logmodel.editor.LogModelLeafColumnEditor;
import com.voole.parrot.shared.entity.EntityHasAutoId;
import com.voole.parrot.shared.entity.logmeta.LogModelColumn;
import com.voole.parrot.shared.entity.logmeta.LogModelGroupColumn;
import com.voole.parrot.shared.entity.logmeta.LogModelLeafColumn;
import com.voole.parrot.shared.entity.logmeta.LogModelRootColumn;

/**
 * @author XuehuiHe
 * @date 2013年12月26日
 */
public class LogModelRootColumnTreeGridMenu extends Menu {
	private final LogModelRootColumnTreeGrid grid;

	private final MenuItem addGroup;
	private final MenuItem modifyGroup;
	private final MenuItem del;
	private final MenuItem addLeaf;
	private final MenuItem modifyLeaf;

	public LogModelRootColumnTreeGridMenu(
			LogModelRootColumnTreeGrid grid2) {
		grid = grid2;
		modifyGroup = new MenuItem("修改group");
		add(modifyGroup);
		modifyLeaf = new MenuItem("修改leaf");
		add(modifyLeaf);
		add(new SeparatorMenuItem());
		addGroup = new MenuItem("添加group");
		add(addGroup);
		addLeaf = new MenuItem("添加leaf");
		add(addLeaf);
		add(new SeparatorMenuItem());
		del = new MenuItem("删除");
		add(del);
		initAddGroup();
		initModifyGroup();
		initDel();
		initAddLeaf();
		initModifyLeaf();
		addBeforeShowHandler(new BeforeShowHandler() {
			@Override
			public void onBeforeShow(BeforeShowEvent event) {
				EntityHasAutoId entity = getGrid().getSelectionModel()
						.getSelectedItem();
				if (entity == null) {
					LogModelRootColumnTreeGridMenu.this.disable();
				} else if (entity instanceof LogModelGroupColumn) {
					modifyLeaf.disable();
					if (entity instanceof LogModelRootColumn) {
						del.disable();
					}
				} else {
					addGroup.disable();
					modifyGroup.disable();
					addLeaf.disable();
				}
			}
		});

		addHideHandler(new HideHandler() {
			@Override
			public void onHide(HideEvent event) {
				LogModelRootColumnTreeGridMenu.this.enable();
				addGroup.enable();
				modifyGroup.enable();
				del.enable();
				addLeaf.enable();
				modifyLeaf.enable();
			}
		});
	}

	protected void initAddGroup() {
		addGroup.addSelectionHandler(new SelectionHandler<Item>() {
			@Override
			public void onSelection(SelectionEvent<Item> event) {
				final LogModelGroupColumn parent = getSeletedGroup();
				LogModelGroupColumn group = new LogModelGroupColumn();
				group.setParent(parent);
				getGroupEditor().fireEditEvent(
						new EditEvent<LogModelGroupColumn>(group,
								new GwtCallBack<LogModelGroupColumn>() {
									@Override
									protected void _succeed() {
										getGrid().getTreeStore().add(parent,
												getResult());
									}
								}));
			}
		});
	}

	protected void initModifyGroup() {
		modifyGroup.addSelectionHandler(new SelectionHandler<Item>() {
			@Override
			public void onSelection(SelectionEvent<Item> event) {
				LogModelGroupColumn group = getSeletedGroup();
				getGroupEditor().fireEditEvent(
						new EditEvent<LogModelGroupColumn>(group,
								new GwtCallBack<LogModelGroupColumn>() {
									@Override
									protected void _succeed() {
										LogModelGroupColumn group = getResult();
										TreeStore<LogModelColumn> treeStroe = getGrid()
												.getTreeStore();
										treeStroe.update(group);
										if (group.getColumns() != null
												&& group.getColumns().size() > 0) {
											for (LogModelColumn column : group
													.getColumns()) {
												if (column instanceof LogModelLeafColumn) {
													treeStroe.update(column);
												}
											}
										}

									}
								}, true));
			}
		});
	}

	protected void initDel() {
		del.addSelectionHandler(new SelectionHandler<Item>() {
			@Override
			public void onSelection(SelectionEvent<Item> event) {
				final LogModelColumn group = getGrid().getSelectionModel()
						.getSelectedItem();
				getGrid().mask("删除中...");
				RpcServiceUtils.LogModelRpcService.deleteColumn(group,
						new RpcAsyncCallback<Void>() {
							@Override
							protected void _onSuccess(Void result) {
								getGrid().getTreeStore().remove(group);
							}

							@Override
							public void post() {
								super.post();
								getGrid().unmask();
							}
						});
			}
		});
	}

	protected void initAddLeaf() {
		addLeaf.addSelectionHandler(new SelectionHandler<Item>() {
			@Override
			public void onSelection(SelectionEvent<Item> event) {
				final LogModelGroupColumn group = getSeletedGroup();
				LogModelLeafColumn leaf = new LogModelLeafColumn();
				leaf.setParent(group);
				getLeafEditor().fireEditEvent(
						new EditEvent<LogModelLeafColumn>(leaf,
								new GwtCallBack<LogModelLeafColumn>() {
									@Override
									protected void _succeed() {
										getGrid().getTreeStore().add(group,
												getResult());
									}
								}));
			}
		});
	}

	protected void initModifyLeaf() {
		modifyLeaf.addSelectionHandler(new SelectionHandler<Item>() {
			@Override
			public void onSelection(SelectionEvent<Item> event) {
				LogModelLeafColumn leaf = getSeletedLeaf();
				getLeafEditor().fireEditEvent(
						new EditEvent<LogModelLeafColumn>(leaf,
								new GwtCallBack<LogModelLeafColumn>() {
									@Override
									protected void _succeed() {
										getGrid().getTreeStore().update(
												getResult());
									}
								}, true));
			}
		});
	}

	public LogModelRootColumnTreeGrid getGrid() {
		return grid;
	}

	protected LogModelGroupColumn getSeletedGroup() {
		return (LogModelGroupColumn) getGrid().getSelectionModel()
				.getSelectedItem();
	}

	protected LogModelLeafColumn getSeletedLeaf() {
		return (LogModelLeafColumn) getGrid().getSelectionModel()
				.getSelectedItem();
	}

	private LogModelGroupColumnEditor groupEditor;

	public LogModelGroupColumnEditor getGroupEditor() {
		if (groupEditor == null) {
			groupEditor = new LogModelGroupColumnEditor();
		}
		return groupEditor;
	}

	private LogModelLeafColumnEditor leafEditor;

	public LogModelLeafColumnEditor getLeafEditor() {
		if (leafEditor == null) {
			leafEditor = new LogModelLeafColumnEditor();
		}
		return leafEditor;
	}
}
