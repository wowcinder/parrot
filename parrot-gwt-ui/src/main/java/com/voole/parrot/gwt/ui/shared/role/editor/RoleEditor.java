package com.voole.parrot.gwt.ui.shared.role.editor;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.voole.parrot.gwt.common.shared.core.editor.SimpleWindowEditor;
import com.voole.parrot.gwt.common.shared.rpcservice.RpcServiceUtils;
import com.voole.parrot.shared.entity.authority.Role;

public class RoleEditor extends SimpleWindowEditor<Role> {
	public interface Driver extends SimpleBeanEditorDriver<Role, RoleEditor> {

	}

	TextField name;

	public RoleEditor() {
		super(GWT.<Driver> create(Driver.class), "角色");
	}

	@Override
	protected void update(Role t) {
		RpcServiceUtils.RoleRpcService.changeRoleName(t,
				getSaveOrUpdateAsyncCallback());
	}

	@Override
	protected void add(Role t) {
		RpcServiceUtils.RoleRpcService.createRole(t,
				getSaveOrUpdateAsyncCallback());
	}

	@Override
	protected void _initView() {
		name = new TextField();
		layoutContainer.add(new FieldLabel(name, "name"), vd);
	}

}
