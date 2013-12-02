/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.parrot.shared.test;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.voole.parrot.shared.test.manytomany.TestMany1;
import com.voole.parrot.shared.test.manytomany.TestMany2;
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

	@org.junit.Test
	public void testManyToMany() {
		Session session = sf.openSession();
		session.beginTransaction();

		Set<TestMany1> many1s = new HashSet<TestMany1>();
		Set<TestMany2> many2s = new HashSet<TestMany2>();

		TestMany1 many1 = new TestMany1();
		many1.setName("many1");
		many1s.add(many1);

		TestMany2 many2 = new TestMany2();
		many2.setName("many2");
		many2.setMany1s(many1s);
		many2s.add(many2);

		
		
		session.persist(many2);
		session.persist(many1);
		
		session.getTransaction().commit();
		session.close();

		session = sf.openSession();
		session.beginTransaction();
		session.delete(many1);
		session.getTransaction().commit();
	}

	public void testDelete() {
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

		session = sf.openSession();
		session.beginTransaction();
		// session.delete(group);
		String sql = "delete from {0} e where e.group = ? ";
		sql = "update {0} e set e.group = null where e.group = ?";
		sql = MessageFormat.format(sql,
				TestOneToManyEntity.class.getSimpleName());
		session.createQuery(sql).setLong(0, group.getId()).executeUpdate();

		session.getTransaction().commit();
		session.close();
	}

}
