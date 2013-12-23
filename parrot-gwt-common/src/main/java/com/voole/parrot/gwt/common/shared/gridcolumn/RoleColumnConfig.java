
package com.voole.parrot.gwt.common.shared.gridcolumn;

import java.util.Set;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.voole.parrot.gwt.common.shared.property.PropertyUtils;
import com.voole.parrot.shared.entity.authority.Role;
import com.voole.parrot.shared.entity.organization.Organization;
import com.voole.parrot.shared.entity.organization.TopOrganizationAuthority;
import com.voole.parrot.shared.entity.organization.User;

public class RoleColumnConfig {


    public static ColumnConfig<Role, Long> id() {
        ColumnConfig<Role, Long> id = new ColumnConfig<Role, Long>(PropertyUtils.RoleProperty.id(), 200, "id");
        return id;
    }

    public static ColumnConfig<Role, Organization> organization() {
        ColumnConfig<Role, Organization> organization = new ColumnConfig<Role, Organization>(PropertyUtils.RoleProperty.organization(), 200, "organization");
        return organization;
    }

    public static ColumnConfig<Role, Set<TopOrganizationAuthority>> authorities() {
        ColumnConfig<Role, Set<TopOrganizationAuthority>> authorities = new ColumnConfig<Role, Set<TopOrganizationAuthority>>(PropertyUtils.RoleProperty.authorities(), 200, "authorities");
        return authorities;
    }

    public static ColumnConfig<Role, String> name() {
        ColumnConfig<Role, String> name = new ColumnConfig<Role, String>(PropertyUtils.RoleProperty.name(), 200, "name");
        return name;
    }

    public static ColumnConfig<Role, Set<User>> members() {
        ColumnConfig<Role, Set<User>> members = new ColumnConfig<Role, Set<User>>(PropertyUtils.RoleProperty.members(), 200, "members");
        return members;
    }

}
