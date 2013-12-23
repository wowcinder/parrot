package com.voole.parrot.gwt.common.util.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import com.google.gwt.editor.client.Editor.Path;
import com.voole.parrot.gwt.common.util.reflect.EntityColumns.FieldType;

public class EntityClassUtil {
	public static boolean isDeclareEntityFieldClass(Class<?> clazz) {
		if (clazz.isAnnotationPresent(Entity.class)
				|| clazz.isAnnotationPresent(MappedSuperclass.class)) {
			return true;
		}
		return false;
	}

	public static boolean isEntityClass(Class<?> clazz) {
		if (clazz.isAnnotationPresent(Entity.class)) {
			return true;
		}
		return false;
	}

	public static boolean isFinalOrStaticOrTransient(Field field) {
		int modifier = field.getModifiers();
		return Modifier.isFinal(modifier) || Modifier.isTransient(modifier)
				|| Modifier.isStatic(modifier)
				|| field.isAnnotationPresent(Transient.class);
	}

	public static class EntityColumnsFinder {
		private final Class<?> clazz;
		private final List<Field> allFields;
		private final List<Field> fields;
		private final Map<String, Field> pathFields;
		private Field id;

		public EntityColumnsFinder(Class<?> clazz) {
			this.clazz = clazz;
			this.allFields = new ArrayList<Field>();
			pathFields = new HashMap<String, Field>();
			fields = new ArrayList<Field>();
			analyze();
		}

		private void analyze() {
			fillAllFields();
			analyzeFields();
		}

		private void fillAllFields() {
			Class<?> c = clazz;
			while (c != Object.class
					&& EntityClassUtil.isDeclareEntityFieldClass(c)) {
				for (Field field : c.getDeclaredFields()) {
					if (!EntityClassUtil.isFinalOrStaticOrTransient(field)) {
						allFields.add(field);
					}
				}
				c = c.getSuperclass();
			}
		}

		private void analyzeFields() {
			for (Field field : allFields) {
				FieldType fieldType = getFieldType(field);
				String path = getPath(field);
				if (fieldType == FieldType.ID) {
					id = field;
					fields.add(field);
					if (path != null) {
						pathFields.put(path, field);
					}
				} else if (fieldType == FieldType.NORMAL) {
					fields.add(field);
					if (path != null) {
						pathFields.put(path, field);
					}
				}
			}
		}

		private String getPath(Field field) {
			if (field.isAnnotationPresent(Path.class)) {
				Path path = field.getAnnotation(Path.class);
				return path.value();
			}
			Method method = MethodFinder.getGetMethod(clazz, field.getName());
			if (method.isAnnotationPresent(Path.class)) {
				Path path = method.getAnnotation(Path.class);
				return path.value();
			}
			return null;
		}

		private FieldType getFieldType(Field field) {
			if (field.isAnnotationPresent(Id.class)) {
				return FieldType.ID;
			}
			Method method = MethodFinder.getGetMethod(clazz, field.getName());
			if (method == null) {
				return FieldType.NORMAL;
			}
			if (method.isAnnotationPresent(Id.class)) {
				return FieldType.ID;
			} else if (method.isAnnotationPresent(Transient.class)) {
				return FieldType.TRANSIENT;
			}
			return FieldType.NORMAL;
		}

		public List<Field> getFields() {
			return fields;
		}

		public Map<String, Field> getPathFields() {
			return pathFields;
		}

		public Field getId() {
			return id;
		}

		public void setId(Field id) {
			this.id = id;
		}

		public Class<?> getClazz() {
			return clazz;
		}

	}
}
