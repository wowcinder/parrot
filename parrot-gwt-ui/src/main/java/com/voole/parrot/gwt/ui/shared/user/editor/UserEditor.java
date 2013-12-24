package com.voole.parrot.gwt.ui.shared.user.editor;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.PasswordField;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.voole.parrot.gwt.common.shared.core.editor.SimpleWindowEditor;
import com.voole.parrot.gwt.common.shared.rpcservice.RpcServiceUtils;
import com.voole.parrot.shared.entity.user.User;

public class UserEditor extends SimpleWindowEditor<User> {
	public interface Driver extends SimpleBeanEditorDriver<User, UserEditor> {

	}

	TextField name;
	PasswordField password;

	public UserEditor() {
		super(GWT.<Driver> create(Driver.class), "用户");
	}

	@Override
	protected void update(User t) {

	}

	@Override
	protected void add(User t) {
		RpcServiceUtils.UserRpcService.persist(t,
				getSaveOrUpdateAsyncCallback());
	}

	@Override
	protected void _initView() {
		name = new TextField();
		password = new PasswordField();

		layoutContainer.add(new FieldLabel(name, "name"), vd);
		layoutContainer.add(new FieldLabel(password, "password"), vd);
	}

}
