package com.voole.parrot.gwt.ui.shared.ctypelogmodel.window;

import com.voole.parrot.gwt.common.shared.core.window.FixedWindow;
import com.voole.parrot.gwt.ui.shared.ctypelogmodel.grid.CtypeLogModelRootColumnTreeGrid;
import com.voole.parrot.shared.entity.ctypelogmeta.CtypeLogModelVersion;

public class CtypeLogModelRootColumnTreeGridWindow extends FixedWindow {
	private final CtypeLogModelRootColumnTreeGrid grid;

	public CtypeLogModelRootColumnTreeGridWindow(CtypeLogModelVersion version) {
		grid = new CtypeLogModelRootColumnTreeGrid(version);
		setTitle("编辑columns");
		grid.setHeight(400);
		setWidget(grid);
	}
}
