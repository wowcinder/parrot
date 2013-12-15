package com.voole.parrot.shared.tree;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.voole.parrot.shared.EntityHasAutoId;

@Entity
public class Node extends EntityHasAutoId {
	private static final long serialVersionUID = 5307409478980590779L;
	private Trunk trunk;
	Integer pos;

	@PrePersist
	@PreUpdate
	private void prepareIndex() {
		System.out
				.println("----------------------------------------------------");
		if (trunk != null) {
			pos = trunk.getNodes().indexOf(this);
		}
	}

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
