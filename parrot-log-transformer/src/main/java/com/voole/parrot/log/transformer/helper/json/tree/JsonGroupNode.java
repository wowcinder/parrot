/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.parrot.log.transformer.helper.json.tree;

import com.voole.parrot.shared.entity.logmeta.LogModelGroupColumn;

/**
 * @author XuehuiHe
 * @date 2013年12月31日
 */
public class JsonGroupNode implements JsonNode {
	private final JsonTrunkNode trunkNode;
	private final LogModelGroupColumn groupColumn;

	public JsonGroupNode(JsonTrunkNode trunkNode,
			LogModelGroupColumn groupColumn) {
		this.trunkNode = trunkNode;
		this.groupColumn = groupColumn;
	}

	public JsonTrunkNode getTrunkNode() {
		return trunkNode;
	}

	public LogModelGroupColumn getGroupColumn() {
		return groupColumn;
	}

}
