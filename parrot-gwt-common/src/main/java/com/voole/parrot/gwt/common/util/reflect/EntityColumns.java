package com.voole.parrot.gwt.common.util.reflect;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import com.voole.parrot.gwt.common.util.reflect.EntityClassUtil.EntityColumnsFinder;

public class EntityColumns {
	private Field id;
	private final Map<String, Field> columsMap;
	private final Map<String, Field> pathFields;
	private final Class<?> clazz;

	public EntityColumns(Class<?> clazz) {
		this.clazz = clazz;
		columsMap = new HashMap<String, Field>();
		pathFields = new HashMap<String, Field>();
	}

	public Field getId() {
		return id;
	}

	public Map<String, Field> getColumsMap() {
		return columsMap;
	}

	public Map<String, Field> getPathFields() {
		return pathFields;
	}

	public void setId(Field id) {
		this.id = id;
	}

	public Class<?> getClazz() {
		return clazz;
	}

	public static <T> EntityColumns create(Class<T> clazz) {
		EntityColumnsFinder finder = new EntityColumnsFinder(clazz);
		EntityColumns columns = new EntityColumns(finder.getClazz());
		for (Field field : finder.getFields()) {
			columns.getColumsMap().put(field.getName(), field);
		}
		columns.setId(finder.getId());
		columns.getPathFields().putAll(finder.getPathFields());
		return columns;
	}

	public static enum FieldType {
		NORMAL, ID, TRANSIENT;
	}

}
