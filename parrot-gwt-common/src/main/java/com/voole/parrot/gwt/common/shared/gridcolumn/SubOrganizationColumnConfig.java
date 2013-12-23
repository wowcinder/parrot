
package com.voole.parrot.gwt.common.shared.gridcolumn;

import java.util.Set;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.voole.parrot.gwt.common.shared.property.PropertyUtils;
import com.voole.parrot.shared.entity.authority.Role;
import com.voole.parrot.shared.entity.organization.Organization;
import com.voole.parrot.shared.entity.organization.SubOrganization;
import com.voole.parrot.shared.entity.organization.TopOrganizationAuthority;
import com.voole.parrot.shared.entity.organization.User;

public class SubOrganizationColumnConfig {


    public static ColumnConfig<SubOrganization, Long> id() {
        ColumnConfig<SubOrganization, Long> id = new ColumnConfig<SubOrganization, Long>(PropertyUtils.SubOrganizationProperty.id(), 200, "id");
        return id;
    }

    public static ColumnConfig<SubOrganization, Set<TopOrganizationAuthority>> authorities() {
        ColumnConfig<SubOrganization, Set<TopOrganizationAuthority>> authorities = new ColumnConfig<SubOrganization, Set<TopOrganizationAuthority>>(PropertyUtils.SubOrganizationProperty.authorities(), 200, "authorities");
        return authorities;
    }

    public static ColumnConfig<SubOrganization, String> name() {
        ColumnConfig<SubOrganization, String> name = new ColumnConfig<SubOrganization, String>(PropertyUtils.SubOrganizationProperty.name(), 200, "name");
        return name;
    }

    public static ColumnConfig<SubOrganization, Set<Role>> roles() {
        ColumnConfig<SubOrganization, Set<Role>> roles = new ColumnConfig<SubOrganization, Set<Role>>(PropertyUtils.SubOrganizationProperty.roles(), 200, "roles");
        return roles;
    }

    public static ColumnConfig<SubOrganization, Set<SubOrganization>> subOrganizations() {
        ColumnConfig<SubOrganization, Set<SubOrganization>> subOrganizations = new ColumnConfig<SubOrganization, Set<SubOrganization>>(PropertyUtils.SubOrganizationProperty.subOrganizations(), 200, "subOrganizations");
        return subOrganizations;
    }

    public static ColumnConfig<SubOrganization, Organization> parentOrganization() {
        ColumnConfig<SubOrganization, Organization> parentOrganization = new ColumnConfig<SubOrganization, Organization>(PropertyUtils.SubOrganizationProperty.parentOrganization(), 200, "parentOrganization");
        return parentOrganization;
    }

    public static ColumnConfig<SubOrganization, Set<User>> members() {
        ColumnConfig<SubOrganization, Set<User>> members = new ColumnConfig<SubOrganization, Set<User>>(PropertyUtils.SubOrganizationProperty.members(), 200, "members");
        return members;
    }

}
