/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.parrot.gwt.ui.shared.logmodel.grid;

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
import com.voole.parrot.shared.entity.logmeta.LogModel;
import com.voole.parrot.shared.entity.logmeta.LogModelVersion;

/**
 * @author XuehuiHe
 * @date 2013年12月26日
 */
public class LogModelTreeGrid extends FixedTreeGrid<EntityHasAutoId> {

	private static ColumnModel<EntityHasAutoId> cm;
	private static ColumnConfig<EntityHasAutoId, String> name;

	static {
		List<ColumnConfig<EntityHasAutoId, ?>> l = new ArrayList<ColumnConfig<EntityHasAutoId, ?>>();
		name = new ColumnConfig<EntityHasAutoId, String>(
				new TreeValueProvider<EntityHasAutoId, String>() {
					@Override
					protected String _getValue(EntityHasAutoId object) {
						if (object instanceof LogModel) {
							return ((LogModel) object).getName();
						} else if (object instanceof LogModelVersion) {
							return ((LogModelVersion) object).getVersion();
						}
						return null;
					}
				}, 200, "name");

		ColumnConfig<EntityHasAutoId, String> desc = new ColumnConfig<EntityHasAutoId, String>(
				new TreeValueProvider<EntityHasAutoId, String>() {
					@Override
					protected String _getValue(EntityHasAutoId object) {
						if (object instanceof LogModel) {
							return ((LogModel) object).getDesc();
						} else if (object instanceof LogModelVersion) {
							return ((LogModelVersion) object).getDesc();
						}
						return null;
					}
				}, 200, "desc");

		ColumnConfig<EntityHasAutoId, String> type = new ColumnConfig<EntityHasAutoId, String>(
				new TreeValueProvider<EntityHasAutoId, String>() {
					@Override
					protected String _getValue(EntityHasAutoId object) {
						if (object instanceof LogModel) {
							return ((LogModel) object).getType().name();
						} else if (object instanceof LogModelVersion) {
							return ((LogModelVersion) object).getModel()
									.getType().name();
						}
						return null;
					}
				}, 200, "type");

		l.add(name);
		l.add(type);
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
			if (item instanceof LogModel) {
				return "m-" + item.getId();
			}
			return "v-" + item.getId();
		}
	}

	public LogModelTreeGrid() {
		super(new TreeStore<EntityHasAutoId>(new KeyProvider()), cm, name);
		setContextMenu(new LogModelTreeGridMenu(this));
		setIconProvider(new FixedTreeGridIconProvider<EntityHasAutoId>(this) {

			@Override
			protected boolean isFolderModel(EntityHasAutoId model) {
				if (model instanceof LogModel) {
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
				RpcServiceUtils.LogModelRpcService
						.listModelWithVersions(new RpcAsyncCallback<List<LogModel>>() {
							@Override
							protected void _onSuccess(List<LogModel> result) {
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

	private void initDate(List<LogModel> result) {
		for (LogModel model : result) {
			treeStore.add(model);
			List<LogModelVersion> versions = model.getVersions();
			if (versions != null && versions.size() > 0) {
				for (LogModelVersion version : versions) {
					treeStore.add(model, version);
				}
			}
		}
		expandAll();
	}

}
