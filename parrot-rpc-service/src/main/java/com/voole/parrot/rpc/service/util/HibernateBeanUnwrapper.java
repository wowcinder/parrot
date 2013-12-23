package com.voole.parrot.rpc.service.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;

import org.hibernate.EntityMode;
import org.hibernate.SessionFactory;
import org.hibernate.collection.PersistentCollection;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.type.Type;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.reflect.TypeToken;
import com.voole.parrot.shared.entity.menu.Menu;

public class HibernateBeanUnwrapper {
	@Autowired
	private SessionFactory sessionFactory;

	private final Map<Class<?>, EntityPropertyInfo> propertyInfoMap;

	public HibernateBeanUnwrapper() {
		propertyInfoMap = new ConcurrentHashMap<Class<?>, EntityPropertyInfo>();
	}

	public EntityPropertyInfo getPropertyInfo(Class<?> clazz) {
		if (!propertyInfoMap.containsKey(clazz)) {
			createPropertyInfo(clazz);
		}
		return propertyInfoMap.get(clazz);
	}

	public synchronized void createPropertyInfo(Class<?> clazz) {
		if (propertyInfoMap.containsKey(clazz)) {
			return;
		}
		propertyInfoMap.put(clazz,
				new EntityPropertyInfo(sessionFactory.getClassMetadata(clazz),
						clazz));
	}

	public Object unwrap(Object bean) {
		EntityPropertyInfo entityPropertyInfo = getPropertyInfo(bean.getClass());
		for (String propertyName : entityPropertyInfo.getBeanProperties()) {
			Object value = entityPropertyInfo.getPropertyValue(bean,
					propertyName);
			if(){
				
			}
		}
		return null;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Object unwrapCollection(PersistentCollection collection) {
		if (List.class.isAssignableFrom(collection.getClass())) {
			List list = new ArrayList();
			for (Object item : (Collection<?>) collection) {
				list.add(unwrap(item));
			}
			return list;
		} else if (Set.class.isAssignableFrom(collection.getClass())) {
			Set set = new HashSet();
			for (Object item : (Collection<?>) collection) {
				set.add(unwrap(item));
			}
			return set;
		} else {
			// TODO
		}
		return null;
	}

	public static class EntityPropertyInfo {
		private final ClassMetadata classMetadata;
		private final Set<String> beanProperties;
		private final Map<String, Class<?>> collectionPropertiesMap;
		private final Class<?> clazz;

		public EntityPropertyInfo(ClassMetadata classMetadata, Class<?> clazz) {
			this.clazz = clazz;
			this.classMetadata = classMetadata;
			beanProperties = new HashSet<String>();
			collectionPropertiesMap = new HashMap<String, Class<?>>();
			analyze();
		}

		public Object getPropertyValue(Object object,String propertyName) {
			return classMetadata.getPropertyValue(object, propertyName, EntityMode.POJO);
		}

		@SuppressWarnings("rawtypes")
		private void analyze() {
			@SuppressWarnings("unchecked")
			TypeToken<?> typeToken = new TypeToken(clazz) {
			};
			Map<String, Field> map = getFields(clazz);
			for (String propertyName : classMetadata.getPropertyNames()) {
				Type type = classMetadata.getPropertyType(propertyName);
				if (type.isCollectionType()) {
					Field field = map.get(propertyName);
					java.lang.reflect.Type genericType = field.getGenericType();
					java.lang.reflect.Type[] actualTypeArguments = ((ParameterizedType) genericType)
							.getActualTypeArguments();
					java.lang.reflect.Type assosiatedType = actualTypeArguments[0];
					assosiatedType = typeToken.resolveType(assosiatedType)
							.getRawType();
					collectionPropertiesMap.put(propertyName,
							(Class<?>) assosiatedType);
				} else if (type.isAssociationType()) {
					beanProperties.add(propertyName);
				}
			}
		}

		public ClassMetadata getClassMetadata() {
			return classMetadata;
		}

		public Set<String> getBeanProperties() {
			return beanProperties;
		}

		public Map<String, Class<?>> getCollectionPropertiesMap() {
			return collectionPropertiesMap;
		}

		protected static Map<String, Field> getFields(Class<?> clazz) {
			Map<String, Field> fields = new HashMap<String, Field>();
			if (clazz.isAnnotationPresent(Entity.class)
					|| clazz.isAnnotationPresent(MappedSuperclass.class)) {
				for (Field field : clazz.getDeclaredFields()) {
					int mod = field.getModifiers();
					if (!Modifier.isStatic(mod) && !Modifier.isTransient(mod)) {
						fields.put(field.getName(), field);
					}
				}
			}
			Class<?> superClazz = clazz.getSuperclass();
			if (!superClazz.equals(Object.class)) {
				fields.putAll(getFields(clazz.getSuperclass()));
			}
			return fields;
		}

	}
}
