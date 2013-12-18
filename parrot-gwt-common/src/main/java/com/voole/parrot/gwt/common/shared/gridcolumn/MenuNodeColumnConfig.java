
package com.voole.parrot.gwt.common.shared.gridcolumn;

import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.voole.parrot.gwt.common.shared.property.PropertyUtils;
import com.voole.parrot.shared.entity.menu.MenuGroup;
import com.voole.parrot.shared.entity.menu.MenuNode;

public class MenuNodeColumnConfig {


    public static ColumnConfig<MenuNode, String> name() {
        ColumnConfig<MenuNode, String> name = new ColumnConfig<MenuNode, String>(PropertyUtils.MenuNodeProperty.name(), 200, "name");
        name.setSortable(false);
        name.setMenuDisabled(true);
        return name;
    }

    public static ColumnConfig<MenuNode, MenuGroup> parent() {
        ColumnConfig<MenuNode, MenuGroup> parent = new ColumnConfig<MenuNode, MenuGroup>(PropertyUtils.MenuNodeProperty.parent(), 200, "parent");
        parent.setSortable(false);
        parent.setMenuDisabled(true);
        return parent;
    }

    public static ColumnConfig<MenuNode, Integer> pos() {
        ColumnConfig<MenuNode, Integer> pos = new ColumnConfig<MenuNode, Integer>(PropertyUtils.MenuNodeProperty.pos(), 200, "pos");
        pos.setSortable(false);
        pos.setMenuDisabled(true);
        return pos;
    }

    public static ColumnConfig<MenuNode, Long> id() {
        ColumnConfig<MenuNode, Long> id = new ColumnConfig<MenuNode, Long>(PropertyUtils.MenuNodeProperty.id(), 200, "id");
        id.setSortable(false);
        id.setMenuDisabled(true);
        return id;
    }

}
