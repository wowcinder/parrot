/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.parrot.log.transformer;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.voole.parrot.log.transformer.exception.LogTransformException;
import com.voole.parrot.log.transformer.exception.LogTransformInitException;
import com.voole.parrot.log.transformer.helper.ctype.CtypeLogOriginalInfo;
import com.voole.parrot.log.transformer.helper.ctype.CtypeLogState;
import com.voole.parrot.shared.entity.hbasemeta.HbaseTableColumn;
import com.voole.parrot.shared.entity.logmeta.LogModel.LogModelType;
import com.voole.parrot.shared.entity.logmeta.LogModelColumn;
import com.voole.parrot.shared.entity.logmeta.LogModelGroupColumn;
import com.voole.parrot.shared.entity.logmeta.LogModelLeafColumn;
import com.voole.parrot.shared.entity.logmeta.LogModelVersion;
import com.voole.parrot.shared.hbasequery.HbaseLogRecord;
import com.voole.parrot.shared.hbasequery.HbaseLogRecordCollection;

/**
 * @author XuehuiHe
 * @date 2013年12月31日
 */
public class CtypeLogTransformer extends LogTransformer {
	private final List<LogModelColumn> columns;
	private LogModelGroupColumn repeatedColumn;
	private Integer repeatedSize;
	private final Integer mainSize;

	private final Map<CtypeLogOriginalInfo, CtypeLogState> logInfoToState;

	public CtypeLogTransformer(LogModelVersion version)
			throws LogTransformInitException {
		super(version);
		logInfoToState = new ConcurrentHashMap<CtypeLogOriginalInfo, CtypeLogState>();
		if (version.getModel().getType() != LogModelType.CTYPE) {
			throw new LogTransformInitException(version,
					"version is not a ctype version");
		}
		columns = version.getRootColumn().getColumns();
		for (LogModelColumn column : columns) {
			if (column instanceof LogModelGroupColumn) {
				if (repeatedColumn == null) {
					repeatedColumn = (LogModelGroupColumn) column;
				} else {
					throw new LogTransformInitException(version,
							"version has multi repeated columns");
				}
				for (LogModelColumn subColumn : ((LogModelGroupColumn) column)
						.getColumns()) {
					if (subColumn instanceof LogModelGroupColumn) {
						throw new LogTransformInitException(version,
								"version has nested repeated columns");
					}

				}
			}
		}
		if (repeatedColumn == null) {
			mainSize = columns.size();
		} else {
			mainSize = columns.size() - 1;
			repeatedSize = repeatedColumn.getColumns().size();
			if (repeatedSize == 0) {
				throw new LogTransformInitException(version,
						"repeated columns size is zero");
			}
		}
	}

	@Override
	protected HbaseLogRecordCollection transform(Date stamp, String log)
			throws LogTransformException {
		HbaseLogRecordCollection collection = new HbaseLogRecordCollection();

		CtypeLogOriginalInfo logOriginalInfo = new CtypeLogOriginalInfo(log);
		CtypeLogState logState = getLogState(logOriginalInfo);
		if (!logState.isMatch()) {
			String msg = "version id:" + getVersion().getId()
					+ " don't match raw:" + log + ","
					+ getColumnsMatchMsg(logOriginalInfo);
			throw new LogTransformException(msg);
		}
		String[] logItems = getLogItems(log, logOriginalInfo, logState);

		HbaseLogRecord record = new HbaseLogRecord(stamp, getVersion()
				.getRootColumn().getHbaseTableVersion().getVersion());
		record.setKey(generateKey(stamp));

		collection.add(getVersion().getRootColumn().getHbaseTableVersion()
				.getTable().getName(), record);

		int i = 0;
		for (LogModelColumn itemColumn : columns) {
			if (itemColumn instanceof LogModelLeafColumn) {
				LogModelLeafColumn leafColumn = (LogModelLeafColumn) itemColumn;
				HbaseTableColumn hbaseColumn = leafColumn.getHbaseTableColumn();
				Object t = null;
				try {
					t = TypeParser.parse(logItems[i], hbaseColumn.getType()
							.getClazz());
				} catch (Exception e) {
					String msg = "parse wrong,logItem:" + logItems[i]
							+ " can't parse to " + " column name:"
							+ hbaseColumn.getName() + " type:"
							+ hbaseColumn.getType().getClazz().getSimpleName();
					throw new LogTransformException(msg);
				}
				if (t != null) {
					record.getData().put(hbaseColumn.getName(), t);
				}
				i++;
			} else if (itemColumn instanceof LogModelGroupColumn) {
				LogModelGroupColumn groupColumn = (LogModelGroupColumn) itemColumn;
				for (int j = 0; j < logState.getRepeatTimes(); j++) {
					HbaseLogRecord subRecord = new HbaseLogRecord(stamp,
							groupColumn.getHbaseTableVersion().getVersion());
					subRecord.setKey(generateKey(record.getKey(), j + 1));
					collection.add(groupColumn.getHbaseTableVersion()
							.getTable().getName(), subRecord);
					for (LogModelColumn column : groupColumn.getColumns()) {
						LogModelLeafColumn simpleColumn = (LogModelLeafColumn) column;
						HbaseTableColumn hbaseColumn = simpleColumn
								.getHbaseTableColumn();
						Object t = null;
						try {
							t = TypeParser.parse(logItems[i], hbaseColumn
									.getType().getClazz());
						} catch (Exception e) {
							String msg = "parse wrong,sub logItem:"
									+ logItems[i]
									+ " can't parse to "
									+ " column name:"
									+ hbaseColumn.getName()
									+ " type:"
									+ hbaseColumn.getType().getClazz()
											.getSimpleName();
							throw new LogTransformException(msg);
						}
						if (t != null) {
							subRecord.getData().put(hbaseColumn.getName(), t);
						}
						i++;
					}
				}
			}
		}

		return collection;
	}

