package com.voole.parrot.gwt.common.shared.core.dnd;

import java.util.List;

import com.google.gwt.dom.client.Element;
import com.google.gwt.resources.client.ImageResource;
import com.sencha.gxt.core.client.dom.XElement;
import com.sencha.gxt.core.client.util.Rectangle;
import com.sencha.gxt.data.shared.TreeStore;
import com.sencha.gxt.dnd.core.client.DND.Feedback;
import com.sencha.gxt.dnd.core.client.DndDragMoveEvent;
import com.sencha.gxt.dnd.core.client.Insert;
import com.sencha.gxt.dnd.core.client.TreeGridDropTarget;
import com.sencha.gxt.widget.core.client.tree.Tree.TreeNode;
import com.sencha.gxt.widget.core.client.treegrid.TreeGrid;

public class FixedTreeGridTargetLeafDrop<M> extends TreeGridDropTarget<M> {
	private boolean isAllowDropOnRoot;

	public FixedTreeGridTargetLeafDrop(TreeGrid<M> tree) {
		super(tree);
		isAllowDropOnRoot = true;
	}

	@Override
	protected void handleAppend(DndDragMoveEvent event, TreeNode<M> item) {
		if (!isAllowDropOnRoot() && item == null) {
			Insert.get().hide();
			event.getStatusProxy().setStatus(false);
			return;
		}
		super.handleAppend(event, item);
	}

	@Override
	protected void showFeedback(DndDragMoveEvent event) {
		final TreeNode<M> item = getWidget().findNode(
				event.getDragMoveEvent().getNativeEvent().getEventTarget()
						.<Element> cast());
		if (item == null) {
			if (activeItem != null) {
				clearStyle(activeItem);
			}
		}

		if (item != null
				&& event.getDropTarget().getWidget() == event.getDragSource()
						.getWidget()) {
			@SuppressWarnings("unchecked")
			TreeGrid<M> source = (TreeGrid<M>) event.getDragSource()
					.getWidget();
			List<M> list = source.getSelectionModel().getSelection();
			M overModel = item.getModel();
			for (int i = 0; i < list.size(); i++) {
				M sel = list.get(i);
				if (overModel == sel) {
					Insert.get().hide();
					event.getStatusProxy().setStatus(false);
					return;
				}
				List<M> children = getWidget().getTreeStore().getAllChildren(
						sel);
				if (children.contains(item.getModel())) {
					Insert.get().hide();
					event.getStatusProxy().setStatus(false);
					return;
				}
			}
		}

		boolean append = feedback == Feedback.APPEND
				|| feedback == Feedback.BOTH;
		boolean insert = feedback == Feedback.INSERT
				|| feedback == Feedback.BOTH;

		if (item == null) {
			handleAppend(event, item);
		} else if (insert) {
			handleInsert(event, item);
		} else if ((!getWidget().isLeaf(item.getModel()) || isAllowDropOnLeaf(item))
				&& append) {
			handleAppend(event, item);
		} else {
			if (activeItem != null) {
				clearStyle(activeItem);
			}
			status = -1;
			activeItem = null;
			appendItem = null;
			Insert.get().hide();
			event.getStatusProxy().setStatus(false);
		}
	}

	@Override
	protected void handleInsert(DndDragMoveEvent event, final TreeNode<M> item) {

		int height = getWidget().getView().getRow(item.getModel())
				.getOffsetHeight();
		int mid = height / 2;
		int top = getWidget().getView().getRow(item.getModel())
				.getAbsoluteTop();
		mid += top;
		int y = event.getDragMoveEvent().getNativeEvent().getClientY();
		boolean before = y < mid;

		if ((!getWidget().isLeaf(item.getModel()) || isAllowDropOnLeaf(item))
				&& (feedback == Feedback.BOTH || feedback == Feedback.APPEND)
				&& ((before && y > top + 4) || (!before && y < top + height - 4))) {
			handleAppend(event, item);
			return;
		}

		// clear any active append item
		if (activeItem != null && activeItem != item) {
			clearStyle(activeItem);
		}

		appendItem = null;

		status = before ? 0 : 1;

		if (activeItem != null) {
			clearStyle(activeItem);
		}

		activeItem = item;

		if (activeItem != null) {
			TreeStore<M> store = getWidget().getTreeStore();

			int idx = -1;

			M p = store.getParent(activeItem.getModel());
			if (p != null) {
				idx = store.getChildren(p).indexOf(activeItem.getModel());
			} else {
				idx = store.getRootItems().indexOf(activeItem.getModel());
			}
			if (!isAllowDropOnRoot() && p == null && before) {
				Insert.get().hide();
				event.getStatusProxy().setStatus(false);
				return;
			}
			ImageResource status = resources.dropInsert();
			if (before && idx == 0) {
				status = resources.dropInsertAbove();
			} else if (idx > 1 && !before && p != null
					&& idx == store.getChildCount(p) - 1) {
				status = resources.dropInsertBelow();
			}

			event.getStatusProxy().setStatus(true, status);

			if (before) {
				showInsert(
						event,
						(Element) getWidget().getView().getRow(item.getModel()),
						true);
			} else {
				showInsert(
						event,
						(Element) getWidget().getView().getRow(item.getModel()),
						false);
			}
		}

	}

	private void showInsert(DndDragMoveEvent event, Element elem, boolean before) {
		Insert insert = Insert.get();
		insert.show(elem);
		Rectangle rect = elem.<XElement> cast().getBounds();
		int y = before ? rect.getY() - 2 : (rect.getY() + rect.getHeight() - 4);

		insert.getElement().setBounds(rect.getX(), y, rect.getWidth(), 6);
	}

	public boolean isAllowDropOnRoot() {
		return isAllowDropOnRoot;
	}

	public void setAllowDropOnRoot(boolean isAllowDropOnRoot) {
		this.isAllowDropOnRoot = isAllowDropOnRoot;
	}

	protected boolean isAllowDropOnLeaf(TreeNode<M> item) {
		return isAllowDropOnLeaf() && isDropOnLeafEnabled(item.getModel());
	}

	protected boolean isDropOnLeafEnabled(M m) {
		return true;
	}

}
