package com.voole.parrot.shared.test.manytomany;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.voole.parrot.shared.test.BaseEntity;

@Entity
public class TestMany2 extends BaseEntity {
	private Set<TestMany1> many1s;

	@ManyToMany
	@JoinTable(name = "test_many1_many2s", joinColumns = { @JoinColumn(name = "many2") }, inverseJoinColumns = { @JoinColumn(name = "many1") })
	public Set<TestMany1> getMany1s() {
		return many1s;
	}

	public void setMany1s(Set<TestMany1> many1s) {
		this.many1s = many1s;
	}

}
