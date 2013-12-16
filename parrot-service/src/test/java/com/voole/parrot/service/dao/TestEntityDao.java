package com.voole.parrot.service.dao;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/TestEntityDao.xml" })
public class TestEntityDao {
	@Autowired
	private EntityDaoTest t;
	@Autowired
	private EntityDaoTest2 t2;

	@Repository
	public static class EntityDaoTest extends EntityDao<String> {

	}

	@Repository
	public static class EntityDaoTest2 extends EntityDao<Double> {

	}

	@Test
	@Transactional
	public void test() {
		Assert.assertEquals(String.class, t.getInnerClass());
		Assert.assertNotNull(t.getCurrSession());

		Assert.assertEquals(Double.class, t2.getInnerClass());
		Assert.assertNotNull(t2.getCurrSession());

		Assert.assertEquals(t2.getCurrSession(), t2.getCurrSession());
	}

}
