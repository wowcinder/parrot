/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.parrot.gwt.ui.shared.logmodel.editor;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.TextArea;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.voole.parrot.gwt.common.shared.core.combox.EnumComboBox;
import com.voole.parrot.gwt.common.shared.core.editor.SimpleWindowEditor;
import com.voole.parrot.gwt.common.shared.rpcservice.RpcServiceUtils;
import com.voole.parrot.shared.entity.logmeta.LogModel;
import com.voole.parrot.shared.entity.logmeta.LogModel.LogModelType;

/**
 * @author XuehuiHe
 * @date 2013年12月26日
 */
public class LogModelEditor extends SimpleWindowEditor<LogModel> {

	protected interface Driver extends
			SimpleBeanEditorDriver<LogModel, LogModelEditor> {

	}

	public LogModelEditor() {
		super(GWT.<Driver> create(Driver.class), "model");
	}

	@Override
	protected void update(LogModel t) {
		RpcServiceUtils.LogModelRpcService.modifyModel(t,
				getSaveOrUpdateAsyncCallback());
	}

	@Override
	protected void add(LogModel t) {
		RpcServiceUtils.LogModelRpcService.createModel(t,
				getSaveOrUpdateAsyncCallback());
	}

	TextField name;
	TextArea desc;
	EnumComboBox<LogModelType> type;

	@Override
	protected void _initView() {
		name = new TextField();
		desc = new TextArea();
		type = new EnumComboBox<LogModelType>(LogModelType.values());

		layoutContainer.add(new FieldLabel(name, "name"), vd);
		layoutContainer.add(new FieldLabel(type, "type"), vd);
		layoutContainer.add(new FieldLabel(desc, "desc"), vd);
	}
}
