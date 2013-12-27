/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.parrot.gwt.ui.shared.ctypelogmodel.grid;

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
import com.voole.parrot.gwt.ui.shared.ctypelogmodel.editor.CtypeLogModelEditor;
import com.voole.parrot.gwt.ui.shared.ctypelogmodel.editor.CtypeLogModelVersionDuplicateEditor;
import com.voole.parrot.gwt.ui.shared.ctypelogmodel.editor.CtypeLogModelVersionEditor;
import com.voole.parrot.gwt.ui.shared.ctypelogmodel.window.CtypeLogModelRootColumnTreeGridWindow;
import com.voole.parrot.shared.entity.EntityHasAutoId;
import com.voole.parrot.shared.entity.logmeta.LogModel;
import com.voole.parrot.shared.entity.logmeta.LogModelVersion;

/**
 * @author XuehuiHe
 * @date 2013年12月26日
 */
public class CtypeLogModelTreeGridMenu extends Menu {
	private final CtypeLogModelTreeGrid grid;

	private final MenuItem addModel;
	private final MenuItem modifyModel;
	private final MenuItem delModel;
	private final MenuItem addVersion;
	private final MenuItem modifyVersion;
	private final MenuItem delVersion;
	private final MenuItem duplicateVersion;
	private final MenuItem editColumns;

	public CtypeLogModelTreeGridMenu(CtypeLogModelTreeGrid grid2) {
		grid = grid2;
		addModel = new MenuItem("添加model");
		add(addModel);
		add(new SeparatorMenuItem());
		modifyModel = new MenuItem("修改model");
		add(modifyModel);
		delModel = new MenuItem("删除model");
		add(delModel);
		add(new SeparatorMenuItem());
		addVersion = new MenuItem("添加version");
		add(addVersion);
		add(new SeparatorMenuItem());
		modifyVersion = new MenuItem("修改version");
		add(modifyVersion);
		delVersion = new MenuItem("删除version");
		add(delVersion);
		duplicateVersion = new MenuItem("创建version副本");
		add(duplicateVersion);
		add(new SeparatorMenuItem());
		editColumns = new MenuItem("编辑version的字段");
		add(editColumns);
		initAddModel();
		initModifyModel();
		initDelModel();
		initAddVersion();
		initModifyVersion();
		initDelVersion();
		initDuplicateVersion();
		initEditColumns();
		addBeforeShowHandler(new BeforeShowHandler() {
			@Override
			public void onBeforeShow(BeforeShowEvent event) {
				EntityHasAutoId entity = getGrid().getSelectionModel()
						.getSelectedItem();
				if (entity == null) {
					modifyModel.disable();
					delModel.disable();
					addVersion.disable();
					modifyVersion.disable();
					delVersion.disable();
					duplicateVersion.disable();
					editColumns.disable();
				} else if (entity instanceof LogModel) {
					modifyVersion.disable();
					delVersion.disable();
					duplicateVersion.disable();
					editColumns.disable();
				} else {
					addModel.disable();
					modifyModel.disable();
					delModel.disable();
					addVersion.disable();
				}

			}
		});

		addHideHandler(new HideHandler() {
			@Override
			public void onHide(HideEvent event) {
				addModel.enable();
				modifyModel.enable();
				delModel.enable();
				addVersion.enable();
				modifyVersion.enable();
				delVersion.enable();
				duplicateVersion.enable();
				editColumns.enable();
			}
		});
	}

	protected void initAddModel() {
		addModel.addSelectionHandler(new SelectionHandler<Item>() {
			@Override
			public void onSelection(SelectionEvent<Item> event) {
				LogModel model = new LogModel();
				getModelEditor().fireEditEvent(
						new EditEvent<LogModel>(model,
								new GwtCallBack<LogModel>() {
									@Override
									protected void _succeed() {
										getGrid().getTreeStore().add(
												getResult());
									}
								}));
			}
		});
	}

	protected void initModifyModel() {
		modifyModel.addSelectionHandler(new SelectionHandler<Item>() {
			@Override
			public void onSelection(SelectionEvent<Item> event) {
				LogModel model = getSeletedModel();
				getModelEditor().fireEditEvent(
						new EditEvent<LogModel>(model,
								new GwtCallBack<LogModel>() {
									@Override
									protected void _succeed() {
										getGrid().getTreeStore().update(
												getResult());
									}
								}, true));
			}
		});
	}

