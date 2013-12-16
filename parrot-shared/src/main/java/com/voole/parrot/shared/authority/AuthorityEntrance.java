package com.voole.parrot.shared.authority;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.voole.parrot.shared.EntityHasAutoId;
import com.voole.parrot.shared.EntityWithOrderChildren;

@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
public class AuthorityEntrance extends EntityHasAutoId implements
		EntityWithOrderChildren {

	private static final long serialVersionUID = 2924510788634515506L;

	private String name;
	private List<Authority> authorities;

	@NotNull
	@Column(nullable = false, length = 100)
	@Length(min = 1, max = 100)
	public String getName() {
		return name;
	}

	@OneToMany(mappedBy = "entrance", cascade = { CascadeType.ALL })
	public List<Authority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<Authority> authorities) {
		this.authorities = authorities;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void sortChildren() {
		if (authorities == null || authorities.size() == 0) {
			return;
		}
		for (Authority authority : authorities) {
			authority.setPos(authorities.indexOf(authority));
		}
	}

}
