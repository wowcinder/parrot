/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.parrot.gwt.ui.shared.menu.editor;

import java.text.ParseException;

import com.sencha.gxt.widget.core.client.form.PropertyEditor;
import com.sencha.gxt.widget.core.client.form.TriggerField;
import com.voole.parrot.shared.entity.authority.Authority;

/**
 * @author XuehuiHe
 * @date 2013年12月20日
 */
public class AuthorityTriggerEditor extends TriggerField<Authority> {

	public AuthorityTriggerEditor() {
		super(new PropertyEditor<Authority>() {

			@Override
			public String render(Authority object) {
				return object.getName();
			}

			@Override
			public Authority parse(CharSequence text) throws ParseException {
				return null;
			}
		});
	}

}
