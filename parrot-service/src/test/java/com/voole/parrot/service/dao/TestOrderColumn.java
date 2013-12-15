package com.voole.parrot.service.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.voole.parrot.shared.tree.Node;
import com.voole.parrot.shared.tree.Trunk;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "TestEntityDao.xml" })
public class TestOrderColumn {
	@PersistenceContext(unitName = "parrotUnit")
	private EntityManager sf;

	@Test
	@Transactional
	@Rollback(false)
	public void test() {
		Trunk trunk = new Trunk();

		List<Node> nodes = new ArrayList<Node>();
		nodes.add(createNode(trunk));
		nodes.add(createNode(trunk));
		nodes.add(createNode(trunk));
		nodes.add(createNode(trunk));

		trunk.setNodes(nodes);

		// sf.getTransaction().begin();
		System.out.println(nodes);
		sf.persist(trunk);
		sf.flush();
		// trunk.setName("sdfs");
		trunk.getNodes().add(0, createNode(trunk));
		System.out.println(trunk.getNodes());
		sf.persist(trunk);
		// trunk.getNodes().add(2, createNode(trunk));
		// System.out.println(trunk.getNodes());
		// sf.merge(trunk);
		// System.out.println(trunk.getNodes());
		// sf.flush();

		// Session session = sf.openSession();
		// session.beginTransaction();
		// session.save(trunk);
		// session.getTransaction().commit();
		// session.close();
	}

	// @Test
	// @Transactional
	// @Rollback(false)
	public void test2() {
		Trunk trunk = sf.find(Trunk.class, 1L);
		System.out.println(trunk.getNodes().getClass());
		Node node = createNode(trunk);
		List<Node> nodes = trunk.getNodes();

		trunk.setNodes(null);
		for (Node node2 : nodes) {
			node2.setTrunk(null);
		}
		sf.flush();
		nodes.add(2, node);
		for (Node node2 : nodes) {
			node2.setTrunk(trunk);
		}
		trunk.setNodes(nodes);
		sf.merge(trunk);

		// sf.detach(nodes);
		// trunk.setNodes(nodes);
		// sf.persist(node);
		// trunk = sf.merge(trunk);
		// for (Node node : trunk.getNodes()) {
		// sf.merge(node);
		// }
	}

	private Node createNode(Trunk trunk) {
		Node node = new Node();
		node.setTrunk(trunk);
		return node;
	}
}
