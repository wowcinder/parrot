/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.parrot.gwt.common.util;

import java.io.File;
import java.io.IOException;

import com.sun.codemodel.JCodeModel;

/**
 * @author XuehuiHe
 * @date 2013年12月20日
 */
public class BaseJavaSourceGenerator {
	protected final JCodeModel jCodeModel;

	public BaseJavaSourceGenerator() {
		jCodeModel = new JCodeModel();
	}

	public void build() throws IOException {
		build(getMainJavaPath());
	}

	public void build(File path) throws IOException {
		jCodeModel.build(path);
	}

	private File getMainJavaPath() {
		String path = JCodeModelUtil.class.getClassLoader().getResource("")
				.getFile();
		File file = new File(path);
		file = file.getParentFile().getParentFile();
		path = file.getAbsolutePath() + File.separator + "src" + File.separator
				+ "main" + File.separator + "java";
		file = new File(path);
		return file;
	}

	public JCodeModel getjCodeModel() {
		return jCodeModel;
	}

}
