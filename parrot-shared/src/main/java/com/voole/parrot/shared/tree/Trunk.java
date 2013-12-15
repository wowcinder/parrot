package com.voole.parrot.shared.tree;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.voole.parrot.shared.EntityHasAutoId;

@Entity
public class Trunk extends EntityHasAutoId {
	private static final long serialVersionUID = -2931590694078314598L;
	private List<Node> nodes;

	@OneToMany(mappedBy = "trunk", cascade = { CascadeType.ALL })
	public List<Node> getNodes() {
		return nodes;
	}

	public void setNodes(List<Node> nodes) {
		this.nodes = nodes;
	}
}