	protected void initDelModel() {
		delModel.addSelectionHandler(new SelectionHandler<Item>() {
			@Override
			public void onSelection(SelectionEvent<Item> event) {
				final LogModel model = getSeletedModel();
				getGrid().mask("删除中...");
				RpcServiceUtils.CtypeLogModelRpcService.deleteModel(model,
						new RpcAsyncCallback<Void>() {
							@Override
							protected void _onSuccess(Void result) {
								getGrid().getTreeStore().remove(model);
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

	protected void initAddVersion() {
		addVersion.addSelectionHandler(new SelectionHandler<Item>() {
			@Override
			public void onSelection(SelectionEvent<Item> event) {
				final LogModel model = getSeletedModel();
				LogModelVersion version = new LogModelVersion();
				version.setModel(model);
				getVersionEditor().fireEditEvent(
						new EditEvent<LogModelVersion>(version,
								new GwtCallBack<LogModelVersion>() {
									@Override
									protected void _succeed() {
										getGrid().getTreeStore().add(model,
												getResult());
									}
								}));
			}
		});
	}

	protected void initModifyVersion() {
		modifyVersion.addSelectionHandler(new SelectionHandler<Item>() {
			@Override
			public void onSelection(SelectionEvent<Item> event) {
				LogModelVersion version = getSeletedVersion();
				getVersionEditor().fireEditEvent(
						new EditEvent<LogModelVersion>(version,
								new GwtCallBack<LogModelVersion>() {
									@Override
									protected void _succeed() {
										getGrid().getTreeStore().update(
												getResult());
									}
								}, true));
			}
		});
	}

	protected void initDelVersion() {
		delVersion.addSelectionHandler(new SelectionHandler<Item>() {
			@Override
			public void onSelection(SelectionEvent<Item> event) {
				final LogModelVersion verion = getSeletedVersion();
				getGrid().mask("删除中...");
				RpcServiceUtils.CtypeLogModelRpcService.deleteVersion(verion,
						new RpcAsyncCallback<Void>() {
							@Override
							protected void _onSuccess(Void result) {
								getGrid().getTreeStore().remove(verion);
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

	protected void initDuplicateVersion() {
		duplicateVersion.addSelectionHandler(new SelectionHandler<Item>() {
			@Override
			public void onSelection(SelectionEvent<Item> event) {
				final LogModelVersion version = getSeletedVersion();
				CtypeLogModelVersionDuplicateEditor e = new CtypeLogModelVersionDuplicateEditor(
						version);
				LogModelVersion d = new LogModelVersion();
				e.fireEditEvent(new EditEvent<LogModelVersion>(d,
						new GwtCallBack<LogModelVersion>() {
							@Override
							protected void _succeed() {
								getGrid().getTreeStore().add(
										version.getModel(), getResult());
							}
						}));
			}
		});
	}

	protected void initEditColumns() {
		editColumns.addSelectionHandler(new SelectionHandler<Item>() {
			@Override
			public void onSelection(SelectionEvent<Item> event) {
				CtypeLogModelRootColumnTreeGridWindow w = new CtypeLogModelRootColumnTreeGridWindow(
						getSeletedVersion());
				w.show();
			}
		});
	}

	public CtypeLogModelTreeGrid getGrid() {
		return grid;
	}

	protected LogModel getSeletedModel() {
		return (LogModel) getGrid().getSelectionModel().getSelectedItem();
	}

	protected LogModelVersion getSeletedVersion() {
		return (LogModelVersion) getGrid().getSelectionModel()
				.getSelectedItem();
	}

	private CtypeLogModelEditor modelEditor;

	public CtypeLogModelEditor getModelEditor() {
		if (modelEditor == null) {
			modelEditor = new CtypeLogModelEditor();
		}
		return modelEditor;
	}

	private CtypeLogModelVersionEditor versionEditor;

	public CtypeLogModelVersionEditor getVersionEditor() {
		if (versionEditor == null) {
			versionEditor = new CtypeLogModelVersionEditor();
		}
		return versionEditor;
	}
}
