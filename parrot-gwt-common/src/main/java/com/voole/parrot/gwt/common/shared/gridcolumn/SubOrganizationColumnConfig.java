
package com.voole.parrot.gwt.common.shared.gridcolumn;

import java.util.Set;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.voole.parrot.gwt.common.shared.property.PropertyUtils;
import com.voole.parrot.shared.entity.authority.Role;
import com.voole.parrot.shared.entity.organization.Leader;
import com.voole.parrot.shared.entity.organization.Member;
import com.voole.parrot.shared.entity.organization.Organization;
import com.voole.parrot.shared.entity.organization.SubOrganization;
import com.voole.parrot.shared.entity.organization.TopOrganizationAuthority;

public class SubOrganizationColumnConfig {


    public static ColumnConfig<SubOrganization, Set<TopOrganizationAuthority>> authorities() {
        ColumnConfig<SubOrganization, Set<TopOrganizationAuthority>> authorities = new ColumnConfig<SubOrganization, Set<TopOrganizationAuthority>>(PropertyUtils.SubOrganizationProperty.authorities(), 200, "authorities");
        authorities.setSortable(false);
        authorities.setMenuDisabled(true);
        return authorities;
    }

    public static ColumnConfig<SubOrganization, Organization> parentOrganization() {
        ColumnConfig<SubOrganization, Organization> parentOrganization = new ColumnConfig<SubOrganization, Organization>(PropertyUtils.SubOrganizationProperty.parentOrganization(), 200, "parentOrganization");
        parentOrganization.setSortable(false);
        parentOrganization.setMenuDisabled(true);
        return parentOrganization;
    }

    public static ColumnConfig<SubOrganization, Set<Member>> members() {
        ColumnConfig<SubOrganization, Set<Member>> members = new ColumnConfig<SubOrganization, Set<Member>>(PropertyUtils.SubOrganizationProperty.members(), 200, "members");
        members.setSortable(false);
        members.setMenuDisabled(true);
        return members;
    }

    public static ColumnConfig<SubOrganization, Set<Role>> roles() {
        ColumnConfig<SubOrganization, Set<Role>> roles = new ColumnConfig<SubOrganization, Set<Role>>(PropertyUtils.SubOrganizationProperty.roles(), 200, "roles");
        roles.setSortable(false);
        roles.setMenuDisabled(true);
        return roles;
    }

    public static ColumnConfig<SubOrganization, Set<Leader>> leaders() {
        ColumnConfig<SubOrganization, Set<Leader>> leaders = new ColumnConfig<SubOrganization, Set<Leader>>(PropertyUtils.SubOrganizationProperty.leaders(), 200, "leaders");
        leaders.setSortable(false);
        leaders.setMenuDisabled(true);
        return leaders;
    }

    public static ColumnConfig<SubOrganization, Set<SubOrganization>> subOrganizations() {
        ColumnConfig<SubOrganization, Set<SubOrganization>> subOrganizations = new ColumnConfig<SubOrganization, Set<SubOrganization>>(PropertyUtils.SubOrganizationProperty.subOrganizations(), 200, "subOrganizations");
        subOrganizations.setSortable(false);
        subOrganizations.setMenuDisabled(true);
        return subOrganizations;
    }

    public static ColumnConfig<SubOrganization, String> name() {
        ColumnConfig<SubOrganization, String> name = new ColumnConfig<SubOrganization, String>(PropertyUtils.SubOrganizationProperty.name(), 200, "name");
        name.setSortable(false);
        name.setMenuDisabled(true);
        return name;
    }

    public static ColumnConfig<SubOrganization, Long> id() {
        ColumnConfig<SubOrganization, Long> id = new ColumnConfig<SubOrganization, Long>(PropertyUtils.SubOrganizationProperty.id(), 200, "id");
        id.setSortable(false);
        id.setMenuDisabled(true);
        return id;
    }

}
