/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.client;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.sencha.gxt.widget.core.client.form.DateField;
import com.sencha.gxt.widget.core.client.form.FormPanel;
import com.sencha.gxt.widget.core.client.form.NumberField;
import com.sencha.gxt.widget.core.client.form.NumberPropertyEditor.LongPropertyEditor;

/**
 * @author XuehuiHe
 * @date 2013年12月20日
 */
public class TestBeanEditor extends FormPanel implements Editor<TestBean> {

	public interface Driver extends
			SimpleBeanEditorDriver<TestBean, TestBeanEditor> {

	}

	private final Driver driver;

	NumberField<Long> id;
	DateField now;

	public TestBeanEditor() {
		id = new NumberField<Long>(new LongPropertyEditor());
		now = new DateField();

		
//		this.add(id);
		this.add(now);

		driver = GWT.<Driver> create(Driver.class);
		driver.initialize(this);
	}
}
