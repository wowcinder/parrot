package com.voole.parrot.gwt.common.shared.core.grid;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.Callback;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.core.client.IdentityValueProvider;
import com.sencha.gxt.core.client.Style.SelectionMode;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.loader.DataProxy;
import com.sencha.gxt.data.shared.loader.ListLoadResult;
import com.sencha.gxt.data.shared.loader.LoadEvent;
import com.sencha.gxt.data.shared.loader.LoadHandler;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoader;
import com.sencha.gxt.widget.core.client.grid.CheckBoxSelectionModel;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.toolbar.PagingToolBar;
import com.voole.parrot.gwt.common.shared.RpcAsyncCallback;
import com.voole.parrot.shared.condition.QueryCondition;
import com.voole.parrot.shared.grid.GwtListLoadConfigBean;
import com.voole.parrot.shared.grid.GwtPagingLoadConfigBean;

public class GridBuilder<M> {
	protected boolean isMultiSelect;
	protected final ModelKeyProvider<M> keyProvider;
	protected final List<ColumnConfig<M, ?>> columns;

	protected RpcPagingProxyLoad<M, ?, ?> pagingProxyLoad;
	protected RpcListProxyLoad<M, ?, ?> listProxyLoad;
	protected boolean isShowPagingBar;
	protected PagingToolBar pagingToolBar;
	protected int pageSize;
	protected boolean isShowDataImmediately;

	public GridBuilder(ModelKeyProvider<M> keyProvider) {
		this.pageSize = 50;
		this.keyProvider = keyProvider;
		this.columns = new ArrayList<ColumnConfig<M, ?>>();
		isMultiSelect = true;
		isShowPagingBar = true;
		isShowDataImmediately = true;
	}

	public void disableMultiSelect() {
		isMultiSelect = false;
	}

	public void enableMultiSelect() {
		isMultiSelect = true;
	}

	public Grid<M> create() {
		CheckBoxSelectionModel<M> sm = null;
		if (isMultiSelect) {
			IdentityValueProvider<M> identity = new IdentityValueProvider<M>();
			sm = new CheckBoxSelectionModel<M>(identity);
			sm.setSelectionMode(SelectionMode.MULTI);
			columns.add(0, sm.getColumn());
		}
		ColumnModel<M> cm = new ColumnModel<M>(columns);
		Grid<M> grid = null;
		if ((listProxyLoad != null || pagingProxyLoad != null)
				&& isShowDataImmediately) {
			grid = new Grid<M>(new ListStore<M>(keyProvider), cm) {
				protected void onAfterFirstAttach() {
					super.onAfterFirstAttach();
					Scheduler.get().scheduleDeferred(new ScheduledCommand() {
						@Override
						public void execute() {
							getLoader().load();
						}
					});
				};
			};
		} else {
			grid = new Grid<M>(new ListStore<M>(keyProvider), cm);
		}
		if (sm != null) {
			grid.setSelectionModel(sm);
		}
		if (this.pagingProxyLoad != null) {
			grid.setLoader(pagingProxyLoad.getPagingLoader(grid.getStore()));
		} else if (this.listProxyLoad != null) {
			grid.setLoader(listProxyLoad.getPagingLoader(grid.getStore()));
		}
		grid.setLoadMask(true);
		return grid;
	}

	public void addColumns(List<ColumnConfig<M, ?>> columns) {
		this.columns.addAll(columns);
	}

	public void addColumn(ColumnConfig<M, ?> column) {
		this.columns.add(column);
	}

	public void setPagingProxyLoad(RpcPagingProxyLoad<M, ?, ?> pagingProxyLoad) {
		this.pagingProxyLoad = pagingProxyLoad;
	}

