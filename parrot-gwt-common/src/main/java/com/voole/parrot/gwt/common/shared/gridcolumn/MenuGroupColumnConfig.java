
package com.voole.parrot.gwt.common.shared.gridcolumn;

import java.util.List;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.voole.parrot.gwt.common.shared.property.PropertyUtils;
import com.voole.parrot.shared.entity.menu.MenuGroup;
import com.voole.parrot.shared.entity.menu.MenuNode;

public class MenuGroupColumnConfig {


    public static ColumnConfig<MenuGroup, Long> id() {
        ColumnConfig<MenuGroup, Long> id = new ColumnConfig<MenuGroup, Long>(PropertyUtils.MenuGroupProperty.id(), 200, "id");
        return id;
    }

    public static ColumnConfig<MenuGroup, List<MenuNode>> nodes() {
        ColumnConfig<MenuGroup, List<MenuNode>> nodes = new ColumnConfig<MenuGroup, List<MenuNode>>(PropertyUtils.MenuGroupProperty.nodes(), 200, "nodes");
        return nodes;
    }

    public static ColumnConfig<MenuGroup, String> name() {
        ColumnConfig<MenuGroup, String> name = new ColumnConfig<MenuGroup, String>(PropertyUtils.MenuGroupProperty.name(), 200, "name");
        return name;
    }

    public static ColumnConfig<MenuGroup, MenuGroup> parent() {
        ColumnConfig<MenuGroup, MenuGroup> parent = new ColumnConfig<MenuGroup, MenuGroup>(PropertyUtils.MenuGroupProperty.parent(), 200, "parent");
        return parent;
    }

    public static ColumnConfig<MenuGroup, Integer> pos() {
        ColumnConfig<MenuGroup, Integer> pos = new ColumnConfig<MenuGroup, Integer>(PropertyUtils.MenuGroupProperty.pos(), 200, "pos");
        return pos;
    }

    public static ColumnConfig<MenuGroup, String> parent_name() {
        ColumnConfig<MenuGroup, String> parent_name = new ColumnConfig<MenuGroup, String>(PropertyUtils.MenuGroupProperty.parent_name(), 200, "parent_name");
        return parent_name;
    }

}
