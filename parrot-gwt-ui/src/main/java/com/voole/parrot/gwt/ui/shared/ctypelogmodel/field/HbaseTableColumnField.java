package com.voole.parrot.gwt.ui.shared.ctypelogmodel.field;

import java.util.List;

import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.dom.client.NativeEvent;
import com.sencha.gxt.core.client.dom.XElement;
import com.sencha.gxt.widget.core.client.Window;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutPack;
import com.sencha.gxt.widget.core.client.event.CellDoubleClickEvent;
import com.sencha.gxt.widget.core.client.event.CellDoubleClickEvent.CellDoubleClickHandler;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.event.ShowEvent;
import com.sencha.gxt.widget.core.client.event.ShowEvent.ShowHandler;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.info.Info;
import com.voole.parrot.gwt.common.shared.RpcAsyncCallback;
import com.voole.parrot.gwt.common.shared.core.field.SimpleFieldCell;
import com.voole.parrot.gwt.common.shared.core.field.SimplePropertyEditor;
import com.voole.parrot.gwt.common.shared.core.field.SimpleTriggerField;
import com.voole.parrot.gwt.common.shared.core.grid.GridBuilder;
import com.voole.parrot.gwt.common.shared.core.window.FixedWindow;
import com.voole.parrot.gwt.common.shared.gridcolumn.HbaseTableColumnColumnConfig;
import com.voole.parrot.gwt.common.shared.property.PropertyUtils;
import com.voole.parrot.gwt.common.shared.rpcservice.RpcServiceUtils;
import com.voole.parrot.gwt.ui.shared.ctypelogmodel.editor.CtypeLogModelLeafColumnEditor;
import com.voole.parrot.shared.entity.hbasemeta.HbaseTableColumn;
import com.voole.parrot.shared.entity.hbasemeta.HbaseTableVersion;

public class HbaseTableColumnField extends SimpleTriggerField<HbaseTableColumn> {

	public HbaseTableColumnField(CtypeLogModelLeafColumnEditor columnEditor) {
		super(new HbaseTableColumnFieldCell(columnEditor),
				new SimplePropertyEditor<HbaseTableColumn>() {

					@Override
					public String render(HbaseTableColumn object) {
						if (object != null) {
							return object.getName();
						}
						return null;
					}

				});
	}

	public static class HbaseTableColumnFieldCell extends
			SimpleFieldCell<HbaseTableColumn> {
		private final CtypeLogModelLeafColumnEditor columnEditor;

		public HbaseTableColumnFieldCell(
				CtypeLogModelLeafColumnEditor columnEditor) {
			this.columnEditor = columnEditor;
		}

		protected void onTriggerClick(Context context, XElement parent,
				NativeEvent event, HbaseTableColumn value,
				ValueUpdater<HbaseTableColumn> updater) {
			HbaseTableVersion version = columnEditor.getCurrEditEvent()
					.getTarget().getParent().getHbaseTableVersion();
			if (version == null) {
				Info.display("提示:", "先为GROUP选择table_version");
			} else {
				super.onTriggerClick(context, parent, event, value, updater);
			}
		}

		@Override
		protected Window createPicker() {
			return new HbaseTableColumnWindow(columnEditor, this);
		}
	}

	public static class HbaseTableColumnWindow extends FixedWindow {
		private final CtypeLogModelLeafColumnEditor columnEditor;
		private final HbaseTableColumnFieldCell fieldCell;
		private final TextButton clearBt;
		private final Grid<HbaseTableColumn> grid;

		private Long currVerionId = null;

		public HbaseTableColumnWindow(
				CtypeLogModelLeafColumnEditor columnEditor,
				HbaseTableColumnFieldCell fieldCell2) {
			this.fieldCell = fieldCell2;
			this.columnEditor = columnEditor;
			this.clearBt = new TextButton("clear");
			addButton(clearBt);
			setButtonAlign(BoxLayoutPack.END);

			GridBuilder<HbaseTableColumn> gb = new GridBuilder<HbaseTableColumn>(
					PropertyUtils.HbaseTableColumnProperty.key());
			gb.disableMultiSelect();
			gb.addColumn(HbaseTableColumnColumnConfig.name());
			gb.addColumn(HbaseTableColumnColumnConfig.type());
			gb.addColumn(HbaseTableColumnColumnConfig.desc());

			grid = gb.create();
			grid.setHeight(400);
			setWidget(grid);

			this.removeFromParentOnHide = false;

			grid.addCellDoubleClickHandler(new CellDoubleClickHandler() {
				@Override
				public void onCellClick(CellDoubleClickEvent event) {
					HbaseTableColumn entity = grid.getStore().get(
							event.getRowIndex());
					fieldCell.doTriggerValue(entity);
					HbaseTableColumnWindow.this.hide();

				}
			});

			clearBt.addSelectHandler(new SelectHandler() {
				@Override
				public void onSelect(SelectEvent event) {
					fieldCell.doTriggerValue(null);
					HbaseTableColumnWindow.this.hide();
				}
			});

			addShowHandler(new ShowHandler() {
				@Override
				public void onShow(ShowEvent event) {
					Scheduler.get().scheduleDeferred(new ScheduledCommand() {
						@Override
						public void execute() {
							initGridData();
						}
					});
				}
			});
		}

		protected void initGridData() {
			HbaseTableVersion version = columnEditor.getCurrEditEvent()
					.getTarget().getParent().getHbaseTableVersion();
			if (version.getId() == currVerionId) {
				return;
			} else {
				currVerionId = version.getId();
			}
			grid.getStore().clear();
			grid.mask("加载中...");
			RpcServiceUtils.HbaseTableMetaRpcService.listHbaseTableColumns(
					version, new RpcAsyncCallback<List<HbaseTableColumn>>() {

						@Override
						protected void _onSuccess(List<HbaseTableColumn> result) {
							grid.getStore().addAll(result);
						}

						@Override
						public void post() {
							super.post();
							grid.unmask();
						}
					});
		}
	}

}
