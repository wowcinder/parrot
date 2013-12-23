/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.parrot.gwt.ui.shared.menu.editor;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.voole.parrot.gwt.common.shared.VerticalLayoutDataUtil;
import com.voole.parrot.gwt.common.shared.core.editor.SimpleWindowEditor;
import com.voole.parrot.gwt.common.shared.rpcservice.RpcServiceUtils;
import com.voole.parrot.shared.entity.menu.Menu;

/**
 * @author XuehuiHe
 * @date 2013年12月20日
 */
public class MenuEditor extends SimpleWindowEditor<Menu> {

	public interface Driver extends SimpleBeanEditorDriver<Menu, MenuEditor> {

	}

	TextField name;
	TextField token;
	AuthorityField requireAuthority;

	public MenuEditor() {
		super(GWT.<Driver> create(Driver.class), "菜单");
	}

	@Override
	protected void update(Menu t) {
		RpcServiceUtils.MenuNodeRpcService.update(t,
				getSaveOrUpdateAsyncCallback());
	}

	@Override
	protected void add(Menu t) {
		RpcServiceUtils.MenuNodeRpcService.persist(t,
				getSaveOrUpdateAsyncCallback());
	}

	@Override
	protected void _initView() {
		name = new TextField();
		token = new TextField();
		requireAuthority = new AuthorityField();

		layoutContainer.add(new FieldLabel(name, "name"),
				VerticalLayoutDataUtil.smallVd);
		layoutContainer.add(new FieldLabel(token, "token"),
				VerticalLayoutDataUtil.smallVd);
		layoutContainer.add(new FieldLabel(requireAuthority, "authority"),
				VerticalLayoutDataUtil.smallVd);
	}

}
