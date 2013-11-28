/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.parrot.shared.test;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.voole.parrot.shared.test.onetomany.TestOneToManyEntity;
import com.voole.parrot.shared.test.onetomany.TestOneToManyGroup;

/**
 * @author XuehuiHe
 * @date 2013年11月27日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:/spring-test.xml" })
public class Test {
	@Resource(name = "parrotSf")
	private SessionFactory sf;

	@org.junit.Test
	public void test() {
		TestOneToManyGroup group = new TestOneToManyGroup();
		group.setName("group");
		List<TestOneToManyEntity> list = new ArrayList<TestOneToManyEntity>();
		for (int i = 0; i < 2; i++) {
			TestOneToManyEntity entity = new TestOneToManyEntity();
			entity.setGroup(group);
			entity.setName("entity_" + i);
			list.add(entity);
		}
		group.setEntities(list);

		Session session = sf.openSession();
		session.beginTransaction();
		session.saveOrUpdate(group);

		session.getTransaction().commit();
		session.close();

		Long id = group.getId();
		group = new TestOneToManyGroup();
		group.setId(id);
		group.setName("group");

		list.clear();
		for (int i = 0; i < 5; i++) {
			TestOneToManyEntity entity = new TestOneToManyEntity();
			entity.setGroup(group);
			entity.setName("entity_" + i);
			list.add(entity);
		}
		// group.setEntities(list);

		session = sf.openSession();
		session.beginTransaction();

		for (TestOneToManyEntity testOneToManyEntity : (List<TestOneToManyEntity>) session
				.createCriteria(TestOneToManyEntity.class)
				.add(Restrictions.eq("group", group)).list()) {
			testOneToManyEntity.setGroup(null);
			session.update(testOneToManyEntity);
		}
		group.setEntities(list);
		session.merge(group);
		session.getTransaction().commit();

		session.close();

	}

}
