package com.voole.parrot.gwt.ui.shared.authority.window;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutPack;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.tree.Tree.CheckCascade;
import com.sencha.gxt.widget.core.client.tree.Tree.CheckNodes;
import com.sencha.gxt.widget.core.client.tree.Tree.CheckState;
import com.voole.parrot.gwt.common.shared.core.window.FixedWindow;
import com.voole.parrot.gwt.ui.shared.authority.tree.AuthorityTree;
import com.voole.parrot.shared.entity.EntityHasAutoId;
import com.voole.parrot.shared.entity.authority.Authority;
import com.voole.parrot.shared.entity.authority.AuthorityEntrance;

public abstract class AuthorityCheckTreeWindow<T> extends FixedWindow {
	private final T t;
	private final AuthorityTree tree;
	private final TextButton tb;

	public AuthorityCheckTreeWindow(T t2) {
		t = t2;

		tree = new AuthorityTree() {
			@Override
			public void initData(List<AuthorityEntrance> entrances) {
				super.initData(entrances);
				Scheduler.get().scheduleDeferred(new ScheduledCommand() {
					@Override
					public void execute() {
						getSelectAuthorities();
					}
				});

			}
		};
		tree.setCheckable(true);
		tree.setCheckStyle(CheckCascade.TRI);
		tree.setCheckNodes(CheckNodes.BOTH);
		tree.setHeight(400);
		tree.setWidth(300);

		setWidget(tree);

		tb = new TextButton("提交");
		addButton(tb);
		setButtonAlign(BoxLayoutPack.CENTER);
		tb.addSelectHandler(getTbSelectHandler());
	}

	protected abstract SelectHandler getTbSelectHandler();

	protected abstract void getSelectAuthorities();

	protected Set<Authority> getCheckNodes() {
		List<EntityHasAutoId> checkNodes = tree.getCheckedSelection();
		Set<Authority> authorities = new HashSet<Authority>();
		for (EntityHasAutoId entityHasAutoId : checkNodes) {
			if (entityHasAutoId instanceof Authority) {
				authorities.add((Authority) entityHasAutoId);
			}
		}
		return authorities;
	}

	public T getT() {
		return t;
	}

	public TextButton getTb() {
		return tb;
	}

	public AuthorityTree getTree() {
		return tree;
	}

	protected void checkNodes(Collection<Authority> authorities) {
		for (Authority authority : authorities) {
			tree.setChecked(authority, CheckState.CHECKED);
		}
	}
}
