
package com.voole.parrot.gwt.common.shared.gridcolumn;

import java.util.Set;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.voole.parrot.gwt.common.shared.property.PropertyUtils;
import com.voole.parrot.shared.entity.authority.Role;
import com.voole.parrot.shared.entity.organization.Organization;
import com.voole.parrot.shared.entity.organization.SubOrganization;
import com.voole.parrot.shared.entity.organization.User;

public class OrganizationColumnConfig {


    public static ColumnConfig<Organization, Long> id() {
        ColumnConfig<Organization, Long> id = new ColumnConfig<Organization, Long>(PropertyUtils.OrganizationProperty.id(), 200, "id");
        return id;
    }

    public static ColumnConfig<Organization, String> name() {
        ColumnConfig<Organization, String> name = new ColumnConfig<Organization, String>(PropertyUtils.OrganizationProperty.name(), 200, "name");
        return name;
    }

    public static ColumnConfig<Organization, Set<Role>> roles() {
        ColumnConfig<Organization, Set<Role>> roles = new ColumnConfig<Organization, Set<Role>>(PropertyUtils.OrganizationProperty.roles(), 200, "roles");
        return roles;
    }

    public static ColumnConfig<Organization, Set<SubOrganization>> subOrganizations() {
        ColumnConfig<Organization, Set<SubOrganization>> subOrganizations = new ColumnConfig<Organization, Set<SubOrganization>>(PropertyUtils.OrganizationProperty.subOrganizations(), 200, "subOrganizations");
        return subOrganizations;
    }

    public static ColumnConfig<Organization, Set<User>> members() {
        ColumnConfig<Organization, Set<User>> members = new ColumnConfig<Organization, Set<User>>(PropertyUtils.OrganizationProperty.members(), 200, "members");
        return members;
    }

}
