package com.voole.parrot.shared.test.onetomany;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring-test.xml" })
public class Test2 {
	@Resource(name = "parrotSf")
	private SessionFactory sf;

	@Test
	public void test() {
		Session session = sf.openSession();
		session.beginTransaction();

		List<TestOneToManyEntity> list = new ArrayList<TestOneToManyEntity>();

		TestOneToManyGroup group = new TestOneToManyGroup();
		group.setName("test");
		group.setEntities(list);

		TestOneToManyEntity many = new TestOneToManyEntity();
		many.setGroup(group);
		list.add(many);

		session.save(group);
		session.getTransaction().commit();
		session.close();

		session = sf.openSession();
		session.beginTransaction();
//		group = (TestOneToManyGroup) session.get(TestOneToManyGroup.class,
//				group.getId());
		group.setEntities(null);
		session.refresh(group);
		session.delete(group);
		session.getTransaction().commit();
		session.close();
	}

}
