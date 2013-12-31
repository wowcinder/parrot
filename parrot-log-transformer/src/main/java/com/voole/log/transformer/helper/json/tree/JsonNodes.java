/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.log.transformer.helper.json.tree;

import com.voole.parrot.shared.entity.logmeta.LogModelColumn;
import com.voole.parrot.shared.entity.logmeta.LogModelGroupColumn;
import com.voole.parrot.shared.entity.logmeta.LogModelLeafColumn;

/**
 * @author XuehuiHe
 * @date 2013年12月31日
 */
public class JsonNodes {
	public static JsonGroupNode create(LogModelGroupColumn groupColumn) {
		JsonTrunkNode trunkNode = new JsonTrunkNode();
		for (LogModelColumn column : groupColumn.getColumns()) {
			putin(trunkNode, column);
		}
		return new JsonGroupNode(trunkNode, groupColumn);
	}

	private static String preprocessPath(String path) {
		path = path.replaceAll("\\s", "");
		path = path.replaceAll("^\\.+|\\.+$", "");
		path = path.replaceAll("\\.{2,}", "\\.");
		return path;
	}

	public static void putin(JsonTrunkNode trunkNode, LogModelColumn column) {
		String path = column.getName();
		path = preprocessPath(path);
		JsonTrunkNode tempNode = trunkNode;
		String[] paths = path.split("\\.");
		int length = paths.length;
		for (int i = 0; i < length - 1; i++) {
			String itemPath = paths[i];
			if (!tempNode.containsKey(itemPath)) {
				tempNode.put(itemPath, new JsonTrunkNode());
			}
			tempNode = (JsonTrunkNode) tempNode.get(itemPath);
		}
		String lastItemPath = paths[length - 1];
		if (column instanceof LogModelLeafColumn) {
			tempNode.put(lastItemPath, new JsonLeafNode(
					(LogModelLeafColumn) column));
		} else {
			tempNode.put(lastItemPath, create((LogModelGroupColumn) column));
		}
	}
}
