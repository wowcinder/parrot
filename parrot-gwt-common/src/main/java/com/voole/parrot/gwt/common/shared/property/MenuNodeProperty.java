
package com.voole.parrot.gwt.common.shared.property;

import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.voole.parrot.shared.entity.menu.MenuGroup;
import com.voole.parrot.shared.entity.menu.MenuNode;

public interface MenuNodeProperty
    extends PropertyAccess<MenuNode>
{


    @com.google.gwt.editor.client.Editor.Path("id")
    public ModelKeyProvider<MenuNode> key();

    public ValueProvider<MenuNode, Long> id();

    public ValueProvider<MenuNode, String> name();

    public ValueProvider<MenuNode, MenuGroup> parent();

    public ValueProvider<MenuNode, Integer> pos();

    @com.google.gwt.editor.client.Editor.Path("parent.name")
    public ValueProvider<MenuNode, String> parent_name();

}
