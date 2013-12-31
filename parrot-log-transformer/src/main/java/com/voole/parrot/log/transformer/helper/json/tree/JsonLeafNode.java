/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.parrot.log.transformer.helper.json.tree;

import com.voole.parrot.shared.entity.logmeta.LogModelLeafColumn;

/**
 * @author XuehuiHe
 * @date 2013年12月31日
 */
public class JsonLeafNode implements JsonNode {
	private final LogModelLeafColumn leafColumn;

	public JsonLeafNode(LogModelLeafColumn leafColumn) {
		this.leafColumn = leafColumn;
	}

	public LogModelLeafColumn getLeafColumn() {
		return leafColumn;
	}

}
