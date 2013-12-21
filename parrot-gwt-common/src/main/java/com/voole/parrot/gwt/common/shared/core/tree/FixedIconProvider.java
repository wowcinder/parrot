package com.voole.parrot.gwt.common.shared.core.tree;

import com.google.gwt.resources.client.ImageResource;
import com.sencha.gxt.data.shared.IconProvider;
import com.sencha.gxt.widget.core.client.tree.Tree;
import com.sencha.gxt.widget.core.client.tree.Tree.TreeAppearance;
import com.sencha.gxt.widget.core.client.tree.TreeStyle;

public abstract class FixedIconProvider<M> implements IconProvider<M> {
	private final Tree<M, ?> tree;

	public FixedIconProvider(Tree<M, ?> tree) {
		this.tree = tree;
	}

	@Override
	public ImageResource getIcon(M model) {
		TreeAppearance appearance = tree.getAppearance();
		ImageResource style = null;
		TreeStyle ts = tree.getStyle();
		if (!tree.isLeaf(model)) {
			if (tree.isExpanded(model)) {
				style = ts.getNodeOpenIcon() != null ? ts.getNodeOpenIcon()
						: appearance.openNodeIcon();
			} else {
				style = ts.getNodeCloseIcon() != null ? ts.getNodeCloseIcon()
						: appearance.closeNodeIcon();
			}
		} else if (isFolderModel(model)) {
			style = ts.getNodeCloseIcon() != null ? ts.getNodeCloseIcon()
					: appearance.closeNodeIcon();
		} else {
			style = ts.getLeafIcon();
		}
		return style;
	}

	protected abstract boolean isFolderModel(M model);

}
