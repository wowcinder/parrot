/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.parrot.gwt.ui.shared.hbasemeta.grid;

import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
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
import com.voole.parrot.gwt.ui.shared.hbasemeta.editor.HbaseTableEditor;
import com.voole.parrot.gwt.ui.shared.hbasemeta.editor.HbaseTableVersionEditor;
import com.voole.parrot.shared.entity.EntityHasAutoId;
import com.voole.parrot.shared.entity.hbasemeta.HbaseTable;
import com.voole.parrot.shared.entity.hbasemeta.HbaseTableVersion;

/**
 * @author XuehuiHe
 * @date 2013年12月25日
 */
public class HbaseTableTreeGridMenu extends Menu {
	private final HbaseTableTreeGrid tree;
	private MenuItem addTable;
	private MenuItem modifyTable;
	private MenuItem deleteTable;
	private MenuItem addVersion;
	private MenuItem modifyVersion;
	private MenuItem deleteVersion;
	private MenuItem editColumns;

	public HbaseTableTreeGridMenu(HbaseTableTreeGrid tree2) {
		tree = tree2;
		createAddTable();
		createModifyTable();
		createDeleteTable();
		createAddVersion();
		add(new SeparatorMenuItem());
		createModifyVersion();
		createDeleteVersion();
		createEditColumns();
		addMenuBeforeShowHandler();
		addMenuHideHandler();
	}

	protected void addMenuHideHandler() {
		addHideHandler(new HideHandler() {
			@Override
			public void onHide(HideEvent event) {
				addTable.enable();
				modifyTable.enable();
				deleteTable.enable();
				addVersion.enable();
				modifyVersion.enable();
				deleteVersion.enable();
				editColumns.enable();
			}
		});
	}

	protected void addMenuBeforeShowHandler() {
		addBeforeShowHandler(new BeforeShowHandler() {
			@Override
			public void onBeforeShow(BeforeShowEvent event) {
				EntityHasAutoId entity = tree.getSelectionModel()
						.getSelectedItem();
				if (entity == null) {
					modifyTable.disable();
					deleteTable.disable();
					addVersion.disable();
					modifyVersion.disable();
					deleteVersion.disable();
					editColumns.disable();
				} else if (entity instanceof HbaseTable) {
					modifyVersion.disable();
					deleteVersion.disable();
					editColumns.disable();
				} else {
					addTable.disable();
					modifyTable.disable();
					deleteTable.disable();
					addVersion.disable();
				}
			}
		});
	}

	protected void createEditColumns() {
		editColumns = new MenuItem("编辑columns");
		add(editColumns);
	}

	protected void createModifyVersion() {
		modifyVersion = new MenuItem("修改version");
		add(modifyVersion);
		modifyVersion.addSelectionHandler(new SelectionHandler<Item>() {

			@Override
			public void onSelection(SelectionEvent<Item> event) {
				final HbaseTableVersion version = getSelectedVersion();
				getVersionEditor().fireEditEvent(
						new EditEvent<HbaseTableVersion>(version,
								new GwtCallBack<HbaseTableVersion>() {

									@Override
									protected void _succeed() {
										tree.getStore().update(getResult());
									}
								}, true));
			}
		});
	}

	protected void createDeleteVersion() {
		deleteVersion = new MenuItem("删除version");
		add(deleteVersion);

		deleteVersion.addSelectionHandler(new SelectionHandler<Item>() {
			@Override
			public void onSelection(SelectionEvent<Item> event) {
				tree.mask("删除中...");
				final HbaseTableVersion version = getSelectedVersion();
				RpcServiceUtils.HbaseTableMetaRpcService
						.deleteHbaseTableVersion(version,
								new RpcAsyncCallback<Void>() {

									@Override
									protected void _onSuccess(Void result) {
										tree.getTreeStore().remove(version);
									}

									@Override
									public void post() {
										super.post();
										tree.unmask();
									}
								});
			}
		});
	}

	protected void createAddVersion() {
		addVersion = new MenuItem("添加version");
		add(addVersion);

		addVersion.addSelectionHandler(new SelectionHandler<Item>() {
			@Override
			public void onSelection(SelectionEvent<Item> event) {
				HbaseTableVersion v = new HbaseTableVersion();
				v.setTable(getSelectedTable());
				getVersionEditor().fireEditEvent(
						new EditEvent<HbaseTableVersion>(v,
								new GwtCallBack<HbaseTableVersion>() {
									@Override
									protected void _succeed() {
										HbaseTableVersion result = getResult();
										HbaseTable table = (HbaseTable) tree
												.getTreeStore().findModel(
														result.getTable());
										tree.getTreeStore().add(table, result);
									}
								}));
			}
		});
	}

	protected void createDeleteTable() {
		deleteTable = new MenuItem("删除table");
		add(deleteTable);

		deleteTable.addSelectionHandler(new SelectionHandler<Item>() {
			@Override
			public void onSelection(SelectionEvent<Item> event) {
				final HbaseTable t = getSelectedTable();
				tree.mask("删除中...");
				RpcServiceUtils.HbaseTableMetaRpcService.deleteTable(t,
						new RpcAsyncCallback<Void>() {

							@Override
							protected void _onSuccess(Void result) {
								tree.getTreeStore().remove(t);
							}

							@Override
							public void post() {
								super.post();
								tree.unmask();
							}
						});
			}
		});
	}

	protected void createModifyTable() {
		modifyTable = new MenuItem("修改table");
		add(modifyTable);

		modifyTable.addSelectionHandler(new SelectionHandler<Item>() {
			@Override
			public void onSelection(SelectionEvent<Item> event) {
				final HbaseTable t = getSelectedTable();
				getTableEditor().fireEditEvent(
						new EditEvent<HbaseTable>(t,
								new GwtCallBack<HbaseTable>() {
									@Override
									protected void _succeed() {
										tree.getTreeStore().update(getResult());
									}
								}, true));
			}
		});
	}

	protected void createAddTable() {
		addTable = new MenuItem("添加table");
		add(addTable);

		addTable.addSelectionHandler(new SelectionHandler<Item>() {

			@Override
			public void onSelection(SelectionEvent<Item> event) {
				HbaseTable t = new HbaseTable();
				getTableEditor().fireEditEvent(
						new EditEvent<HbaseTable>(t,
								new GwtCallBack<HbaseTable>() {
									@Override
									protected void _succeed() {
										tree.getTreeStore().add(getResult());
									}
								}));

			}
		});

	}

	protected HbaseTable getSelectedTable() {
		return (HbaseTable) tree.getSelectionModel().getSelectedItem();
	}

	protected HbaseTableVersion getSelectedVersion() {
		return (HbaseTableVersion) tree.getSelectionModel().getSelectedItem();
	}

	private HbaseTableEditor tableEditor;

	public HbaseTableEditor getTableEditor() {
		if (tableEditor == null) {
			tableEditor = new HbaseTableEditor();
		}
		return tableEditor;
	}

	private HbaseTableVersionEditor versionEditor;

	public HbaseTableVersionEditor getVersionEditor() {
		if (versionEditor == null) {
			versionEditor = new HbaseTableVersionEditor();
		}
		return versionEditor;
	}
}
