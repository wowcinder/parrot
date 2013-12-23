package com.voole.parrot.gwt.ui.shared.menu.editor;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.voole.parrot.gwt.common.shared.core.editor.SimpleWindowEditor;
import com.voole.parrot.gwt.common.shared.rpcservice.RpcServiceUtils;
import com.voole.parrot.shared.entity.menu.MenuGroup;

public class MenuGroupEditor extends SimpleWindowEditor<MenuGroup> {
	public interface Driver extends
			SimpleBeanEditorDriver<MenuGroup, MenuGroupEditor> {

	}

	TextField name;

	public MenuGroupEditor() {
		super(GWT.<Driver> create(Driver.class), "菜单组");
	}

	@Override
	protected void update(MenuGroup t) {
		RpcServiceUtils.MenuNodeRpcService.update(t,
				getSaveOrUpdateAsyncCallback());
	}

	@Override
	protected void add(MenuGroup t) {
		RpcServiceUtils.MenuNodeRpcService.persist(t,
				getSaveOrUpdateAsyncCallback());
	}

	@Override
	protected void _initView() {
		name = new TextField();
		layoutContainer.add(new FieldLabel(name, "name"), vd);
	}

}
