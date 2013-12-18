
package com.voole.parrot.gwt.common.shared.gridcolumn;

import java.util.Set;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.voole.parrot.gwt.common.shared.property.PropertyUtils;
import com.voole.parrot.shared.entity.authority.Role;
import com.voole.parrot.shared.entity.organization.Leader;
import com.voole.parrot.shared.entity.organization.Member;
import com.voole.parrot.shared.entity.organization.SubOrganization;
import com.voole.parrot.shared.entity.organization.TopOrganization;
import com.voole.parrot.shared.entity.organization.TopOrganizationAuthority;

public class TopOrganizationColumnConfig {


    public static ColumnConfig<TopOrganization, Set<TopOrganizationAuthority>> authorities() {
        ColumnConfig<TopOrganization, Set<TopOrganizationAuthority>> authorities = new ColumnConfig<TopOrganization, Set<TopOrganizationAuthority>>(PropertyUtils.TopOrganizationProperty.authorities(), 200, "authorities");
        authorities.setSortable(false);
        authorities.setMenuDisabled(true);
        return authorities;
    }

    public static ColumnConfig<TopOrganization, Set<Member>> members() {
        ColumnConfig<TopOrganization, Set<Member>> members = new ColumnConfig<TopOrganization, Set<Member>>(PropertyUtils.TopOrganizationProperty.members(), 200, "members");
        members.setSortable(false);
        members.setMenuDisabled(true);
        return members;
    }

    public static ColumnConfig<TopOrganization, Set<Role>> roles() {
        ColumnConfig<TopOrganization, Set<Role>> roles = new ColumnConfig<TopOrganization, Set<Role>>(PropertyUtils.TopOrganizationProperty.roles(), 200, "roles");
        roles.setSortable(false);
        roles.setMenuDisabled(true);
        return roles;
    }

    public static ColumnConfig<TopOrganization, Set<Leader>> leaders() {
        ColumnConfig<TopOrganization, Set<Leader>> leaders = new ColumnConfig<TopOrganization, Set<Leader>>(PropertyUtils.TopOrganizationProperty.leaders(), 200, "leaders");
        leaders.setSortable(false);
        leaders.setMenuDisabled(true);
        return leaders;
    }

    public static ColumnConfig<TopOrganization, Set<SubOrganization>> subOrganizations() {
        ColumnConfig<TopOrganization, Set<SubOrganization>> subOrganizations = new ColumnConfig<TopOrganization, Set<SubOrganization>>(PropertyUtils.TopOrganizationProperty.subOrganizations(), 200, "subOrganizations");
        subOrganizations.setSortable(false);
        subOrganizations.setMenuDisabled(true);
        return subOrganizations;
    }

    public static ColumnConfig<TopOrganization, String> name() {
        ColumnConfig<TopOrganization, String> name = new ColumnConfig<TopOrganization, String>(PropertyUtils.TopOrganizationProperty.name(), 200, "name");
        name.setSortable(false);
        name.setMenuDisabled(true);
        return name;
    }

    public static ColumnConfig<TopOrganization, Long> id() {
        ColumnConfig<TopOrganization, Long> id = new ColumnConfig<TopOrganization, Long>(PropertyUtils.TopOrganizationProperty.id(), 200, "id");
        id.setSortable(false);
        id.setMenuDisabled(true);
        return id;
    }

}
