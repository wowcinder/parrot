/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.parrot.log.transformer.helper.json;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.voole.parrot.log.transformer.JsonLogTransformer;
import com.voole.parrot.log.transformer.TypeParser;
import com.voole.parrot.log.transformer.exception.LogTransformException;
import com.voole.parrot.log.transformer.helper.json.tree.JsonGroupNode;
import com.voole.parrot.log.transformer.helper.json.tree.JsonLeafNode;
import com.voole.parrot.log.transformer.helper.json.tree.JsonNode;
import com.voole.parrot.log.transformer.helper.json.tree.JsonTrunkNode;
import com.voole.parrot.shared.entity.hbasemeta.HbaseTableColumn;
import com.voole.parrot.shared.hbasequery.HbaseLogRecord;
import com.voole.parrot.shared.hbasequery.HbaseLogRecordCollection;

public class JsonLogTransformerHelper {
	private final HbaseLogRecordCollection collection;
	private final Date stamp;

	public JsonLogTransformerHelper(Date stamp) {
		collection = new HbaseLogRecordCollection();
		this.stamp = stamp;
	}

	public void analyze(Object data, JsonGroupNode groupNode)
			throws LogTransformException {
		analyzeGroup(data, groupNode, null);
	}

	@SuppressWarnings("unchecked")
	public void analyzeGroup(Object data, JsonGroupNode groupNode,
			HbaseLogRecord parent) throws LogTransformException {
		if (data == null) {
			return;
		}
		if (data instanceof Collection) {
			Collection<?> list = (Collection<?>) data;
			int i = 1;
			for (Object item : list) {
				if (!(item instanceof Map)) {
					continue;
				}
				Map<String, Object> itemData = (Map<String, Object>) item;
				analyzeGroupItem(itemData, groupNode, parent, i);
				i++;
			}
		} else if (data instanceof Map) {
			Map<String, Object> itemData = (Map<String, Object>) data;
			analyzeGroupItem(itemData, groupNode, parent, 1);
		}
	}

	public void analyzeGroupItem(Map<String, Object> data,
			JsonGroupNode groupNode, HbaseLogRecord parent, int index)
			throws LogTransformException {
		if (data == null) {
			return;
		}
		HbaseLogRecord record = new HbaseLogRecord(stamp, groupNode
				.getGroupColumn().getHbaseTableVersion().getVersion());
		collection.add(groupNode.getGroupColumn().getHbaseTableVersion()
				.getTable().getName(), record);
		String key = null;
		if (parent == null) {
			key = JsonLogTransformer.generateKey(stamp);
		} else {
			key = JsonLogTransformer.generateKey(parent.getKey(), index);
		}
		record.setKey(key);
		initRecord(data, groupNode.getTrunkNode(), record);
	}

	@SuppressWarnings("unchecked")
	private void initRecord(Map<String, Object> data, JsonTrunkNode trunkNode,
			HbaseLogRecord record) throws LogTransformException {
		for (Entry<String, JsonNode> entry : trunkNode.entrySet()) {
			String path = entry.getKey();
			if (!data.containsKey(path)) {
				continue;
			}
			Object itemData = data.get(path);
			if (itemData == null) {
				continue;
			}
			JsonNode itemNode = entry.getValue();
			if (itemNode instanceof JsonGroupNode) {
				analyzeGroup(itemData, (JsonGroupNode) itemNode, record);
			} else if (itemNode instanceof JsonTrunkNode) {
				if (itemData instanceof Map) {
					// TODO nested => loop
					initRecord((Map<String, Object>) itemData,
							(JsonTrunkNode) itemNode, record);
				} else if (itemData instanceof Collection) {
					dealSpecialNode((Collection<?>) itemData,
							(JsonTrunkNode) itemNode, record);
				}
			} else if (itemNode instanceof JsonLeafNode) {
				HbaseTableColumn hbaseColumn = ((JsonLeafNode) itemNode)
						.getLeafColumn().getHbaseTableColumn();
				Object o = null;
				try {
					o = TypeParser.parse(data.get(path), hbaseColumn.getType()
							.getClazz());
				} catch (Exception e) {
					String msg = "parse wrong,data:" + itemData
							+ " can't parse to " + " column name:"
							+ hbaseColumn.getName() + " type:"
							+ hbaseColumn.getType().getClazz().getSimpleName();
					throw new LogTransformException(msg);
				}
				if (o != null) {
					record.getData().put(hbaseColumn.getName(), o);
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	private void dealSpecialNode(Collection<?> itemData,
			JsonTrunkNode itemNode, HbaseLogRecord record)
			throws LogTransformException {
		// TODO 优化
		if (itemData == null || itemData.size() != 1) {
			return;
		}
		Object innerCollection = itemData.iterator().next();
		if (!(innerCollection instanceof Collection)) {
			return;
		}
		itemData = (Collection<?>) innerCollection;
		if (itemData == null || itemData.size() == 0) {
			return;
		}
		Object first = itemData.iterator().next();
		if (!(first instanceof Map)) {
			return;
		}
		Map<JsonGroupNode, Set<String>> groupNodeToKeys = new HashMap<JsonGroupNode, Set<String>>();
		for (JsonNode jsonNode : itemNode.values()) {
			if (jsonNode instanceof JsonGroupNode) {
				groupNodeToKeys.put((JsonGroupNode) jsonNode,
						((JsonGroupNode) jsonNode).getTrunkNode().keySet());
			} else {
				return;
			}
		}
		Map<String, Object> firstMap = (Map<String, Object>) first;
		JsonGroupNode groupNode = null;
		int max = 0;
		for (Entry<JsonGroupNode, Set<String>> entry : groupNodeToKeys
				.entrySet()) {
			int contains = containsNum(entry.getValue(), firstMap.keySet());
			if (contains > max) {
				groupNode = entry.getKey();
				max = contains;
			}
		}
		if (groupNode != null) {
			analyzeGroup(itemData, groupNode, record);
		}
	}

	private int containsNum(Set<String> total, Set<String> targets) {
		int num = 0;
		for (String target : targets) {
			if (total.contains(target)) {
				num++;
			}
		}
		return num;
	}

	public HbaseLogRecordCollection getCollection() {
		return collection;
	}

}