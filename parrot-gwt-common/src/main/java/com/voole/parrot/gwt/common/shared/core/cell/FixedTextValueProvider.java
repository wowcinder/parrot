package com.voole.parrot.gwt.common.shared.core.cell;

import com.sencha.gxt.core.client.ValueProvider;

public class FixedTextValueProvider<T> implements ValueProvider<T, String> {
	private final String fixedText;

	public FixedTextValueProvider(String fixedText) {
		this.fixedText = fixedText;
	}

	@Override
	public String getValue(T object) {
		return fixedText;
	}

	@Override
	public void setValue(T object, String value) {

	}

	@Override
	public String getPath() {
		return null;
	}

}
