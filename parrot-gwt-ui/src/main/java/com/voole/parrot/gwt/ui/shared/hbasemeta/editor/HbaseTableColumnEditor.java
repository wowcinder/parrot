package com.voole.parrot.gwt.ui.shared.hbasemeta.editor;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.TextArea;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.voole.parrot.gwt.common.shared.core.combox.EnumComboBox;
import com.voole.parrot.gwt.common.shared.core.editor.SimpleWindowEditor;
import com.voole.parrot.gwt.common.shared.rpcservice.RpcServiceUtils;
import com.voole.parrot.shared.entity.hbasemeta.ColumnType;
import com.voole.parrot.shared.entity.hbasemeta.HbaseTableColumn;

public class HbaseTableColumnEditor extends
		SimpleWindowEditor<HbaseTableColumn> {

	protected interface Driver extends
			SimpleBeanEditorDriver<HbaseTableColumn, HbaseTableColumnEditor> {

	}

	public HbaseTableColumnEditor() {
		super(GWT.<Driver> create(Driver.class), "字段");
	}

	TextField name;
	TextField shortname;
	TextArea desc;
	EnumComboBox<ColumnType> type;

	@Override
	protected void update(HbaseTableColumn t) {
		RpcServiceUtils.HbaseTableMetaRpcService.modifyHbaseTableColumn(t,
				getSaveOrUpdateAsyncCallback());
	}

	@Override
	protected void add(HbaseTableColumn t) {
		RpcServiceUtils.HbaseTableMetaRpcService.createHbaseTableColumn(t,
				getSaveOrUpdateAsyncCallback());
	}

	@Override
	protected void _initView() {
		name = new TextField();
		shortname = new TextField();
		desc = new TextArea();
		type = new EnumComboBox<ColumnType>(ColumnType.values());

		layoutContainer.add(new FieldLabel(name, "name"), vd);
		layoutContainer.add(new FieldLabel(shortname, "shortname"), vd);
		layoutContainer.add(new FieldLabel(type, "type"), vd);
		layoutContainer.add(new FieldLabel(desc, "desc"), vd);
	}

}
