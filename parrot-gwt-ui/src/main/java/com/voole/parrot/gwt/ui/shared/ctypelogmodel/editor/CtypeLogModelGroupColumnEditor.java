/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.parrot.gwt.ui.shared.ctypelogmodel.editor;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.TextArea;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.voole.parrot.gwt.common.shared.core.editor.SimpleWindowEditor;
import com.voole.parrot.gwt.common.shared.rpcservice.RpcServiceUtils;
import com.voole.parrot.gwt.ui.shared.ctypelogmodel.field.HbaseTableVersionField;
import com.voole.parrot.shared.entity.ctypelogmeta.CtypeLogModelGroupColumn;

/**
 * @author XuehuiHe
 * @date 2013年12月26日
 */
public class CtypeLogModelGroupColumnEditor extends
		SimpleWindowEditor<CtypeLogModelGroupColumn> {

	protected interface Driver
			extends
			SimpleBeanEditorDriver<CtypeLogModelGroupColumn, CtypeLogModelGroupColumnEditor> {

	}

	public CtypeLogModelGroupColumnEditor() {
		super(GWT.<Driver> create(Driver.class), "group_column");
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void update(CtypeLogModelGroupColumn t) {
		RpcServiceUtils.CtypeLogModelRpcService.modifyColumn(t,
				getSaveOrUpdateAsyncCallback());
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	protected AsyncCallback getSaveOrUpdateAsyncCallback() {
		return super.getSaveOrUpdateAsyncCallback();
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void add(CtypeLogModelGroupColumn t) {
		RpcServiceUtils.CtypeLogModelRpcService.createColumn(t,
				getSaveOrUpdateAsyncCallback());
	}

	TextField name;
	TextArea desc;
	HbaseTableVersionField hbaseTableVersion;

	@Override
	protected void _initView() {
		name = new TextField();
		desc = new TextArea();
		hbaseTableVersion = new HbaseTableVersionField();

		layoutContainer.add(new FieldLabel(name, "name"), vd);
		layoutContainer.add(new FieldLabel(desc, "desc"), vd);
		layoutContainer.add(new FieldLabel(hbaseTableVersion, "table_version"),
				vd);
	}
}
