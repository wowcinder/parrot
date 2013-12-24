
package com.voole.parrot.gwt.common.shared.gridcolumn;

import java.util.Set;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.voole.parrot.gwt.common.shared.property.PropertyUtils;
import com.voole.parrot.shared.entity.authority.Authority;
import com.voole.parrot.shared.entity.authority.AuthorityEntrance;
import com.voole.parrot.shared.entity.authority.Role;
import com.voole.parrot.shared.entity.user.User;

public class AuthorityColumnConfig {


    public static ColumnConfig<Authority, Long> id() {
        ColumnConfig<Authority, Long> id = new ColumnConfig<Authority, Long>(PropertyUtils.AuthorityProperty.id(), 200, "id");
        return id;
    }

    public static ColumnConfig<Authority, Set<User>> users() {
        ColumnConfig<Authority, Set<User>> users = new ColumnConfig<Authority, Set<User>>(PropertyUtils.AuthorityProperty.users(), 200, "users");
        return users;
    }

    public static ColumnConfig<Authority, Set<Authority>> dependencies() {
        ColumnConfig<Authority, Set<Authority>> dependencies = new ColumnConfig<Authority, Set<Authority>>(PropertyUtils.AuthorityProperty.dependencies(), 200, "dependencies");
        return dependencies;
    }

    public static ColumnConfig<Authority, String> token() {
        ColumnConfig<Authority, String> token = new ColumnConfig<Authority, String>(PropertyUtils.AuthorityProperty.token(), 200, "token");
        return token;
    }

    public static ColumnConfig<Authority, Set<Role>> roles() {
        ColumnConfig<Authority, Set<Role>> roles = new ColumnConfig<Authority, Set<Role>>(PropertyUtils.AuthorityProperty.roles(), 200, "roles");
        return roles;
    }

    public static ColumnConfig<Authority, String> name() {
        ColumnConfig<Authority, String> name = new ColumnConfig<Authority, String>(PropertyUtils.AuthorityProperty.name(), 200, "name");
        return name;
    }

    public static ColumnConfig<Authority, Set<Authority>> reDependencies() {
        ColumnConfig<Authority, Set<Authority>> reDependencies = new ColumnConfig<Authority, Set<Authority>>(PropertyUtils.AuthorityProperty.reDependencies(), 200, "reDependencies");
        return reDependencies;
    }

    public static ColumnConfig<Authority, AuthorityEntrance> entrance() {
        ColumnConfig<Authority, AuthorityEntrance> entrance = new ColumnConfig<Authority, AuthorityEntrance>(PropertyUtils.AuthorityProperty.entrance(), 200, "entrance");
        return entrance;
    }

    public static ColumnConfig<Authority, Integer> pos() {
        ColumnConfig<Authority, Integer> pos = new ColumnConfig<Authority, Integer>(PropertyUtils.AuthorityProperty.pos(), 200, "pos");
        return pos;
    }

    public static ColumnConfig<Authority, String> entrance_name() {
        ColumnConfig<Authority, String> entrance_name = new ColumnConfig<Authority, String>(PropertyUtils.AuthorityProperty.entrance_name(), 200, "entrance_name");
        return entrance_name;
    }

}
