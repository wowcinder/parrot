package com.voole.parrot.shared.authority;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.voole.parrot.shared.EntityHasAutoId;

@Entity
public class AuthorityEntrance extends EntityHasAutoId {

	private static final long serialVersionUID = 2924510788634515506L;

	private String name;
	private Set<Authority> authorities;

	@NotNull
	@Column(nullable = false, length = 100)
	@Length(min = 1, max = 100)
	public String getName() {
		return name;
	}

	@OneToMany(mappedBy = "entrance", cascade = { CascadeType.REMOVE })
	public Set<Authority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Set<Authority> authorities) {
		this.authorities = authorities;
	}

	public void setName(String name) {
		this.name = name;
	}

}
