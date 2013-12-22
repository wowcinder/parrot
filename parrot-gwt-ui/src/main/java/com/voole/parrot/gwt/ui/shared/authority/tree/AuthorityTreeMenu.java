package com.voole.parrot.gwt.ui.shared.authority.tree;

import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.sencha.gxt.widget.core.client.event.BeforeShowEvent;
import com.sencha.gxt.widget.core.client.event.BeforeShowEvent.BeforeShowHandler;
import com.sencha.gxt.widget.core.client.event.HideEvent;
import com.sencha.gxt.widget.core.client.event.HideEvent.HideHandler;
import com.sencha.gxt.widget.core.client.menu.Item;
import com.sencha.gxt.widget.core.client.menu.Menu;
import com.sencha.gxt.widget.core.client.menu.MenuItem;
import com.voole.parrot.gwt.ui.shared.authority.window.AuthorityWindow;
import com.voole.parrot.shared.entity.EntityHasAutoId;
import com.voole.parrot.shared.entity.authority.Authority;
import com.voole.parrot.shared.entity.authority.AuthorityEntrance;

public class AuthorityTreeMenu extends Menu {
	private final MenuItem modifyMenu;
	private final AuthorityTree tree;

	public AuthorityTreeMenu(AuthorityTree tree2) {
		tree = tree2;
		modifyMenu = new MenuItem("修改依赖权限");
		add(modifyMenu);
		this.addBeforeShowHandler(new BeforeShowHandler() {
			@Override
			public void onBeforeShow(BeforeShowEvent event) {
				EntityHasAutoId selectItem = tree.getSelectionModel()
						.getSelectedItem();
				if (selectItem == null
						|| selectItem instanceof AuthorityEntrance) {
					AuthorityTreeMenu.this.disable();
				}
			}
		});

		this.addHideHandler(new HideHandler() {
			@Override
			public void onHide(HideEvent event) {
				AuthorityTreeMenu.this.enable();
			}
		});

		modifyMenu.addSelectionHandler(new SelectionHandler<Item>() {
			@Override
			public void onSelection(SelectionEvent<Item> event) {
				final Authority authority = (Authority) tree
						.getSelectionModel().getSelectedItem();
				AuthorityWindow window = new AuthorityWindow(authority);
				window.show();
			}
		});
	}
}
