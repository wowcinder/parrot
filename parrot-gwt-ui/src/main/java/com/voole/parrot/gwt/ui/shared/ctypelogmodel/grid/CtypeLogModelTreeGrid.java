/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.parrot.gwt.ui.shared.ctypelogmodel.grid;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.TreeStore;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.voole.parrot.gwt.common.shared.RpcAsyncCallback;
import com.voole.parrot.gwt.common.shared.core.cell.TreeValueProvider;
import com.voole.parrot.gwt.common.shared.core.grid.FixedTreeGrid;
import com.voole.parrot.gwt.common.shared.core.tree.FixedTreeGridIconProvider;
import com.voole.parrot.gwt.common.shared.rpcservice.RpcServiceUtils;
import com.voole.parrot.shared.entity.EntityHasAutoId;
import com.voole.parrot.shared.entity.ctypelogmeta.CtypeLogModel;
import com.voole.parrot.shared.entity.ctypelogmeta.CtypeLogModelVersion;

/**
 * @author XuehuiHe
 * @date 2013年12月26日
 */
public class CtypeLogModelTreeGrid extends FixedTreeGrid<EntityHasAutoId> {

	private static ColumnModel<EntityHasAutoId> cm;
	private static ColumnConfig<EntityHasAutoId, String> name;

	static {
		List<ColumnConfig<EntityHasAutoId, ?>> l = new ArrayList<ColumnConfig<EntityHasAutoId, ?>>();
		name = new ColumnConfig<EntityHasAutoId, String>(
				new TreeValueProvider<EntityHasAutoId, String>() {
					@Override
					protected String _getValue(EntityHasAutoId object) {
						if (object instanceof CtypeLogModel) {
							return ((CtypeLogModel) object).getName();
						} else if (object instanceof CtypeLogModelVersion) {
							return ((CtypeLogModelVersion) object).getVersion();
						}
						return null;
					}
				}, 200, "name");

		ColumnConfig<EntityHasAutoId, String> desc = new ColumnConfig<EntityHasAutoId, String>(
				new TreeValueProvider<EntityHasAutoId, String>() {
					@Override
					protected String _getValue(EntityHasAutoId object) {
						if (object instanceof CtypeLogModel) {
							return ((CtypeLogModel) object).getDesc();
						} else if (object instanceof CtypeLogModelVersion) {
							return ((CtypeLogModelVersion) object).getDesc();
						}
						return null;
					}
				}, 200, "desc");

		l.add(name);
		l.add(desc);

		cm = new ColumnModel<EntityHasAutoId>(l);
	}

	protected static class KeyProvider implements
			ModelKeyProvider<EntityHasAutoId> {
		@Override
		public String getKey(EntityHasAutoId item) {
			if (item == null) {
				return null;
			}
			if (item instanceof CtypeLogModel) {
				return "m-" + item.getId();
			}
			return "v-" + item.getId();
		}
	}

	public CtypeLogModelTreeGrid() {
		super(new TreeStore<EntityHasAutoId>(new KeyProvider()), cm, name);
		setContextMenu(new CtypeLogModelTreeGridMenu(this));
		setIconProvider(new FixedTreeGridIconProvider<EntityHasAutoId>(this) {

			@Override
			protected boolean isFolderModel(EntityHasAutoId model) {
				if (model instanceof CtypeLogModel) {
					return true;
				}
				return false;
			}
		});
	}

	@Override
	protected void onAfterFirstAttach() {
		super.onAfterFirstAttach();
		Scheduler.get().scheduleDeferred(new ScheduledCommand() {

			@Override
			public void execute() {
				mask("加载中...");
				RpcServiceUtils.CtypeLogModelRpcService
						.listModelWithVersions(new RpcAsyncCallback<List<CtypeLogModel>>() {
							@Override
							protected void _onSuccess(List<CtypeLogModel> result) {
								initDate(result);
							}

							@Override
							public void post() {
								super.post();
								unmask();
							}
						});
			}
		});
	}

	private void initDate(List<CtypeLogModel> result) {
		for (CtypeLogModel model : result) {
			treeStore.add(model);
			List<CtypeLogModelVersion> versions = model.getVersions();
			if (versions != null && versions.size() > 0) {
				for (CtypeLogModelVersion version : versions) {
					treeStore.add(model, version);
				}
			}
		}
		expandAll();
	}

}
