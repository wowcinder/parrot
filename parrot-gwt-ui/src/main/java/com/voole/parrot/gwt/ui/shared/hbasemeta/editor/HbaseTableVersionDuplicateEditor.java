package com.voole.parrot.gwt.ui.shared.hbasemeta.editor;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.TextArea;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.voole.parrot.gwt.common.shared.core.editor.SimpleWindowEditor;
import com.voole.parrot.gwt.common.shared.rpcservice.RpcServiceUtils;
import com.voole.parrot.shared.entity.hbasemeta.HbaseTableVersion;

public class HbaseTableVersionDuplicateEditor extends
		SimpleWindowEditor<HbaseTableVersion> {
	protected interface Driver
			extends
			SimpleBeanEditorDriver<HbaseTableVersion, HbaseTableVersionDuplicateEditor> {

	}

	private final HbaseTableVersion from;
	TextField version;
	TextArea desc;

	public HbaseTableVersionDuplicateEditor(HbaseTableVersion from) {
		super(GWT.<Driver> create(Driver.class), "版本");
		this.from = from;
	}

	@Override
	protected void update(HbaseTableVersion t) {
	}

	protected void refreshTextInfo() {
		getRoot().setHeadingText(getHeadingText());
		getSaveOrUpdateBt().setText("创建副本");
	}

	protected String getHeadingText() {
		return "创建副本  " + getBaseHeadingText();
	}

	@Override
	protected void add(HbaseTableVersion t) {
		RpcServiceUtils.HbaseTableMetaRpcService.duplicateHbaseTableVerion(t,
				from, getSaveOrUpdateAsyncCallback());
	}

	@Override
	protected void _initView() {
		version = new TextField();
		desc = new TextArea();

		layoutContainer.add(new FieldLabel(version, "version"), vd);
		layoutContainer.add(new FieldLabel(desc, "desc"), vd);

	}

}
