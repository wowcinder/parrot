package com.voole.parrot.gwt.ui.shared.ctypelogmodel.window;

import com.voole.parrot.gwt.common.shared.core.window.FixedWindow;
import com.voole.parrot.gwt.ui.shared.ctypelogmodel.grid.CtypeLogModelRootColumnTreeGrid;
import com.voole.parrot.shared.entity.logmeta.LogModelVersion;

public class CtypeLogModelRootColumnTreeGridWindow extends FixedWindow {
	private final CtypeLogModelRootColumnTreeGrid grid;

	public CtypeLogModelRootColumnTreeGridWindow(LogModelVersion version) {
		grid = new CtypeLogModelRootColumnTreeGrid(version);
		setTitle("编辑columns");
		grid.setHeight(400);
		setWidget(grid);
	}
}
