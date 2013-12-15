package com.voole.parrot.shared.tree;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.voole.parrot.shared.EntityHasAutoId;
import com.voole.parrot.shared.EntityWithOrderChildren;

@Entity
public class Trunk extends EntityHasAutoId implements EntityWithOrderChildren {
	private static final long serialVersionUID = -2931590694078314598L;
	private List<Node> nodes;
	private String name;

	@OneToMany(mappedBy = "trunk", cascade = { CascadeType.ALL })
	public List<Node> getNodes() {
		return nodes;
	}

	public void setNodes(List<Node> nodes) {
		this.nodes = nodes;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void sortChildren() {
		if (this.nodes == null || this.nodes.size() == 0) {
			return;
		}
		System.out
				.println("----------------------------------------------------");
		for (Node node : this.nodes) {
			node.setPos(this.nodes.indexOf(node));
		}
	}
}
