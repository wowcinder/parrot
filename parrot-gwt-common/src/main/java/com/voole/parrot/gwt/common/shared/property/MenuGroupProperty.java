
package com.voole.parrot.gwt.common.shared.property;

import java.util.List;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.voole.parrot.shared.entity.menu.MenuGroup;
import com.voole.parrot.shared.entity.menu.MenuNode;

public interface MenuGroupProperty
    extends PropertyAccess<MenuGroup>
{


    @com.google.gwt.editor.client.Editor.Path("id")
    public ModelKeyProvider<MenuGroup> key();

    public ValueProvider<MenuGroup, Long> id();

    public ValueProvider<MenuGroup, List<MenuNode>> nodes();

    public ValueProvider<MenuGroup, String> name();

    public ValueProvider<MenuGroup, MenuGroup> parent();

    public ValueProvider<MenuGroup, Integer> pos();

    @com.google.gwt.editor.client.Editor.Path("parent.name")
    public ValueProvider<MenuGroup, String> parent_name();

}
