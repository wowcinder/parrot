package com.voole.parrot.shared.tree;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.voole.parrot.shared.EntityHasAutoId;

@Entity
public class Node extends EntityHasAutoId {
	private static final long serialVersionUID = 5307409478980590779L;
	private Trunk trunk;
	Integer pos;

	@ManyToOne
	public Trunk getTrunk() {
		return trunk;
	}

	public void setTrunk(Trunk trunk) {
		this.trunk = trunk;
	}

	public Integer getPos() {
		return pos;
	}

	public void setPos(Integer pos) {
		this.pos = pos;
	}

}
