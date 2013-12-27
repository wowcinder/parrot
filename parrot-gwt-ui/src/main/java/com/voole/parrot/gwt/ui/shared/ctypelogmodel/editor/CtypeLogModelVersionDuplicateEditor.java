/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.parrot.gwt.ui.shared.ctypelogmodel.editor;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.TextArea;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.voole.parrot.gwt.common.shared.core.editor.SimpleWindowEditor;
import com.voole.parrot.gwt.common.shared.rpcservice.RpcServiceUtils;
import com.voole.parrot.shared.entity.logmeta.LogModelVersion;

/**
 * @author XuehuiHe
 * @date 2013年12月26日
 */
public class CtypeLogModelVersionDuplicateEditor extends
		SimpleWindowEditor<LogModelVersion> {

	protected interface Driver
			extends
			SimpleBeanEditorDriver<LogModelVersion, CtypeLogModelVersionDuplicateEditor> {

	}

	private final LogModelVersion from;

	public CtypeLogModelVersionDuplicateEditor(LogModelVersion from) {
		super(GWT.<Driver> create(Driver.class), "version");
		this.from = from;
	}

	@Override
	protected void update(LogModelVersion t) {
	}

	@Override
	protected void add(LogModelVersion t) {
		RpcServiceUtils.CtypeLogModelRpcService.duplicateVerion(t, from,
				getSaveOrUpdateAsyncCallback());
	}

	TextField version;
	TextArea desc;

	@Override
	protected void _initView() {
		version = new TextField();
		desc = new TextArea();

		layoutContainer.add(new FieldLabel(version, "version"), vd);
		layoutContainer.add(new FieldLabel(desc, "desc"), vd);
	}

	protected void refreshTextInfo() {
		getRoot().setHeadingText(getHeadingText());
		getSaveOrUpdateBt().setText("创建副本");
	}

	protected String getHeadingText() {
		return "创建副本  " + getBaseHeadingText();
	}
}
