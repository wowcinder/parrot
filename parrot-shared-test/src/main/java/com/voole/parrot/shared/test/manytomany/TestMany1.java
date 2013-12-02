package com.voole.parrot.shared.test.manytomany;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.voole.parrot.shared.test.BaseEntity;

@Entity
public class TestMany1 extends BaseEntity {
	private Set<TestMany2> many2s;

	@ManyToMany
	@JoinTable(name = "test_many1_many2s", joinColumns = { @JoinColumn(name = "many1") }, inverseJoinColumns = { @JoinColumn(name = "many2") })
	public Set<TestMany2> getMany2s() {
		return many2s;
	}

	public void setMany2s(Set<TestMany2> many2s) {
		this.many2s = many2s;
	}

}
