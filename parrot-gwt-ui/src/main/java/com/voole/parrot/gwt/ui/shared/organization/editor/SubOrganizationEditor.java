package com.voole.parrot.gwt.ui.shared.organization.editor;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.voole.parrot.gwt.common.shared.core.editor.SimpleWindowEditor;
import com.voole.parrot.gwt.common.shared.rpcservice.RpcServiceUtils;
import com.voole.parrot.shared.entity.organization.SubOrganization;

public class SubOrganizationEditor extends SimpleWindowEditor<SubOrganization> {
	public interface Driver extends
			SimpleBeanEditorDriver<SubOrganization, SubOrganizationEditor> {

	}

	TextField name;

	public SubOrganizationEditor() {
		super(GWT.<Driver> create(Driver.class), "top组织");
	}

	@Override
	protected void update(SubOrganization t) {
		// RpcServiceUtils.OrganizationRpcService.changeTopName(t,
		// getSaveOrUpdateAsyncCallback());
	}

	@Override
	protected void add(SubOrganization t) {
		RpcServiceUtils.OrganizationRpcService.createSubOrganization(t,
				getSaveOrUpdateAsyncCallback());
	}

	@Override
	protected void _initView() {
		name = new TextField();

		layoutContainer.add(new FieldLabel(name, "name"), vd);
	}

}
