package com.voole.parrot.gwt.common.shared.property;

import java.util.List;
import java.util.Set;

import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.voole.parrot.shared.entity.account.Account;
import com.voole.parrot.shared.entity.authority.Authority;
import com.voole.parrot.shared.entity.authority.AuthorityEntrance;
import com.voole.parrot.shared.entity.organization.TopOrganizationAuthority;

public interface AuthorityProperty extends PropertyAccess<Authority> {

	@com.google.gwt.editor.client.Editor.Path("token")
	public ModelKeyProvider<Authority> key();

	@Path("entrance.name")
	public ValueProvider<Authority, String> entranceName();

	public ValueProvider<Authority, String> token();

	public ValueProvider<Authority, AuthorityEntrance> entrance();

	public ValueProvider<Authority, List<TopOrganizationAuthority>> organizationAuthorities();

	public ValueProvider<Authority, Set<Authority>> dependencies();

	public ValueProvider<Authority, Set<Authority>> reDependencies();

	public ValueProvider<Authority, Set<Account>> accounts();

	public ValueProvider<Authority, String> name();

	public ValueProvider<Authority, Integer> pos();

}
