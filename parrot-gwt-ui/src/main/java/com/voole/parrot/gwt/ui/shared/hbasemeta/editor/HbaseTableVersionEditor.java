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
import com.voole.parrot.shared.entity.hbasemeta.HbaseTableVersion;

/**
 * @author XuehuiHe
 * @date 2013年12月25日
 */
public class HbaseTableVersionEditor extends
		SimpleWindowEditor<HbaseTableVersion> {
	protected interface Driver extends
			SimpleBeanEditorDriver<HbaseTableVersion, HbaseTableVersionEditor> {

	}

	TextField version;
	TextArea desc;

	public HbaseTableVersionEditor() {
		super(GWT.<Driver> create(Driver.class), "版本");
	}

	@Override
	protected void update(HbaseTableVersion t) {
		RpcServiceUtils.HbaseTableMetaRpcService.modifyHbaseTableVersion(t,
				getSaveOrUpdateAsyncCallback());
	}

	@Override
	protected void add(HbaseTableVersion t) {
		RpcServiceUtils.HbaseTableMetaRpcService.createHbaseTableVersion(t,
				getSaveOrUpdateAsyncCallback());
	}

	@Override
	protected void _initView() {
		version = new TextField();
		desc = new TextArea();

		layoutContainer.add(new FieldLabel(version, "version"), vd);
		layoutContainer.add(new FieldLabel(desc, "desc"), vd);

	}

}
