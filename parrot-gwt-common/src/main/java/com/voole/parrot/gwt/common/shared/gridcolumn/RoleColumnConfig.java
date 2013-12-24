
package com.voole.parrot.gwt.common.shared.gridcolumn;

import java.util.Set;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.voole.parrot.gwt.common.shared.property.PropertyUtils;
import com.voole.parrot.shared.entity.authority.Authority;
import com.voole.parrot.shared.entity.authority.Role;
import com.voole.parrot.shared.entity.user.User;

public class RoleColumnConfig {


    public static ColumnConfig<Role, Long> id() {
        ColumnConfig<Role, Long> id = new ColumnConfig<Role, Long>(PropertyUtils.RoleProperty.id(), 200, "id");
        return id;
    }

    public static ColumnConfig<Role, Set<User>> users() {
        ColumnConfig<Role, Set<User>> users = new ColumnConfig<Role, Set<User>>(PropertyUtils.RoleProperty.users(), 200, "users");
        return users;
    }

    public static ColumnConfig<Role, Set<Authority>> authorities() {
        ColumnConfig<Role, Set<Authority>> authorities = new ColumnConfig<Role, Set<Authority>>(PropertyUtils.RoleProperty.authorities(), 200, "authorities");
        return authorities;
    }

    public static ColumnConfig<Role, String> name() {
        ColumnConfig<Role, String> name = new ColumnConfig<Role, String>(PropertyUtils.RoleProperty.name(), 200, "name");
        return name;
    }

}
