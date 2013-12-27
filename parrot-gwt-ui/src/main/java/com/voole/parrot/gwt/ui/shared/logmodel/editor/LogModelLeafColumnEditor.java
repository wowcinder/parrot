/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.parrot.gwt.ui.shared.logmodel.editor;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.TextArea;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.voole.parrot.gwt.common.shared.core.combox.EnumComboBox;
import com.voole.parrot.gwt.common.shared.core.editor.SimpleWindowEditor;
import com.voole.parrot.gwt.common.shared.rpcservice.RpcServiceUtils;
import com.voole.parrot.gwt.ui.shared.logmodel.field.HbaseTableColumnField;
import com.voole.parrot.shared.entity.hbasemeta.ColumnType;
import com.voole.parrot.shared.entity.logmeta.LogModelLeafColumn;

/**
 * @author XuehuiHe
 * @date 2013年12月26日
 */
public class LogModelLeafColumnEditor extends
		SimpleWindowEditor<LogModelLeafColumn> {

	protected interface Driver
			extends
			SimpleBeanEditorDriver<LogModelLeafColumn, LogModelLeafColumnEditor> {

	}

	public LogModelLeafColumnEditor() {
		super(GWT.<Driver> create(Driver.class), "leaf_column");
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void update(LogModelLeafColumn t) {
		RpcServiceUtils.LogModelRpcService.modifyColumn(t,
				getSaveOrUpdateAsyncCallback());
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	protected AsyncCallback getSaveOrUpdateAsyncCallback() {
		return super.getSaveOrUpdateAsyncCallback();
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void add(LogModelLeafColumn t) {
		RpcServiceUtils.LogModelRpcService.createColumn(t,
				getSaveOrUpdateAsyncCallback());
	}

	TextField name;
	TextArea desc;
	EnumComboBox<ColumnType> type;
	HbaseTableColumnField hbaseTableColumn;

	@Override
	protected void _initView() {
		name = new TextField();
		desc = new TextArea();
		type = new EnumComboBox<ColumnType>(ColumnType.values());
		hbaseTableColumn = new HbaseTableColumnField(this);

		layoutContainer.add(new FieldLabel(name, "name"), vd);
		layoutContainer.add(new FieldLabel(type, "type"), vd);
		layoutContainer.add(new FieldLabel(hbaseTableColumn, "table_column"),
				vd);
		layoutContainer.add(new FieldLabel(desc, "desc"), vd);
	}
}
