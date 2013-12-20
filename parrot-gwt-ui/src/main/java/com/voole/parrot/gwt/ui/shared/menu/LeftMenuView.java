/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.parrot.gwt.ui.shared.menu;

import java.util.List;

import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.text.shared.SafeHtmlRenderer;
import com.google.gwt.text.shared.SimpleSafeHtmlRenderer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.cell.core.client.SimpleSafeHtmlCell;
import com.sencha.gxt.core.client.Style.SelectionMode;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.TreeStore;
import com.sencha.gxt.widget.core.client.tree.Tree;
import com.voole.parrot.gwt.common.shared.RpcAsyncCallback;
import com.voole.parrot.gwt.common.shared.property.PropertyUtils;
import com.voole.parrot.gwt.common.shared.rpcservice.RpcServiceUtils;
import com.voole.parrot.gwt.ui.shared.event.MenuClickEvent;
import com.voole.parrot.shared.entity.menu.Menu;
import com.voole.parrot.shared.entity.menu.MenuGroup;
import com.voole.parrot.shared.entity.menu.MenuNode;

/**
 * @author XuehuiHe
 * @date 2013年8月9日
 */
public class LeftMenuView extends Tree<MenuNode, MenuNode> {

	private Widget menuClickRecipient;

	public LeftMenuView() {
		super(new TreeStore<MenuNode>(PropertyUtils.MenuNodeProperty.key()),
				new ValueProvider<MenuNode, MenuNode>() {

					@Override
					public MenuNode getValue(MenuNode object) {
						return object;
					}

					@Override
					public void setValue(MenuNode object, MenuNode value) {
						object = value;
					}

					@Override
					public String getPath() {
						return "name";
					}
				});
		SimpleSafeHtmlCell<MenuNode> cell = new SimpleSafeHtmlCell<MenuNode>(
				new SafeHtmlRenderer<MenuNode>() {

					@Override
					public SafeHtml render(MenuNode object) {
						return SimpleSafeHtmlRenderer.getInstance().render(
								object.getName());
					}

					@Override
					public void render(MenuNode object, SafeHtmlBuilder builder) {
						SimpleSafeHtmlRenderer.getInstance().render(
								object.getName(), builder);

					}
				}, "click") {
			@Override
			public void onBrowserEvent(
					com.google.gwt.cell.client.Cell.Context context,
					Element parent, MenuNode value, NativeEvent event,
					ValueUpdater<MenuNode> valueUpdater) {
				super.onBrowserEvent(context, parent, value, event,
						valueUpdater);
				if ("click".equals(event.getType())) {
					if (value instanceof Menu) {
						if (getMenuClickRecipient() != null) {
							getMenuClickRecipient().fireEvent(
									new MenuClickEvent(((Menu) value)
											.getToken()));
						}
					} else if (value instanceof LogoutMenu) {
						RpcServiceUtils.OpenAuthorizeRpcService
								.logout(new AsyncCallback<Void>() {

									@Override
									public void onSuccess(Void result) {
										Window.Location.reload();
									}

									@Override
									public void onFailure(Throwable caught) {

									}
								});
					}
				}
			}

		};
		setCell(cell);
		setWidth(300);
		getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
	}

	@Override
	protected void onAfterFirstAttach() {
		super.onAfterFirstAttach();
		Scheduler.get().scheduleDeferred(new ScheduledCommand() {
			@Override
			public void execute() {
				getData();
			}
		});
	}

	protected void initData(MenuGroup root) {
		List<MenuNode> result = root.getNodes();
		store.clear();
		for (MenuNode menuNode : result) {
			initData(null, menuNode);
		}
		store.add(new LogoutMenu());
	}

	protected void initData(MenuNode parent, MenuNode menuNode) {
		if (menuNode == null) {
			return;
		}
		if (menuNode instanceof MenuGroup) {
			List<MenuNode> nodes = ((MenuGroup) menuNode).getNodes();
			if (nodes == null || nodes.size() == 0) {
				return;
			}
		}
		if (parent == null) {
			store.add(menuNode);
		} else {
			store.add(parent, menuNode);
		}
		if (menuNode instanceof MenuGroup) {
			List<MenuNode> nodes = ((MenuGroup) menuNode).getNodes();
			if (nodes == null || nodes.size() == 0) {
				return;
			}
			for (MenuNode node : nodes) {
				initData(menuNode, node);
			}
		}
	}

	public void showMenu(String token) {
		MenuNode mn = getStore().findModelWithKey(token);
		if (mn != null) {
			MenuNode parent = getStore().getParent(mn);
			if (parent != null) {
				setExpanded(parent, true);
			}
			scrollIntoView(mn);
			getSelectionModel().select(mn, true);
		} else {
			getSelectionModel().deselectAll();
		}

	}

	private void getData() {
		RpcServiceUtils.OpenAuthorizeRpcService
				.getUserMenus(new RpcAsyncCallback<MenuGroup>() {
					@Override
					public void _onSuccess(MenuGroup t) {
						initData(t);
					}
				});
	}

	public class LogoutMenu extends MenuNode {
		private static final long serialVersionUID = 6258185941078205551L;

		public LogoutMenu() {
			setName("登出");
			setId(-1L);
		}
	}

	public Widget getMenuClickRecipient() {
		return menuClickRecipient;
	}

	public void setMenuClickRecipient(Widget menuClickRecipient) {
		this.menuClickRecipient = menuClickRecipient;
	}

}
