package com.voole.parrot.gwt.ui.shared.logmodel.window;

import com.voole.parrot.gwt.common.shared.core.window.FixedWindow;
import com.voole.parrot.gwt.ui.shared.logmodel.grid.LogModelRootColumnTreeGrid;
import com.voole.parrot.shared.entity.logmeta.LogModelVersion;

public class LogModelRootColumnTreeGridWindow extends FixedWindow {
	private final LogModelRootColumnTreeGrid grid;

	public LogModelRootColumnTreeGridWindow(LogModelVersion version) {
		grid = new LogModelRootColumnTreeGrid(version);
		setTitle("编辑columns");
		grid.setHeight(400);
		setWidget(grid);
	}
}
