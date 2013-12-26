/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.parrot.gwt.ui.shared.ctypelogmodel.grid;

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
import com.voole.parrot.gwt.ui.shared.ctypelogmodel.editor.CtypeLogModelGroupColumnEditor;
import com.voole.parrot.gwt.ui.shared.ctypelogmodel.editor.CtypeLogModelLeafColumnEditor;
import com.voole.parrot.shared.entity.EntityHasAutoId;
import com.voole.parrot.shared.entity.ctypelogmeta.CtypeLogModelColumn;
import com.voole.parrot.shared.entity.ctypelogmeta.CtypeLogModelGroupColumn;
import com.voole.parrot.shared.entity.ctypelogmeta.CtypeLogModelLeafColumn;
import com.voole.parrot.shared.entity.ctypelogmeta.CtypeLogModelRootColumn;

/**
 * @author XuehuiHe
 * @date 2013年12月26日
 */
public class CtypeLogModelRootColumnTreeGridMenu extends Menu {
	private final CtypeLogModelRootColumnTreeGrid grid;

	private final MenuItem addGroup;
	private final MenuItem modifyGroup;
	private final MenuItem del;
	private final MenuItem addLeaf;
	private final MenuItem modifyLeaf;

	public CtypeLogModelRootColumnTreeGridMenu(
			CtypeLogModelRootColumnTreeGrid grid2) {
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
					CtypeLogModelRootColumnTreeGridMenu.this.disable();
				} else if (entity instanceof CtypeLogModelGroupColumn) {
					modifyLeaf.disable();
					if (entity instanceof CtypeLogModelRootColumn) {
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
				CtypeLogModelRootColumnTreeGridMenu.this.enable();
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
				final CtypeLogModelGroupColumn parent = getSeletedGroup();
				CtypeLogModelGroupColumn group = new CtypeLogModelGroupColumn();
				group.setParent(parent);
				getGroupEditor().fireEditEvent(
						new EditEvent<CtypeLogModelGroupColumn>(group,
								new GwtCallBack<CtypeLogModelGroupColumn>() {
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
				CtypeLogModelGroupColumn group = getSeletedGroup();
				getGroupEditor().fireEditEvent(
						new EditEvent<CtypeLogModelGroupColumn>(group,
								new GwtCallBack<CtypeLogModelGroupColumn>() {
									@Override
									protected void _succeed() {
										CtypeLogModelGroupColumn group = getResult();
										TreeStore<CtypeLogModelColumn> treeStroe = getGrid()
												.getTreeStore();
										treeStroe.update(group);
										if (group.getColumns() != null
												&& group.getColumns().size() > 0) {
											for (CtypeLogModelColumn column : group
													.getColumns()) {
												if (column instanceof CtypeLogModelLeafColumn) {
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
				final CtypeLogModelColumn group = getGrid().getSelectionModel()
						.getSelectedItem();
				getGrid().mask("删除中...");
				RpcServiceUtils.CtypeLogModelRpcService.deleteColumn(group,
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
				final CtypeLogModelGroupColumn group = getSeletedGroup();
				CtypeLogModelLeafColumn leaf = new CtypeLogModelLeafColumn();
				leaf.setParent(group);
				getLeafEditor().fireEditEvent(
						new EditEvent<CtypeLogModelLeafColumn>(leaf,
								new GwtCallBack<CtypeLogModelLeafColumn>() {
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
				CtypeLogModelLeafColumn leaf = getSeletedLeaf();
				getLeafEditor().fireEditEvent(
						new EditEvent<CtypeLogModelLeafColumn>(leaf,
								new GwtCallBack<CtypeLogModelLeafColumn>() {
									@Override
									protected void _succeed() {
										getGrid().getTreeStore().update(
												getResult());
									}
								}, true));
			}
		});
	}

	public CtypeLogModelRootColumnTreeGrid getGrid() {
		return grid;
	}

	protected CtypeLogModelGroupColumn getSeletedGroup() {
		return (CtypeLogModelGroupColumn) getGrid().getSelectionModel()
				.getSelectedItem();
	}

	protected CtypeLogModelLeafColumn getSeletedLeaf() {
		return (CtypeLogModelLeafColumn) getGrid().getSelectionModel()
				.getSelectedItem();
	}

	private CtypeLogModelGroupColumnEditor groupEditor;

	public CtypeLogModelGroupColumnEditor getGroupEditor() {
		if (groupEditor == null) {
			groupEditor = new CtypeLogModelGroupColumnEditor();
		}
		return groupEditor;
	}

	private CtypeLogModelLeafColumnEditor leafEditor;

	public CtypeLogModelLeafColumnEditor getLeafEditor() {
		if (leafEditor == null) {
			leafEditor = new CtypeLogModelLeafColumnEditor();
		}
		return leafEditor;
	}
}
