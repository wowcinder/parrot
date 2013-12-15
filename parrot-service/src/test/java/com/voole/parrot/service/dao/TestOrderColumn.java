package com.voole.parrot.service.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.voole.parrot.shared.tree.Node;
import com.voole.parrot.shared.tree.Trunk;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "TestEntityDao.xml" })
public class TestOrderColumn {
	@Resource(name = "parrotSf")
	private SessionFactory sf;

	@Test
	public void test() {
		Trunk trunk = new Trunk();

		List<Node> nodes = new ArrayList<Node>();
		nodes.add(createNode(trunk));
		nodes.add(createNode(trunk));
		nodes.add(createNode(trunk));
		nodes.add(createNode(trunk));

		trunk.setNodes(nodes);

		Session session = sf.openSession();
		session.beginTransaction();
		session.save(trunk);
		session.getTransaction().commit();
		session.close();
	}

	private Node createNode(Trunk trunk) {
		Node node = new Node();
		node.setTrunk(trunk);
		return node;
	}
}
