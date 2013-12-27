/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import xdata.etl.hbase.annotatins.HbaseColumn;
import xdata.etl.hbase.annotatins.HbaseEmbedded;
import xdata.etl.hbase.annotatins.HbaseTable;
import xdata.etl.hbase.column.HbaseColumnInfoManager;
import xdata.etl.hbase.entity.HbaseEntity;

import com.voole.parrot.service.service.hbasemeta.HbaseTableService;
import com.voole.parrot.service.service.hbasemeta.HbaseTableVersionService;
import com.voole.parrot.shared.entity.hbasemeta.ColumnType;
import com.voole.parrot.shared.entity.hbasemeta.HbaseTableColumn;
import com.voole.parrot.shared.entity.hbasemeta.HbaseTableVersion;
import com.voole.parrot.util.ClassScanner;

/**
 * @author XuehuiHe
 * @date 2013年8月30日
 */
public class AnalyzeOldHbaseMeta {
	private static ClassPathXmlApplicationContext ac;
	private static HbaseTableService tableService;
	private static HbaseTableVersionService versionService;

	public static void main(String[] args) throws Exception {
		ac = new ClassPathXmlApplicationContext("spring-test.xml");
		init();

		ClassScanner scaner = new ClassScanner("xdata.etl.entity");
		for (Class<?> clazz : scaner.getClazzes()) {
			if (clazz.isAnnotationPresent(HbaseTable.class)) {
				deal(clazz);
			}
		}
		ac.close();

	}

	private static void deal(Class<?> clazz) {
		String name = getTableName(clazz);
		com.voole.parrot.shared.entity.hbasemeta.HbaseTable table = new com.voole.parrot.shared.entity.hbasemeta.HbaseTable();
		table.setDesc(name);
		table.setName(name);
		table.setShortname(name);

		tableService.persist(table);

		HbaseTableVersion v = new HbaseTableVersion();
		v.setTable(table);
		v.setDesc("0.0");
		v.setVersion("0.0");
		tableService.createHbaseTableVersion(v);

		saveColumns(v, clazz);
	}

	private static void saveColumns(HbaseTableVersion v, Class<?> clazz) {
		for (Field field : getField(clazz)) {
			String name = getColumnName(field);
			HbaseTableColumn column = new HbaseTableColumn();
			if (name.equals("mainPart")) {
				continue;
			}
			column.setName(name);
			column.setShortname(name);
			column.setDesc(name);
			column.setVersion(v);
			column.setType(getType(field));

			versionService.createHbaseTableColumn(column);

		}
		HbaseTableColumn column = new HbaseTableColumn();
		String name = "version";
		column.setName(name);
		column.setShortname(name);
		column.setDesc(name);
		column.setVersion(v);
		column.setType(ColumnType.STRING);
		versionService.createHbaseTableColumn(column);
	}

	private static List<Field> getField(Class<?> clazz) {
		List<Field> list = new ArrayList<Field>();
		Class<?> superClass = clazz.getSuperclass();
		if (superClass != null && !superClass.equals(Object.class)) {
			list.addAll(getField(superClass));
		}
		for (Field field : clazz.getDeclaredFields()) {
			if (HbaseColumnInfoManager.isExcludeMod(field.getModifiers())) {
				continue;
			}
			if (HbaseColumnInfoManager.isExcludeField(field)) {
				continue;
			}
			if (HbaseEntity.class.isAssignableFrom(field.getType())) {
			} else if (Collection.class.isAssignableFrom(field.getType())) {
			} else {// 基本属性
				if (field.isAnnotationPresent(HbaseEmbedded.class)) {
					list.addAll(getField(field.getType()));
				} else {
					list.add(field);
				}
			}
		}
		return list;
	}

	private static ColumnType getType(Field field) {
		Class<?> type = field.getType();
		if (type.equals(Short.class) || type.equals(short.class)) {
			return ColumnType.SHORT;
		} else if (type.equals(Integer.class) || type.equals(int.class)) {
			return ColumnType.INT;
		} else if (type.equals(Long.class) || type.equals(long.class)) {
			return ColumnType.LONG;
		} else if (type.equals(Float.class) || type.equals(float.class)) {
			return ColumnType.FLOAT;
		} else if (type.equals(Double.class) || type.equals(double.class)) {
			return ColumnType.DOUBLE;
		} else if (type.equals(String.class)) {
			return ColumnType.STRING;
		} else if (type.equals(Character.class) || type.equals(char.class)) {
			return ColumnType.CHAR;
		} else if (type.equals(Boolean.class)) {
			return ColumnType.BOOLEAN;
		} else if (type.equals(Date.class)) {
			return ColumnType.DATETIME;
		}
		return ColumnType.STRING;
	}

	private static String getColumnName(Field field) {
		String name = field.getName();
		if (field.isAnnotationPresent(HbaseColumn.class)) {
			HbaseColumn column = field.getAnnotation(HbaseColumn.class);
			if (column.name() != null && column.name().length() != 0) {
				name = column.name();
			}
		}
		return name;
	}

	private static String getTableName(Class<?> clazz) {
		String name = clazz.getSimpleName();
		HbaseTable t = clazz.getAnnotation(HbaseTable.class);
		if (t != null && t.name() != null && t.name().length() != 0) {
			name = t.name();
		}
		return name;
	}

	private static void init() {
		tableService = ac.getBean(HbaseTableService.class);
		versionService = ac.getBean(HbaseTableVersionService.class);
	}

}
