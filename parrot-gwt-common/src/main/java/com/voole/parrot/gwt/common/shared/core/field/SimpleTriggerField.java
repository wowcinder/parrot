package com.voole.parrot.gwt.common.shared.core.field;

import com.sencha.gxt.widget.core.client.form.TriggerField;

public class SimpleTriggerField<T> extends TriggerField<T> {

	public SimpleTriggerField(SimpleFieldCell<T> cell,
			SimplePropertyEditor<T> propertyEditor) {
		super(cell, propertyEditor);
		setEditable(false);
		redraw();
	}

	@Override
	public SimpleFieldCell<T> getCell() {
		return (SimpleFieldCell<T>) super.getCell();
	}

	@Override
	public SimplePropertyEditor<T> getPropertyEditor() {
		return (SimplePropertyEditor<T>) super.getPropertyEditor();
	}
}
