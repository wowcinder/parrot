
package com.voole.parrot.gwt.common.shared.gridcolumn;

import java.util.Set;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.voole.parrot.gwt.common.shared.property.PropertyUtils;
import com.voole.parrot.shared.entity.authority.Authority;
import com.voole.parrot.shared.entity.authority.Role;
import com.voole.parrot.shared.entity.user.User;

public class UserColumnConfig {


    public static ColumnConfig<User, Long> id() {
        ColumnConfig<User, Long> id = new ColumnConfig<User, Long>(PropertyUtils.UserProperty.id(), 200, "id");
        return id;
    }

    public static ColumnConfig<User, Set<Authority>> authorities() {
        ColumnConfig<User, Set<Authority>> authorities = new ColumnConfig<User, Set<Authority>>(PropertyUtils.UserProperty.authorities(), 200, "authorities");
        return authorities;
    }

    public static ColumnConfig<User, String> name() {
        ColumnConfig<User, String> name = new ColumnConfig<User, String>(PropertyUtils.UserProperty.name(), 200, "name");
        return name;
    }

    public static ColumnConfig<User, Set<Role>> roles() {
        ColumnConfig<User, Set<Role>> roles = new ColumnConfig<User, Set<Role>>(PropertyUtils.UserProperty.roles(), 200, "roles");
        return roles;
    }

    public static ColumnConfig<User, String> password() {
        ColumnConfig<User, String> password = new ColumnConfig<User, String>(PropertyUtils.UserProperty.password(), 200, "password");
        return password;
    }

}