	public void setListProxyLoad(RpcListProxyLoad<M, ?, ?> listProxyLoad) {
		this.listProxyLoad = listProxyLoad;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public PagingToolBar getPagingToolBar(Grid<M> grid) {
		if (pagingProxyLoad != null && isShowPagingBar) {
			pagingToolBar = new PagingToolBar(this.pageSize);
			pagingToolBar.getElement().getStyle()
					.setProperty("borderBottom", "none");
			pagingToolBar.bind((PagingLoader) grid.getLoader());
		}
		return pagingToolBar;
	}

	public void setShowDataImmediately(boolean isShowDataImmediately) {
		this.isShowDataImmediately = isShowDataImmediately;
	}

	public static abstract class SimpleRpcListProxyLoad<M> extends
			RpcListProxyLoad<M, QueryCondition, ListLoadResult<M>> {

		public abstract void load(AsyncCallback<List<M>> callback);

		@Override
		public void load(GwtListLoadConfigBean<QueryCondition> loadConfig,
				final AsyncCallback<ListLoadResult<M>> callback) {
			load(new AsyncCallback<List<M>>() {

				@Override
				public void onFailure(Throwable caught) {
					callback.onFailure(caught);
				}

				@SuppressWarnings("serial")
				@Override
				public void onSuccess(final List<M> result) {
					callback.onSuccess(new ListLoadResult<M>() {
						@Override
						public List<M> getData() {
							return result;
						}
					});
				}
			});
		}
	}

	public static abstract class RpcListProxyLoad<M, QC extends QueryCondition, PR extends ListLoadResult<M>> {
		public abstract void load(GwtListLoadConfigBean<QC> loadConfig,
				AsyncCallback<PR> callback);

		public DataProxy<GwtListLoadConfigBean<QC>, PR> getProxy() {
			return new DataProxy<GwtListLoadConfigBean<QC>, PR>() {
				public void load(GwtListLoadConfigBean<QC> loadConfig,
						AsyncCallback<PR> callback) {
					RpcListProxyLoad.this.load(loadConfig, callback);
				}

				@Override
				public void load(GwtListLoadConfigBean<QC> loadConfig,
						final Callback<PR, Throwable> callback) {
					load(loadConfig, new RpcAsyncCallback<PR>() {
						@Override
						public void onFailure(Throwable caught) {
							super.onFailure(caught);
							callback.onFailure(caught);
						}

						@Override
						protected void _onSuccess(PR result) {
							callback.onSuccess(result);
						}
					});
				}
			};
		}

		public GwtListLoader<M, QC, PR> getPagingLoader(ListStore<M> store) {
			GwtListLoader<M, QC, PR> loader = new GwtListLoader<M, QC, PR>(
					getProxy());
			loader.setRemoteSort(true);
			if (store != null) {
				loader.addLoadHandler(new LoadResultListStoreBinding(store));
			}
			loader.setRemoteSort(true);
			return loader;
		}

		public class LoadResultListStoreBinding implements
				LoadHandler<GwtListLoadConfigBean<QC>, PR> {
			protected ListStore<M> store;

			public LoadResultListStoreBinding(ListStore<M> store) {
				this.store = store;
			}

			@Override
			public void onLoad(LoadEvent<GwtListLoadConfigBean<QC>, PR> event) {
				ListLoadResult<M> loaded = event.getLoadResult();
				store.replaceAll(loaded.getData());
			}

		}
	}

	public static abstract class RpcPagingProxyLoad<M, QC extends QueryCondition, PR extends PagingLoadResult<M>> {
		public abstract void load(GwtPagingLoadConfigBean<QC> loadConfig,
				AsyncCallback<PR> callback);

		public DataProxy<GwtPagingLoadConfigBean<QC>, PR> getProxy() {
			return new DataProxy<GwtPagingLoadConfigBean<QC>, PR>() {
				public void load(GwtPagingLoadConfigBean<QC> loadConfig,
						AsyncCallback<PR> callback) {
					RpcPagingProxyLoad.this.load(loadConfig, callback);
				}

				@Override
				public void load(GwtPagingLoadConfigBean<QC> loadConfig,
						final Callback<PR, Throwable> callback) {
					load(loadConfig, new RpcAsyncCallback<PR>() {
						@Override
						public void onFailure(Throwable caught) {
							super.onFailure(caught);
							callback.onFailure(caught);
						}

						@Override
						protected void _onSuccess(PR result) {
							callback.onSuccess(result);
						}
					});
				}

			};
		}

		public GwtPagingLoader<M, QC, PR> getPagingLoader(ListStore<M> store) {
			GwtPagingLoader<M, QC, PR> loader = new GwtPagingLoader<M, QC, PR>(
					getProxy());
			loader.setRemoteSort(true);
			if (store != null) {
				loader.addLoadHandler(new LoadResultListStoreBinding(store));
			}
			return loader;
		}

		public class LoadResultListStoreBinding implements
				LoadHandler<GwtPagingLoadConfigBean<QC>, PR> {
			protected ListStore<M> store;

			public LoadResultListStoreBinding(ListStore<M> store) {
				this.store = store;
			}

			@Override
			public void onLoad(LoadEvent<GwtPagingLoadConfigBean<QC>, PR> event) {
				ListLoadResult<M> loaded = event.getLoadResult();
				store.replaceAll(loaded.getData());
			}

		}
	}

}
