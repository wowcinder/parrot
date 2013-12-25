/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.parrot.gwt.ui.shared.hbasemeta.grid;

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
import com.voole.parrot.shared.entity.hbasemeta.HbaseTable;
import com.voole.parrot.shared.entity.hbasemeta.HbaseTableVersion;

/**
 * @author XuehuiHe
 * @date 2013年12月25日
 */
public class HbaseTableTreeGrid extends FixedTreeGrid<EntityHasAutoId> {

	private static ColumnModel<EntityHasAutoId> cm;
	private static ColumnConfig<EntityHasAutoId, String> name;

	static {
		List<ColumnConfig<EntityHasAutoId, ?>> l = new ArrayList<ColumnConfig<EntityHasAutoId, ?>>();
		name = new ColumnConfig<EntityHasAutoId, String>(
				new TreeValueProvider<EntityHasAutoId, String>() {
					@Override
					protected String _getValue(EntityHasAutoId object) {
						if (object instanceof HbaseTable) {
							return ((HbaseTable) object).getName();
						} else if (object instanceof HbaseTableVersion) {
							return ((HbaseTableVersion) object).getVersion();
						}
						return null;
					}
				}, 200, "name");

		ColumnConfig<EntityHasAutoId, String> desc = new ColumnConfig<EntityHasAutoId, String>(
				new TreeValueProvider<EntityHasAutoId, String>() {
					@Override
					protected String _getValue(EntityHasAutoId object) {
						if (object instanceof HbaseTable) {
							return ((HbaseTable) object).getDesc();
						} else if (object instanceof HbaseTableVersion) {
							return ((HbaseTableVersion) object).getDesc();
						}
						return null;
					}
				}, 200, "desc");
		ColumnConfig<EntityHasAutoId, String> shortname = new ColumnConfig<EntityHasAutoId, String>(
				new TreeValueProvider<EntityHasAutoId, String>() {
					@Override
					protected String _getValue(EntityHasAutoId object) {
						if (object instanceof HbaseTable) {
							return ((HbaseTable) object).getShortname();
						}
						return null;
					}
				}, 200, "shortname");

		l.add(name);
		l.add(desc);
		l.add(shortname);

		cm = new ColumnModel<EntityHasAutoId>(l);
	}

	protected static class KeyProvider implements
			ModelKeyProvider<EntityHasAutoId> {
		@Override
		public String getKey(EntityHasAutoId item) {
			if (item == null) {
				return null;
			}
			if (item instanceof HbaseTable) {
				return "t-" + item.getId();
			}
			return "v-" + item.getId();
		}
	}

	public HbaseTableTreeGrid() {
		super(new TreeStore<EntityHasAutoId>(new KeyProvider()), cm, name);
		setIconProvider(new FixedTreeGridIconProvider<EntityHasAutoId>(this) {
			@Override
			protected boolean isFolderModel(EntityHasAutoId model) {
				if (model instanceof HbaseTable) {
					return true;
				}
				return false;
			}
		});
		setContextMenu(new HbaseTableTreeGridMenu(this));
	}

	@Override
	protected void onAfterFirstAttach() {
		super.onAfterFirstAttach();
		Scheduler.get().scheduleDeferred(new ScheduledCommand() {

			@Override
			public void execute() {
				mask("加载中...");
				RpcServiceUtils.HbaseTableMetaRpcService
						.listHbaseTables(new RpcAsyncCallback<List<HbaseTable>>() {
							@Override
							protected void _onSuccess(List<HbaseTable> result) {
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

	protected void initDate(List<HbaseTable> result) {
		for (HbaseTable hbaseTable : result) {
			treeStore.add(hbaseTable);
			List<HbaseTableVersion> versions = hbaseTable.getVersions();
			if (versions != null && versions.size() > 0) {
				for (HbaseTableVersion hbaseTableVersion : versions) {
					treeStore.add(hbaseTable, hbaseTableVersion);
				}
			}
		}
		expandAll();
	}

}