	protected String[] getLogItems(String log,
			CtypeLogOriginalInfo originalInfo, CtypeLogState logState) {
		String[] originalStrs = originalInfo.getOriginalStrs();
		String[] logItems = null;
		if (logState.isAddEmptyEnd()) {
			logItems = new String[originalStrs.length + 1];
			for (int i = 0; i < originalStrs.length; i++) {
				logItems[i] = originalStrs[i];
			}
			logItems[originalStrs.length] = "";
		} else {
			logItems = originalStrs;
		}
		return logItems;
	}

	protected String getColumnsMatchMsg(CtypeLogOriginalInfo logOriginalInfo) {
		int length = logOriginalInfo.getLength();
		String info = "fact:" + length;
		if (logOriginalInfo.getIsVerticalEnding()) {
			info += "or " + (length - 1);
		}
		info += ",expect:";
		if (repeatedColumn == null) {
			info += mainSize;
		} else {
			info += mainSize + "+" + repeatedSize + "x";
		}
		return info;
	}

	public List<LogModelColumn> getColumns() {
		return columns;
	}

	public LogModelGroupColumn getRepeatedColumn() {
		return repeatedColumn;
	}

	public Integer getRepeatedSize() {
		return repeatedSize;
	}

	public Integer getMainSize() {
		return mainSize;
	}

	public void setRepeatedColumn(LogModelGroupColumn repeatedColumn) {
		this.repeatedColumn = repeatedColumn;
	}

	public void setRepeatedSize(Integer repeatedSize) {
		this.repeatedSize = repeatedSize;
	}

	public CtypeLogState getLogState(CtypeLogOriginalInfo info) {
		if (!logInfoToState.containsKey(info)) {
			createLogState(info);
		}
		return logInfoToState.get(info);
	}

	protected synchronized void createLogState(CtypeLogOriginalInfo info) {
		if (logInfoToState.containsKey(info)) {
			return;
		}
		boolean isVerticalEnding = info.getIsVerticalEnding();
		int length = info.getLength();
		int repeatTimes = 0;
		boolean isAddEmptyEnd = false;
		boolean isRight = false;
		if (repeatedColumn == null) {
			if (length == mainSize) {
				isRight = true;
			} else if (isVerticalEnding && length + 1 == mainSize) {
				isRight = true;
				isAddEmptyEnd = true;
			}
		} else {
			if ((length - mainSize) % repeatedSize == 0) {
				isRight = true;
				repeatTimes = (length - mainSize) / repeatedSize;
			} else if (isVerticalEnding
					&& (length + 1 - mainSize) % repeatedSize == 0) {
				isRight = true;
				repeatTimes = (length + 1 - mainSize) % repeatedSize;
				isAddEmptyEnd = true;
			}
		}
		CtypeLogState state = new CtypeLogState();
		state.setRepeatTimes(repeatTimes);
		state.setAddEmptyEnd(isAddEmptyEnd);
		state.setMatch(isRight);
		logInfoToState.put(info, state);
	}
}
