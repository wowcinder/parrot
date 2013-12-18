
package com.voole.parrot.gwt.common.shared.gridcolumn;

import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.voole.parrot.gwt.common.shared.property.PropertyUtils;
import com.voole.parrot.shared.entity.authority.Authority;
import com.voole.parrot.shared.entity.menu.Menu;
import com.voole.parrot.shared.entity.menu.MenuGroup;

public class MenuColumnConfig {


    public static ColumnConfig<Menu, String> token() {
        ColumnConfig<Menu, String> token = new ColumnConfig<Menu, String>(PropertyUtils.MenuProperty.token(), 200, "token");
        token.setSortable(false);
        token.setMenuDisabled(true);
        return token;
    }

    public static ColumnConfig<Menu, Authority> requireAuthority() {
        ColumnConfig<Menu, Authority> requireAuthority = new ColumnConfig<Menu, Authority>(PropertyUtils.MenuProperty.requireAuthority(), 200, "requireAuthority");
        requireAuthority.setSortable(false);
        requireAuthority.setMenuDisabled(true);
        return requireAuthority;
    }

    public static ColumnConfig<Menu, String> name() {
        ColumnConfig<Menu, String> name = new ColumnConfig<Menu, String>(PropertyUtils.MenuProperty.name(), 200, "name");
        name.setSortable(false);
        name.setMenuDisabled(true);
        return name;
    }

    public static ColumnConfig<Menu, MenuGroup> parent() {
        ColumnConfig<Menu, MenuGroup> parent = new ColumnConfig<Menu, MenuGroup>(PropertyUtils.MenuProperty.parent(), 200, "parent");
        parent.setSortable(false);
        parent.setMenuDisabled(true);
        return parent;
    }

    public static ColumnConfig<Menu, Integer> pos() {
        ColumnConfig<Menu, Integer> pos = new ColumnConfig<Menu, Integer>(PropertyUtils.MenuProperty.pos(), 200, "pos");
        pos.setSortable(false);
        pos.setMenuDisabled(true);
        return pos;
    }

    public static ColumnConfig<Menu, Long> id() {
        ColumnConfig<Menu, Long> id = new ColumnConfig<Menu, Long>(PropertyUtils.MenuProperty.id(), 200, "id");
        id.setSortable(false);
        id.setMenuDisabled(true);
        return id;
    }

}
