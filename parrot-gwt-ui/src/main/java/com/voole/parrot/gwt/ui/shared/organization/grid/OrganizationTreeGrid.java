package com.voole.parrot.gwt.ui.shared.organization.grid;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.sencha.gxt.data.shared.TreeStore;
import com.sencha.gxt.widget.core.client.event.BeforeShowEvent;
import com.sencha.gxt.widget.core.client.event.BeforeShowEvent.BeforeShowHandler;
import com.sencha.gxt.widget.core.client.event.HideEvent;
import com.sencha.gxt.widget.core.client.event.HideEvent.HideHandler;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.menu.Item;
import com.sencha.gxt.widget.core.client.menu.Menu;
import com.sencha.gxt.widget.core.client.menu.MenuItem;
import com.sencha.gxt.widget.core.client.treegrid.TreeGrid;
import com.voole.parrot.gwt.common.shared.GwtCallBack;
import com.voole.parrot.gwt.common.shared.RpcAsyncCallback;
import com.voole.parrot.gwt.common.shared.core.event.EditEvent;
import com.voole.parrot.gwt.common.shared.gridcolumn.OrganizationColumnConfig;
import com.voole.parrot.gwt.common.shared.property.PropertyUtils;
import com.voole.parrot.gwt.common.shared.rpcservice.RpcServiceUtils;
import com.voole.parrot.gwt.ui.shared.organization.editor.SubOrganizationEditor;
import com.voole.parrot.gwt.ui.shared.organization.editor.TopOrganizationEditor;
import com.voole.parrot.shared.entity.organization.Organization;
import com.voole.parrot.shared.entity.organization.SubOrganization;
import com.voole.parrot.shared.entity.organization.TopOrganization;

public class OrganizationTreeGrid extends TreeGrid<Organization> {

	public static List<ColumnConfig<Organization, ?>> columns = new ArrayList<ColumnConfig<Organization, ?>>();
	public static ColumnConfig<Organization, String> nameColumn = OrganizationColumnConfig
			.name();
	static {
		columns.add(nameColumn);
		columns.add(OrganizationColumnConfig.id());
	}

	public OrganizationTreeGrid() {
		super(new TreeStore<Organization>(
				PropertyUtils.OrganizationProperty.key()),
				new ColumnModel<Organization>(columns), nameColumn);

		getView().setAutoExpandColumn(nameColumn);
		setContextMenu(new OrganizationTreeGridMenu());

	}

	public class OrganizationTreeGridMenu extends Menu {
		private final MenuItem topAdd;
		private final MenuItem subAdd;

		public OrganizationTreeGridMenu() {
			topAdd = new MenuItem("添加Top组织");
			subAdd = new MenuItem("添加Sub组织");
			this.add(topAdd);
			this.add(subAdd);
			topAdd.addSelectionHandler(new SelectionHandler<Item>() {
				@Override
				public void onSelection(SelectionEvent<Item> event) {
					TopOrganizationEditor editor = new TopOrganizationEditor();
					editor.fireEditEvent(new EditEvent<TopOrganization>(
							new TopOrganization(),
							new GwtCallBack<TopOrganization>() {
								@Override
								protected void _succeed() {
									initData(null, getResult());
								}
							}, false));
				}
			});

			subAdd.addSelectionHandler(new SelectionHandler<Item>() {
				@Override
				public void onSelection(SelectionEvent<Item> event) {
					final Organization selectItem = OrganizationTreeGrid.this
							.getSelectionModel().getSelectedItem();
					SubOrganization organization = new SubOrganization();
					organization.setParentOrganization(selectItem);
					SubOrganizationEditor editor = new SubOrganizationEditor();
					editor.fireEditEvent(new EditEvent<SubOrganization>(
							organization, new GwtCallBack<SubOrganization>() {
								@Override
								protected void _succeed() {
									initData(selectItem, getResult());
								}
							}, false));
				}
			});

			addBeforeShowHandler(new BeforeShowHandler() {
				@Override
				public void onBeforeShow(BeforeShowEvent event) {
					Organization selectItem = OrganizationTreeGrid.this
							.getSelectionModel().getSelectedItem();
					if (selectItem == null) {
						subAdd.disable();
					}
				}
			});

			addHideHandler(new HideHandler() {

				@Override
				public void onHide(HideEvent event) {
					subAdd.enable();
				}
			});
		}
	}

	@Override
	protected void onAfterFirstAttach() {
		super.onAfterFirstAttach();
		Scheduler.get().scheduleDeferred(new ScheduledCommand() {
			@Override
			public void execute() {
				RpcServiceUtils.OrganizationRpcService
						.getTopOrganizations(RpcAsyncCallback
								.dealWith(getCallBack()));
			}
		});
	}

	protected GwtCallBack<List<TopOrganization>> getCallBack() {
		return new GwtCallBack<List<TopOrganization>>() {

			@Override
			protected void _succeed() {
				initData(getResult());
			}

		};
	}

	protected void initData(List<TopOrganization> list) {
		for (TopOrganization topOrganization : list) {
			initData(null, topOrganization);
		}
	}

	protected void initData(Organization parent, Organization menuNode) {
		if (menuNode == null) {
			return;
		}
		if (parent == null) {
			treeStore.add(menuNode);
		} else {
			treeStore.add(parent, menuNode);
		}
		Set<SubOrganization> nodes = menuNode.getSubOrganizations();
		if (nodes == null || nodes.size() == 0) {
			return;
		}
		for (Organization node : nodes) {
			initData(menuNode, node);
		}
	}
}
