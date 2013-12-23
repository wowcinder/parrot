/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.parrot.service.util;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sun.codemodel.ClassType;
import com.sun.codemodel.JClass;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JFieldVar;
import com.sun.codemodel.JMod;
import com.voole.parrot.service.dao.EntityDao;
import com.voole.parrot.service.dao.IEntityDao;
import com.voole.parrot.service.service.EntityService;
import com.voole.parrot.service.service.EntityServiceImpl;
import com.voole.parrot.util.ClassScanner;

/**
 * @author XuehuiHe
 * @date 2013年12月23日
 */
public class DaoServiceJavaSourceCreator extends BaseJavaSourceGenerator {
	private static final String[] ENTITY_PATHS = new String[] { "com.voole.parrot.shared.entity" };
	private static final String DAO_BASE_PATH = "com.voole.parrot.service.dao.";
	private static final String SERVICE_BASE_PATH = "com.voole.parrot.service.service.";

	public static void main(String[] args) throws Exception {
		DaoServiceJavaSourceCreator t = new DaoServiceJavaSourceCreator();
		t.work();
		t.build();
	}

	public void work() throws Exception {
		List<Class<?>> classes = getEntityClasses();
		for (Class<?> clazz : classes) {
			ClassFullPath path = new ClassFullPath(clazz);
			create(path);
		}
	}

	public void create(ClassFullPath path) throws Exception {
		JDefinedClass daoInterfaceDc = getjCodeModel()._class(
				path.daoInterfacePath, ClassType.INTERFACE);
		JDefinedClass daoImplDc = getjCodeModel()._class(path.daoImplPath);
		JDefinedClass serviceImplDc = getjCodeModel()._class(
				path.serviceImplPath);
		JDefinedClass serviceInterfaceDc = getjCodeModel()._class(
				path.serviceInterfacePath, ClassType.INTERFACE);
		JClass clazz = getjCodeModel().ref(path.clazz);
		if (!isExists(path.daoInterfacePath)) {
			createDao(clazz, daoInterfaceDc, daoImplDc);
		} else {
			daoInterfaceDc.hide();
			daoImplDc.hide();
		}
		if (!isExists(path.serviceInterfacePath)) {
			createService(daoInterfaceDc, daoImplDc, serviceImplDc,
					serviceInterfaceDc, clazz);
		} else {
			serviceInterfaceDc.hide();
			serviceImplDc.hide();
		}
	}

	@Transactional
	private void createService(JDefinedClass daoInterfaceDc,
			JDefinedClass daoImplDc, JDefinedClass serviceImplDc,
			JDefinedClass serviceInterfaceDc, JClass clazz) {
		serviceInterfaceDc._extends(getjCodeModel().ref(EntityService.class)
				.narrow(clazz));
		serviceImplDc
				._extends(
						getjCodeModel().ref(EntityServiceImpl.class).narrow(
								clazz))._implements(serviceInterfaceDc)
				.annotate(Service.class);
		serviceImplDc.annotate(Transactional.class);

		JFieldVar daoVar = serviceImplDc.field(JMod.PRIVATE, daoInterfaceDc,
				daoImplDc.name());
		daoVar.annotate(Autowired.class);

		serviceImplDc.method(JMod.PUBLIC, daoInterfaceDc, "getEntityDao")
				.body()._return(daoVar);

	}

	public void createDao(JClass clazz, JDefinedClass daoInterfaceDc,
			JDefinedClass daoImplDc) {
		daoInterfaceDc._extends(getjCodeModel().ref(IEntityDao.class).narrow(
				clazz));

		daoImplDc._extends(getjCodeModel().ref(EntityDao.class).narrow(clazz))
				._implements(daoInterfaceDc).annotate(Repository.class);
	}

	public boolean isExists(String fullName) {
		try {
			Class.forName(fullName);
		} catch (ClassNotFoundException e) {
			return false;
		}
		return true;
	}

	public List<Class<?>> getEntityClasses() throws Exception {
		List<Class<?>> list = new ArrayList<Class<?>>();
		ClassScanner scaner = new ClassScanner(ENTITY_PATHS);
		for (Class<?> clazz : scaner.getClazzes()) {
			if (clazz.isAnnotationPresent(Entity.class)
					&& !Modifier.isAbstract(clazz.getModifiers())) {
				list.add(clazz);
			}
		}
		return list;
	}

	public class ClassFullPath {
		public String daoInterfacePath;
		public String daoImplPath;
		public String serviceInterfacePath;
		public String serviceImplPath;
		public final Class<?> clazz;

		public ClassFullPath(Class<?> clazz) {
			this.clazz = clazz;
			String name = clazz.getSimpleName();
			String packagee = clazz.getPackage().getName();
			String daoPackage = getDaoPackage(packagee);
			String servicePackage = getServicePackage(packagee);

			daoInterfacePath = daoPackage + "."
					+ getDaoInterfaceSimpleName(name);
			daoImplPath = daoPackage + "." + getDaoImplSimpleName(name);
			serviceInterfacePath = servicePackage + "."
					+ getServiceInterfaceSimpleName(name);
			serviceImplPath = servicePackage + "."
					+ getServiceImplSimpleName(name);

		}

		String getDaoPackage(String packagee) {
			return DAO_BASE_PATH + getSubPackage(packagee);
		}

		String getServicePackage(String packagee) {
			return SERVICE_BASE_PATH + getSubPackage(packagee);
		}

		String getSubPackage(String packagee) {
			int index = packagee.indexOf("entity.");
			return packagee.substring(index + "entity.".length());
		}

		public String getDaoInterfaceSimpleName(String name) {
			return "I" + name + "Dao";
		}

		public String getDaoImplSimpleName(String name) {
			return name + "Dao";
		}

		public String getServiceInterfaceSimpleName(String name) {
			return name + "Service";
		}

		public String getServiceImplSimpleName(String name) {
			return name + "ServiceImpl";
		}
	}

}
