package com.voole.parrot.gwt.common.shared.core.field;

import java.text.ParseException;

import com.sencha.gxt.widget.core.client.form.PropertyEditor;

public abstract class SimplePropertyEditor<T> extends PropertyEditor<T> {
	private T t;

	public T getEntity() {
		return t;
	}

	public void setEntity(T t) {
		this.t = t;
	}

	@Override
	public T parse(CharSequence text) throws ParseException {
		return t;
	}

}