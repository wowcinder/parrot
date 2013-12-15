package com.voole.parrot.service.dao;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "TestEntityDao.xml" })
public class TestEntityDao {
	@Autowired
	private EntityDaoTest t;
	@Autowired
	private EntityDaoTest2 t2;

	public static class EntityDaoTest extends EntityDao<String> {

	}

	public static class EntityDaoTest2 extends EntityDao<Double> {

	}

	@Test
	public void test() {
		Assert.assertEquals(String.class, t.getInnerClass());
		Assert.assertNotNull(t.em);

		Assert.assertEquals(Double.class, t2.getInnerClass());
		Assert.assertNotNull(t2.em);

		Assert.assertEquals(t2.em, t2.em);
	}

}
