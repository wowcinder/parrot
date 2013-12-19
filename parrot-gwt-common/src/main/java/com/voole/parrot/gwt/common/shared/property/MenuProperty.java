package com.voole.parrot.gwt.common.shared.property;

import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.voole.parrot.shared.entity.authority.Authority;
import com.voole.parrot.shared.entity.menu.Menu;
import com.voole.parrot.shared.entity.menu.MenuGroup;

public interface MenuProperty extends PropertyAccess<Menu> {

	public ValueProvider<Menu, String> token();

	@Path("parent.name")
	public ValueProvider<Menu, String> parentName();

	public ValueProvider<Menu, Authority> requireAuthority();

	public ValueProvider<Menu, String> name();

	public ValueProvider<Menu, MenuGroup> parent();

	public ValueProvider<Menu, Integer> pos();

	@com.google.gwt.editor.client.Editor.Path("id")
	public ModelKeyProvider<Menu> key();

	public ValueProvider<Menu, Long> id();

}
