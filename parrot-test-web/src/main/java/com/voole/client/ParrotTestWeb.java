package com.voole.client;

import java.util.Date;

import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.cell.core.client.form.DateCell;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.DateField;
import com.voole.parrot.gwt.common.shared.GwtCallBack;
import com.voole.parrot.gwt.common.shared.core.event.EditEvent;
import com.voole.parrot.gwt.common.shared.rpcservice.RpcServiceUtils;
import com.voole.parrot.gwt.ui.shared.authority.tree.AuthorityTree;
import com.voole.parrot.gwt.ui.shared.menu.LeftMenuView;
import com.voole.parrot.gwt.ui.shared.menu.editor.AuthorityField;
import com.voole.parrot.gwt.ui.shared.menu.editor.MenuEditor;
import com.voole.parrot.shared.entity.authority.Authority;
import com.voole.parrot.shared.entity.menu.Menu;
import com.voole.parrot.shared.entity.menu.MenuGroup;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class ParrotTestWeb implements EntryPoint {

	public void onModuleLoad() {
		Scheduler.get().scheduleDeferred(new ScheduledCommand() {
			@Override
			public void execute() {
				// AuthorityPicker picker = new AuthorityPicker();
				// picker.show();

				// testField1();

				// testField2();
//				OrganizationTreeGrid view = new OrganizationTreeGrid();
//				RootPanel.get().add(view);

				AuthorityTree tree = new AuthorityTree();
				RootPanel.get().add(tree);
				// RootPanel.get().add(menuView);

				// testDateField();
				// showTestField();
				// createMenus();
				// showGrid();

				// getMenus();
				// showMenuView();
			}

			private void testField2() {
				TextButton tb = new TextButton("add");
				RootPanel.get().add(tb);
				tb.addSelectHandler(new SelectHandler() {

					@Override
					public void onSelect(SelectEvent event) {
						MenuEditor editor = new MenuEditor();
						editor.fireEditEvent(new EditEvent<Menu>(new Menu(),
								new GwtCallBack<Menu>() {

									@Override
									protected void _succeed() {
										Window.alert(getResult().getId() + "");
									}
								}));
					}
				});
			}

			private void testField1() {
				final AuthorityField field = new AuthorityField();
				RootPanel.get().add(field);

				TextButton tb = new TextButton("test");
				tb.addSelectHandler(new SelectHandler() {

					@Override
					public void onSelect(SelectEvent event) {
						field.flush();
						Authority authority = field.getValue();
						Window.alert(authority == null ? "null" : "not null");
						Window.alert(authority == null ? "null" : authority
								.getName());

					}
				});
				RootPanel.get().add(tb);
			}

		});

	}

	private void testDateField() {
		RootPanel.get().add(new DateField(new DateCell() {
			@Override
			protected void onNavigationKey(
					com.google.gwt.cell.client.Cell.Context context,
					Element parent, Date value, NativeEvent event,
					ValueUpdater<Date> valueUpdater) {
				System.out.println(event.getKeyCode());
				super.onNavigationKey(context, parent, value, event,
						valueUpdater);
			}
		}));
	}

	public static void main(String[] args) {
	}

	/**
	 * 
	 */
	protected void showTestField() {
		TestBeanEditor e = new TestBeanEditor();
		RootPanel.get().add(e);
	}

	protected void showMenuView() {
		LeftMenuView mv = new LeftMenuView();
		RootPanel.get().add(mv);
	}

	/**
	 * 
	 */
	protected void getMenus() {
		RpcServiceUtils.OpenAuthorizeRpcService
				.getUserMenus(new AsyncCallback<MenuGroup>() {

					@Override
					public void onFailure(Throwable caught) {

					}

					@Override
					public void onSuccess(MenuGroup result) {
						System.out.println(result.getName().length());
					}
				});
	}

}
