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
import com.voole.parrot.gwt.common.shared.core.combox.EnumComboBox;
import com.voole.parrot.gwt.common.shared.core.editor.SimpleWindowEditor;
import com.voole.parrot.gwt.common.shared.rpcservice.RpcServiceUtils;
import com.voole.parrot.gwt.ui.shared.ctypelogmodel.field.HbaseTableColumnField;
import com.voole.parrot.shared.entity.ctypelogmeta.CtypeLogModelLeafColumn;
import com.voole.parrot.shared.entity.hbasemeta.ColumnType;

/**
 * @author XuehuiHe
 * @date 2013年12月26日
 */
public class CtypeLogModelLeafColumnEditor extends
		SimpleWindowEditor<CtypeLogModelLeafColumn> {

	protected interface Driver
			extends
			SimpleBeanEditorDriver<CtypeLogModelLeafColumn, CtypeLogModelLeafColumnEditor> {

	}

	public CtypeLogModelLeafColumnEditor() {
		super(GWT.<Driver> create(Driver.class), "leaf_column");
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void update(CtypeLogModelLeafColumn t) {
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
	protected void add(CtypeLogModelLeafColumn t) {
		RpcServiceUtils.CtypeLogModelRpcService.createColumn(t,
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
