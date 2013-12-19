package com.voole.parrot.gwt.common.shared.core.editor;

import com.google.gwt.editor.client.SimpleBeanEditorDriver;

public abstract class SimpleWindowEditor<T> extends WindowEditor<T> {

	public SimpleWindowEditor(
			SimpleBeanEditorDriver<T, ? extends WindowEditor<T>> driver,
			String baseHeadingText) {
		super(driver, baseHeadingText);
	}

	protected abstract void update(T t);

	protected abstract void add(T t);

	@Override
	protected void saveOrUpdate() {
		T t = flush();
		if (getCurrEditEvent().isUpdate()) {
			update(t);
		} else {
			add(t);
		}
	}

}
