package com.voole.parrot.gwt.ui.shared.authority;

import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.voole.parrot.gwt.common.shared.VerticalLayoutDataUtil;
import com.voole.parrot.gwt.ui.shared.authority.tree.AuthorityTree;
import com.voole.parrot.gwt.ui.shared.authority.tree.AuthorityTreeMenu;

public class AuthorityView extends VerticalLayoutContainer {
	private final AuthorityTree tree;

	public AuthorityView() {
		tree = new AuthorityTree();
		tree.setContextMenu(new AuthorityTreeMenu(tree));
		add(tree, VerticalLayoutDataUtil.mainVd);
	}

}
