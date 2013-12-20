/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.parrot.gwt.ui.shared.menu.editor;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.voole.parrot.gwt.common.shared.core.editor.SimpleWindowEditor;
import com.voole.parrot.shared.entity.menu.Menu;

/**
 * @author XuehuiHe
 * @date 2013年12月20日
 */
public class MenuEditor extends SimpleWindowEditor<Menu> {

	public interface Driver extends SimpleBeanEditorDriver<Menu, MenuEditor> {

	}

	public MenuEditor() {
		super(GWT.<Driver> create(Driver.class), "菜单");
	}

	@Override
	protected void update(Menu t) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void add(Menu t) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void _initView() {
		// TODO Auto-generated method stub

	}

}
