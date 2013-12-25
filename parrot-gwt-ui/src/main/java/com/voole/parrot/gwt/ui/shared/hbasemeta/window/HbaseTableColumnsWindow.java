package com.voole.parrot.gwt.ui.shared.hbasemeta.window;

import java.util.List;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.event.CellDoubleClickEvent;
import com.sencha.gxt.widget.core.client.event.CellDoubleClickEvent.CellDoubleClickHandler;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;
import com.voole.parrot.gwt.common.shared.GwtCallBack;
import com.voole.parrot.gwt.common.shared.RpcAsyncCallback;
import com.voole.parrot.gwt.common.shared.VerticalLayoutDataUtil;
import com.voole.parrot.gwt.common.shared.core.event.EditEvent;
import com.voole.parrot.gwt.common.shared.core.grid.GridBuilder;
import com.voole.parrot.gwt.common.shared.core.window.FixedWindow;
import com.voole.parrot.gwt.common.shared.gridcolumn.HbaseTableColumnColumnConfig;
import com.voole.parrot.gwt.common.shared.property.PropertyUtils;
import com.voole.parrot.gwt.common.shared.rpcservice.RpcServiceUtils;
import com.voole.parrot.gwt.ui.shared.hbasemeta.editor.HbaseTableColumnEditor;
import com.voole.parrot.shared.entity.hbasemeta.HbaseTableColumn;
import com.voole.parrot.shared.entity.hbasemeta.HbaseTableVersion;

public class HbaseTableColumnsWindow extends FixedWindow {
	private final HbaseTableVersion version;
	private final Grid<HbaseTableColumn> grid;
	private final VerticalLayoutContainer layoutContainer;
	private final ToolBar headbar;
	private final TextButton addBt;
	private final TextButton delBt;

	private HbaseTableColumnEditor columnEditor;

	public HbaseTableColumnsWindow(HbaseTableVersion version2) {
		this.version = version2;
		this.layoutContainer = new VerticalLayoutContainer();
		GridBuilder<HbaseTableColumn> gb = new GridBuilder<HbaseTableColumn>(
				PropertyUtils.HbaseTableColumnProperty.key());
		gb.addColumn(HbaseTableColumnColumnConfig.name());
		gb.addColumn(HbaseTableColumnColumnConfig.shortname());
		gb.addColumn(HbaseTableColumnColumnConfig.desc());
		gb.addColumn(HbaseTableColumnColumnConfig.type());

		this.grid = gb.create();

		headbar = new ToolBar();
		addBt = new TextButton("添加");
		delBt = new TextButton("删除");
		headbar.add(addBt);
		headbar.add(delBt);
		layoutContainer.add(headbar, VerticalLayoutDataUtil.smallVd);
		layoutContainer.add(grid, VerticalLayoutDataUtil.mainVd);
		layoutContainer.setHeight(400);

		setWidget(layoutContainer);

		addBt.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				HbaseTableColumn column = new HbaseTableColumn();
				column.setVersion(getVersion());

				getColumnEditor().fireEditEvent(
						new EditEvent<HbaseTableColumn>(column,
								new GwtCallBack<HbaseTableColumn>() {
									@Override
									protected void _succeed() {
										getGrid().getStore().add(getResult());
									}
								}));
			}
		});

		delBt.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				delBt.disable();
				getGrid().mask("删除中...");
				final List<HbaseTableColumn> list = getGrid()
						.getSelectionModel().getSelectedItems();
				GwtCallBack<Void> callback = new GwtCallBack<Void>() {
					@Override
					protected void _succeed() {
						for (HbaseTableColumn hbaseTableColumn : list) {
							getGrid().getStore().remove(hbaseTableColumn);
						}
					}

					@Override
					protected void post() {
						super.post();
						delBt.enable();
						getGrid().unmask();
					}

				};
				if (list.size() > 0) {
					RpcServiceUtils.HbaseTableMetaRpcService
							.deleteHbaseTableColumns(list,
									RpcAsyncCallback.dealWith(callback));
				} else {
					callback.cancel();
				}
			}
		});

		grid.addCellDoubleClickHandler(new CellDoubleClickHandler() {
			@Override
			public void onCellClick(CellDoubleClickEvent event) {
				HbaseTableColumn column = getGrid().getStore().get(
						event.getRowIndex());
				getColumnEditor().fireEditEvent(
						new EditEvent<HbaseTableColumn>(column,
								new GwtCallBack<HbaseTableColumn>() {
									@Override
									protected void _succeed() {
										getGrid().getStore()
												.update(getResult());
									}
								}, true));
			}
		});
	}

	@Override
	protected void onAfterFirstAttach() {
		super.onAfterFirstAttach();
		Scheduler.get().scheduleDeferred(new ScheduledCommand() {
			@Override
			public void execute() {
				initGrid();
			}
		});
	}

	protected void initGrid() {
		getGrid().mask("加载中...");
		RpcServiceUtils.HbaseTableMetaRpcService.listHbaseTableColumns(
				getVersion(), new RpcAsyncCallback<List<HbaseTableColumn>>() {

					@Override
					protected void _onSuccess(List<HbaseTableColumn> result) {
						getGrid().getStore().clear();
						getGrid().getStore().addAll(result);
					}

					@Override
					public void post() {
						super.post();
						getGrid().unmask();
					}
				});
	}

	public Grid<HbaseTableColumn> getGrid() {
		return grid;
	}

	public VerticalLayoutContainer getLayoutContainer() {
		return layoutContainer;
	}

	public HbaseTableVersion getVersion() {
		return version;
	}

	public HbaseTableColumnEditor getColumnEditor() {
		if (columnEditor == null) {
			columnEditor = new HbaseTableColumnEditor();
		}
		return columnEditor;
	}
}
