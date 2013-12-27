/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.parrot.gwt.ui.shared.logmodel.editor;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.TextArea;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.voole.parrot.gwt.common.shared.core.editor.SimpleWindowEditor;
import com.voole.parrot.shared.entity.logmeta.LogModel;

/**
 * @author XuehuiHe
 * @date 2013年12月26日
 */
public class Simple extends SimpleWindowEditor<LogModel> {

	protected interface Driver extends
			SimpleBeanEditorDriver<LogModel, Simple> {

	}

	public Simple() {
		super(GWT.<Driver> create(Driver.class), "model");
	}

	@Override
	protected void update(LogModel t) {
	}

	@Override
	protected void add(LogModel t) {
	}

	TextField name;
	TextArea desc;

	@Override
	protected void _initView() {
		name = new TextField();
		desc = new TextArea();

		layoutContainer.add(new FieldLabel(name, "name"), vd);
		layoutContainer.add(new FieldLabel(desc, "desc"), vd);
	}
}
