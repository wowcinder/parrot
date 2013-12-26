package com.voole.parrot.gwt.ui.shared.ctypelogmodel.field;

import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutPack;
import com.sencha.gxt.widget.core.client.event.CellDoubleClickEvent;
import com.sencha.gxt.widget.core.client.event.CellDoubleClickEvent.CellDoubleClickHandler;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.voole.parrot.gwt.common.shared.core.field.SimpleFieldCell;
import com.voole.parrot.gwt.common.shared.core.window.FixedWindow;
import com.voole.parrot.gwt.ui.shared.hbasemeta.grid.HbaseTableTreeGrid;
import com.voole.parrot.shared.entity.EntityHasAutoId;
import com.voole.parrot.shared.entity.hbasemeta.HbaseTableVersion;

public class HbaseTableVersionFieldCell extends
		SimpleFieldCell<HbaseTableVersion> {

	@Override
	protected HbaseTableVersionFieldWindow createPicker() {
		return new HbaseTableVersionFieldWindow(this);
	}

	public static class HbaseTableVersionFieldWindow extends FixedWindow {
		private final HbaseTableVersionFieldCell fieldCell;
		private final TextButton clearBt;
		private final HbaseTableTreeGrid grid;

		public HbaseTableVersionFieldWindow(
				HbaseTableVersionFieldCell fieldCell2) {
			this.fieldCell = fieldCell2;
			this.clearBt = new TextButton("clear");
			addButton(clearBt);
			setButtonAlign(BoxLayoutPack.END);

			grid = new HbaseTableTreeGrid();
			grid.setHeight(400);
			setWidget(grid);

			grid.addCellDoubleClickHandler(new CellDoubleClickHandler() {
				@Override
				public void onCellClick(CellDoubleClickEvent event) {
					EntityHasAutoId entity = grid.getStore().get(
							event.getRowIndex());
					if (entity instanceof HbaseTableVersion) {
						HbaseTableVersion version = (HbaseTableVersion) entity;
						fieldCell.doTriggerValue(version);
						HbaseTableVersionFieldWindow.this.hide();
					}
				}
			});

			clearBt.addSelectHandler(new SelectHandler() {

				@Override
				public void onSelect(SelectEvent event) {
					fieldCell.doTriggerValue(null);
					HbaseTableVersionFieldWindow.this.hide();
				}
			});
		}
	}
}