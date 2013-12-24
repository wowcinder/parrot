package com.voole.parrot.rpc.service.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
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
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.hibernate.collection.PersistentCollection;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.proxy.HibernateProxy;
import org.hibernate.proxy.LazyInitializer;
import org.hibernate.type.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
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
		if (bean == null) {
			return bean;
		}
		HibernateBeanInnerUnwrapper inner = new HibernateBeanInnerUnwrapper();
		return inner.unwrap(bean);
	}

	public class HibernateBeanInnerUnwrapper {
		private final Map<Object, Object> proxyMap;
		private final Map<Object, Object> collectionMap;
		private final HashSet<Object> hasDealedNormalBeans;
		private final HashSet<Object> hasDealedNormalColletions;

		public HibernateBeanInnerUnwrapper() {
			proxyMap = new HashMap<Object, Object>();
			collectionMap = new HashMap<Object, Object>();
			hasDealedNormalBeans = new HashSet<Object>();
			hasDealedNormalColletions = new HashSet<Object>();
		}

		protected Object unwrap(Object bean) {
			if (bean == null) {
				return bean;
			}
			if (bean instanceof PersistentCollection) {
				return unwrapPersistentCollection((Collection<?>) bean);
			} else if (bean instanceof Collection) {
				return unwrapNormalCollection((Collection<?>) bean);
			} else if (bean instanceof Object[]) {
				return unwrapArray((Object[]) bean);
			} else {
				return unwrapBean(bean);
			}
		}

		protected Object unwrapBean(Object bean) {
			if (bean == null) {
				return null;
			}
			if (bean instanceof HibernateProxy) {
				if (proxyMap.containsKey(bean)) {
					return proxyMap.get(bean);
				}
				Object impl = null;
				if (Hibernate.isInitialized(bean)) {
					HibernateProxy proxy = (HibernateProxy) bean;
					LazyInitializer li = proxy.getHibernateLazyInitializer();
					impl = li.getImplementation();
				}
				proxyMap.put(bean, impl);
				return unwrapBean(impl);
			}
			if (hasDealedNormalBeans.contains(bean)) {
				return bean;
			}
			hasDealedNormalBeans.add(bean);
			EntityPropertyInfo entityInfo = getPropertyInfo(bean.getClass());
			// normal beans
			for (String propertyName : entityInfo.getBeanProperties()) {
				Object fieldValue = entityInfo.getPropertyValue(bean,
						propertyName);
				if (fieldValue != null
						&& !hasDealedNormalBeans.contains(fieldValue)) {
					entityInfo.setPropertyValue(bean, propertyName,
							unwrapBean(fieldValue));
				}
			}
			// collection beans
			for (String propertyName : entityInfo.getCollectionProperties()) {
				Object fieldValue = entityInfo.getPropertyValue(bean,
						propertyName);
				if (fieldValue == null) {
					continue;
				}
				if (fieldValue instanceof PersistentCollection) {
					entityInfo
							.setPropertyValue(
									bean,
									propertyName,
									unwrapPersistentCollection((Collection<?>) fieldValue));
				} else if (fieldValue instanceof Object[]) {
					if (((Object[]) fieldValue).length > 0
							&& !hasDealedNormalColletions.contains(fieldValue)) {
						entityInfo.setPropertyValue(bean, propertyName,
								unwrapArray((Object[]) fieldValue));
					}
				} else {
					if (!hasDealedNormalColletions.contains(fieldValue)) {
						entityInfo
								.setPropertyValue(
										bean,
										propertyName,
										unwrapNormalCollection((Collection<?>) fieldValue));
					}
				}
			}
			return bean;
		}

		@SuppressWarnings({ "rawtypes", "unchecked" })
		protected Object unwrapPersistentCollection(Collection<?> beans) {
			if (beans == null || !Hibernate.isInitialized(beans)) {
				return null;
			}
			if (collectionMap.containsKey(beans)) {
				return collectionMap.get(beans);
			}
			if (beans instanceof List) {
				List list = new ArrayList();
				collectionMap.put(beans, list);
				for (Object item : (Collection<?>) beans) {
					list.add(unwrap(item));
				}
				return list;
			} else if (beans instanceof Set) {
				Set set = new HashSet();
				collectionMap.put(beans, set);
				for (Object item : (Collection<?>) beans) {
					set.add(unwrap(item));
				}
				return set;
			} else {
				// TODO
				return null;
			}
		}

		@SuppressWarnings({ "rawtypes", "unchecked" })
		protected Object unwrapNormalCollection(Collection<?> beans) {
			if (beans == null) {
				return null;
			}
			if (hasDealedNormalColletions.contains(beans)) {
				return beans;
			}
			hasDealedNormalColletions.add(beans);
			List list = new ArrayList();
			for (Object bean : beans) {
				list.add(unwrap(bean));
			}
			beans.clear();
			beans.addAll(list);
			return beans;
		}

		protected Object unwrapArray(Object[] beans) {
			if (beans == null) {
				return null;
			}
			if (hasDealedNormalColletions.contains(beans)) {
				return beans;
			}
			hasDealedNormalColletions.add(beans);
			int size = beans.length;
			for (int i = 0; i < size; i++) {
				beans[i] = unwrap(beans[i]);
			}
			return beans;
		}

		@SuppressWarnings({ "rawtypes", "unchecked" })
		public Object unwrapCollection(PersistentCollection collection) {
			if (List.class.isAssignableFrom(collection.getClass())) {
				if (collectionMap.containsKey(collection)) {
					return collectionMap.get(collection);
				}
				List list = new ArrayList();
				collectionMap.put(collection, list);
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
	}

	public static class EntityPropertyInfo {
		private final ClassMetadata classMetadata;
		private final Set<String> beanProperties;
		private final Set<String> collectionProperties;
		@SuppressWarnings("unused")
		private final Class<?> clazz;

		public EntityPropertyInfo(ClassMetadata classMetadata, Class<?> clazz) {
			this.clazz = clazz;
			this.classMetadata = classMetadata;
			beanProperties = new HashSet<String>();
			collectionProperties = new HashSet<String>();
			analyze();
		}

		public void setPropertyValue(Object bean, String propertyName,
				Object unwrapBean) {
			classMetadata.setPropertyValue(bean, propertyName, unwrapBean,
					EntityMode.POJO);
		}

		public Object getPropertyValue(Object object, String propertyName) {
			return classMetadata.getPropertyValue(object, propertyName,
					EntityMode.POJO);
		}

		private void analyze() {
			for (String propertyName : classMetadata.getPropertyNames()) {
				Type type = classMetadata.getPropertyType(propertyName);
				if (type.isCollectionType()) {
					collectionProperties.add(propertyName);
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

		public Set<String> getCollectionProperties() {
			return collectionProperties;
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
