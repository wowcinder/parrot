
package com.voole.parrot.gwt.common.shared.gridcolumn;

import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.voole.parrot.gwt.common.shared.property.PropertyUtils;
import com.voole.parrot.shared.entity.menu.MenuGroup;
import com.voole.parrot.shared.entity.menu.MenuNode;

public class MenuNodeColumnConfig {


    public static ColumnConfig<MenuNode, Long> id() {
        ColumnConfig<MenuNode, Long> id = new ColumnConfig<MenuNode, Long>(PropertyUtils.MenuNodeProperty.id(), 200, "id");
        return id;
    }

    public static ColumnConfig<MenuNode, String> name() {
        ColumnConfig<MenuNode, String> name = new ColumnConfig<MenuNode, String>(PropertyUtils.MenuNodeProperty.name(), 200, "name");
        return name;
    }

    public static ColumnConfig<MenuNode, MenuGroup> parent() {
        ColumnConfig<MenuNode, MenuGroup> parent = new ColumnConfig<MenuNode, MenuGroup>(PropertyUtils.MenuNodeProperty.parent(), 200, "parent");
        return parent;
    }

    public static ColumnConfig<MenuNode, Integer> pos() {
        ColumnConfig<MenuNode, Integer> pos = new ColumnConfig<MenuNode, Integer>(PropertyUtils.MenuNodeProperty.pos(), 200, "pos");
        return pos;
    }

    public static ColumnConfig<MenuNode, String> parent_name() {
        ColumnConfig<MenuNode, String> parent_name = new ColumnConfig<MenuNode, String>(PropertyUtils.MenuNodeProperty.parent_name(), 200, "parent_name");
        return parent_name;
    }

}
