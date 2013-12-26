package com.voole.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.voole.parrot.gwt.ui.shared.authority.AuthorityView;
import com.voole.parrot.gwt.ui.shared.menu.tree.MenuTree;
import com.voole.parrot.gwt.ui.shared.role.RoleView;
import com.voole.parrot.gwt.ui.shared.user.UserView;
import com.voole.parrot.gwt.ui.shared.user.window.UserRolesWindow;
import com.voole.parrot.shared.entity.user.User;

public class ParrotTestWeb implements EntryPoint {

	@Override
	public void onModuleLoad() {
		// testAuthority();

		// HbaseTableTreeGrid grid = new HbaseTableTreeGrid();
		// grid.setHeight(400);
		// RootPanel.get().add(grid);
		// CtypeLogModelVersion version = new CtypeLogModelVersion();
		// version.setId(1L);
		// CtypeLogModelRootColumnTreeGrid grid = new
		// CtypeLogModelRootColumnTreeGrid(
		// version);
		// grid.setHeight(400);
		// RootPanel.get().add(grid);
		
		MenuTree grid = new MenuTree();
		grid.setHeight(400);
		RootPanel.get().add(grid);
		// testUserRolesWindow();
	}

	public void testAuthority() {
		AuthorityView view = new AuthorityView();
		view.setHeight(400);

		RoleView view2 = new RoleView();
		view2.setHeight(400);

		UserView view3 = new UserView();
		view3.setHeight(400);
		VerticalLayoutContainer c = new VerticalLayoutContainer();
		c.add(view);
		c.add(view2);
		c.add(view3);

		RootPanel.get().add(c);
	}

	protected void testUserRolesWindow() {
		TextButton tb = new TextButton("test");
		RootPanel.get().add(tb);

		tb.addSelectHandler(new SelectHandler() {

			@Override
			public void onSelect(SelectEvent event) {
				User user = new User();
				user.setId(2L);

				UserRolesWindow window = new UserRolesWindow(user);
				window.show();
			}
		});
	}
}
