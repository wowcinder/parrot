/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.parrot.gwt.ui.shared.hbasemeta.editor;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.TextArea;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.voole.parrot.gwt.common.shared.core.editor.SimpleWindowEditor;
import com.voole.parrot.gwt.common.shared.rpcservice.RpcServiceUtils;
import com.voole.parrot.shared.entity.hbasemeta.HbaseTable;

/**
 * @author XuehuiHe
 * @date 2013年12月25日
 */
public class HbaseTableEditor extends SimpleWindowEditor<HbaseTable> {
	protected interface Driver extends
			SimpleBeanEditorDriver<HbaseTable, HbaseTableEditor> {

	}

	TextField name;
	TextField shortname;
	TextArea desc;

	public HbaseTableEditor() {
		super(GWT.<Driver> create(Driver.class), "表");
	}

	@Override
	protected void update(HbaseTable t) {
		RpcServiceUtils.HbaseTableMetaRpcService.modifyTable(t,
				getSaveOrUpdateAsyncCallback());
	}

	@Override
	protected void add(HbaseTable t) {
		RpcServiceUtils.HbaseTableMetaRpcService.createTable(t,
				getSaveOrUpdateAsyncCallback());
	}

	@Override
	protected void _initView() {
		name = new TextField();
		shortname = new TextField();
		desc = new TextArea();

		layoutContainer.add(new FieldLabel(name, "name"), vd);
		layoutContainer.add(new FieldLabel(shortname, "shortname"), vd);
		layoutContainer.add(new FieldLabel(desc, "desc"), vd);

	}

}
