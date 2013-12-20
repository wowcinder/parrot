package com.voole.parrot.gwt.common.util.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map.Entry;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.google.common.reflect.TypeToken;
import com.google.gwt.editor.client.Editor.Path;

public class TestEntityColumns {
	@MappedSuperclass
	public static class Entity1<T> {
		private T t;

		public T getT() {
			return t;
		}

		public void setT(T t) {
			this.t = t;
		}

	}

	@Entity
	public static class Entity2<M> extends Entity1<String> {
		private M m;

		public M getM() {
			return m;
		}

		public void setM(M m) {
			this.m = m;
		}

	}

	@Entity
	public static class Entity3 extends Entity2<Long> {
		@Id
		private Integer id;

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

	}

	@Entity
	public static class Entity4<N> {
		@Id
		private Integer id;
		@Path("e.m")
		private Entity2<N> e;

		public Integer getId() {
			return id;
		}

		public Entity2<N> getE() {
			return e;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public void setE(Entity2<N> e) {
			this.e = e;
		}

	}

	@Entity
	public static class Entity5 extends Entity4<Number> {

	}

	public static <T> void show(Class<T> clazz) {
		EntityColumns config = EntityColumns.create(clazz);
		TypeToken<T> typeToken = TypeToken.of(clazz);
		if (config.getId() != null) {
			System.out.println("key:" + config.getId().getName());
		}
		for (Entry<String, Field> entry : config.getColumsMap().entrySet()) {
			System.out.println("name:" + entry.getKey());
			System.out.println("type:"
					+ typeToken.resolveType(entry.getValue().getGenericType())
							.getRawType());
		}
		for (Entry<String, Field> entry : config.getPathFields().entrySet()) {
			Field field = entry.getValue();
			String path = entry.getKey();
			System.out.println("path:" + path + ",name:" + field.getName());
			String[] paths = path.split("\\.");
			TypeToken<?> itemToken = typeToken;
			Class<?> itemClazz = clazz;
			for (String itemPath : paths) {
				Method getMethod = MethodFinder.getGetMethod(itemClazz,
						itemPath);
				itemToken = itemToken.method(getMethod).getReturnType();
				itemClazz = itemToken.getRawType();
			}
			System.out.println("type:" + itemClazz);
		}
		System.out.println("------------" + clazz.getSimpleName()
				+ "-----------------");
	}

	public static void main(String[] args) {
		show(Entity1.class);
		show(Entity2.class);
		show(Entity3.class);
		show(Entity4.class);
		show(Entity5.class);
	}

}
