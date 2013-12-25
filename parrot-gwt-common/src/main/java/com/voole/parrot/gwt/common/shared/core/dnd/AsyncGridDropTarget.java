package com.voole.parrot.gwt.common.shared.core.dnd;

import java.util.List;

import com.sencha.gxt.dnd.core.client.DndDropEvent;
import com.sencha.gxt.dnd.core.client.GridDropTarget;
import com.sencha.gxt.dnd.core.client.DND.Feedback;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.voole.parrot.gwt.common.shared.GwtCallBack;

public abstract class AsyncGridDropTarget<M> extends
		GridDropTarget<M> {

	public AsyncGridDropTarget(Grid<M> grid) {
		super(grid);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void onDragDrop(DndDropEvent e) {
		Object data = e.getData();
		List<M> models = (List<M>) prepareDropData(data, true);
		if (models.size() > 0) {
			final Integer pos = insertIndex;
			async(models, pos);
		}
		insertIndex = -1;
		activeItem = null;
	}

	protected abstract void async(List<M> models, Integer pos);

	protected GwtCallBack<Void> getChangePosCallBack(final List<M> models,
			final Integer pos) {
		return new GwtCallBack<Void>() {
			@Override
			protected void _succeed() {
				afterRpc(models, pos);
			}

			@Override
			protected void post() {
				super.post();
				getGrid().unmask();
			}
		};
	}

	protected void afterRpc(List<M> models, Integer pos) {
		if (feedback == Feedback.APPEND) {
			grid.getStore().addAll(models);
		} else {
			grid.getStore().addAll(pos, models);
		}
	}

}