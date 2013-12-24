package com.voole.parrot.gwt.ui.shared.organization.grid;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.sencha.gxt.data.shared.TreeStore;
import com.sencha.gxt.data.shared.event.StoreDataChangeEvent;
import com.sencha.gxt.data.shared.event.StoreUpdateEvent;
import com.sencha.gxt.data.shared.event.StoreUpdateEvent.StoreUpdateHandler;
import com.sencha.gxt.widget.core.client.event.BeforeShowEvent;
import com.sencha.gxt.widget.core.client.event.BeforeShowEvent.BeforeShowHandler;
import com.sencha.gxt.widget.core.client.event.HideEvent;
import com.sencha.gxt.widget.core.client.event.HideEvent.HideHandler;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.menu.Item;
import com.sencha.gxt.widget.core.client.menu.Menu;
import com.sencha.gxt.widget.core.client.menu.MenuItem;
import com.sencha.gxt.widget.core.client.menu.SeparatorMenuItem;
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

		// getView().setAutoExpandColumn(nameColumn);
		setContextMenu(new OrganizationTreeGridMenu());

//		store.addStoreUpdateHandler(new StoreUpdateHandler<Organization>() {
//			@Override
//			public void onUpdate(final StoreUpdateEvent<Organization> event) {
//				Scheduler.get().scheduleDeferred(new ScheduledCommand() {
//					@Override
//					public void execute() {
//						store.fireEvent(new StoreDataChangeEvent<Organization>(
//								event.getItems().get(0)));
//					}
//				});
//			}
//		});

	}

	public class OrganizationTreeGridMenu extends Menu {
		private final MenuItem topAdd;
		private final MenuItem subAdd;
		private final MenuItem modifyAdd;
		private SubOrganizationEditor subEditor;
		private TopOrganizationEditor topEditor;

		public OrganizationTreeGridMenu() {
			topAdd = new MenuItem("添加Top组织");
			subAdd = new MenuItem("添加子组织");
			modifyAdd = new MenuItem("修改组织名称");
			this.add(topAdd);
			this.add(subAdd);
			this.add(new SeparatorMenuItem());
			this.add(modifyAdd);
			topAdd.addSelectionHandler(new SelectionHandler<Item>() {
				@Override
				public void onSelection(SelectionEvent<Item> event) {
					getTopEditor().fireEditEvent(
							new EditEvent<TopOrganization>(
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
					getSubEditor().fireEditEvent(
							new EditEvent<SubOrganization>(organization,
									new GwtCallBack<SubOrganization>() {
										@Override
										protected void _succeed() {
											initData(selectItem, getResult());
										}
									}, false));
				}
			});

			modifyAdd.addSelectionHandler(new SelectionHandler<Item>() {

				@Override
				public void onSelection(SelectionEvent<Item> event) {
					final Organization selectItem = OrganizationTreeGrid.this
							.getSelectionModel().getSelectedItem();
					if (selectItem instanceof TopOrganization) {
						TopOrganization top = (TopOrganization) selectItem;
						getTopEditor().fireEditEvent(
								new EditEvent<TopOrganization>(top,
										new GwtCallBack<TopOrganization>() {

											@Override
											protected void _succeed() {
												// OrganizationTreeGrid.this
												// .getStore().update(
												// getResult());
												// OrganizationTreeGrid.this
												// .initData(OrganizationTreeGrid.this
												// .getStore()
												// .getAll());
												store.update(getResult());
//												treeStore.update(getResult());
//												OrganizationTreeGrid.this
//														.refresh(getResult());

												// treeStore.update(getResult());
												// treeStore.commitChanges();
												// treeStore.add(getResult());
											}

										}, true));
					} else {
						SubOrganization sub = (SubOrganization) selectItem;
						getSubEditor().fireEditEvent(
								new EditEvent<SubOrganization>(sub,
										new GwtCallBack<SubOrganization>() {

											@Override
											protected void _succeed() {
												OrganizationTreeGrid.this
														.getStore().update(
																getResult());
												OrganizationTreeGrid.this
														.initData(OrganizationTreeGrid.this
																.getStore()
																.getAll());
											}

										}, true));
					}

				}
			});

			addBeforeShowHandler(new BeforeShowHandler() {
				@Override
				public void onBeforeShow(BeforeShowEvent event) {
					Organization selectItem = OrganizationTreeGrid.this
							.getSelectionModel().getSelectedItem();
					if (selectItem == null) {
						subAdd.disable();
						modifyAdd.disable();
					}
				}
			});

			addHideHandler(new HideHandler() {

				@Override
				public void onHide(HideEvent event) {
					subAdd.enable();
					modifyAdd.enable();
				}
			});
		}

		public SubOrganizationEditor getSubEditor() {
			if (subEditor == null) {
				subEditor = new SubOrganizationEditor();
			}
			return subEditor;
		}

		public TopOrganizationEditor getTopEditor() {
			if (topEditor == null) {
				topEditor = new TopOrganizationEditor();
			}
			return topEditor;
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

	protected <OR extends Organization> void initData(List<OR> list) {
		store.clear();
		treeStore.clear();
		for (OR topOrganization : list) {
			initData(null, topOrganization);
		}
		expandAll();
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
