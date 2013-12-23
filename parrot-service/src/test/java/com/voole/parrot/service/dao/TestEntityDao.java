package com.voole.parrot.service.dao;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.hibernate.SessionFactory;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.type.BagType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.voole.parrot.shared.entity.menu.MenuGroup;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/TestEntityDao.xml" })
public class TestEntityDao {
	@Autowired
	private EntityDaoTest t;
	@Autowired
	private EntityDaoTest2 t2;
	@Autowired
	private ISimpleDao simpleDao;
	@Autowired
	private SessionFactory sessionFactory;

	@Repository
	public static class EntityDaoTest extends EntityDao<String> {

	}

	@Repository
	public static class EntityDaoTest2 extends EntityDao<Double> {

	}

	@Test
	// @Transactional
	public void test() throws IllegalArgumentException, IllegalAccessException,
			InvocationTargetException {

		ClassMetadata classMetadata = sessionFactory
				.getClassMetadata(MenuGroup.class);
		
		System.out.println(sessionFactory.getClassMetadata("com.voole.parrot.shared.entity.menu.MenuGroup.nodes"));
		org.hibernate.type.Type type = classMetadata.getPropertyType("nodes");
		BagType type2 = (BagType)type;
		type2.isAssociationType();
		type2.isCollectionType();
		for (Method method : type.getClass().getMethods()) {
			try {
				if (method.getParameterTypes().length == 0) {
					String msg = method.getName() + ":";
					Object result = method.invoke(type);
					if (result == null) {
						System.out.println(msg + "null");
					} else {
						System.out.println(msg + result.toString());
					}

				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		}
		System.out.println(type.getReturnedClass());
		System.out.println(classMetadata);

		// Assert.assertEquals(String.class, t.getRawType());
		// Assert.assertNotNull(t.getCurrSession());
		//
		// Assert.assertEquals(Double.class, t2.getRawType());
		// Assert.assertNotNull(t2.getCurrSession());
		//
		// Assert.assertEquals(t2.getCurrSession(), t.getCurrSession());
		// Assert.assertEquals(t2.getCurrSession(), simpleDao.getCurrSession());
	}

}
