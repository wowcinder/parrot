
package com.voole.parrot.gwt.common.shared.gridcolumn;

import java.util.Set;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.voole.parrot.gwt.common.shared.property.PropertyUtils;
import com.voole.parrot.shared.entity.authority.Authority;
import com.voole.parrot.shared.entity.authority.Role;
import com.voole.parrot.shared.entity.organization.SubOrganization;
import com.voole.parrot.shared.entity.organization.TopOrganization;
import com.voole.parrot.shared.entity.organization.TopOrganizationAuthority;

public class TopOrganizationAuthorityColumnConfig {


    public static ColumnConfig<TopOrganizationAuthority, TopOrganization> organization() {
        ColumnConfig<TopOrganizationAuthority, TopOrganization> organization = new ColumnConfig<TopOrganizationAuthority, TopOrganization>(PropertyUtils.TopOrganizationAuthorityProperty.organization(), 200, "organization");
        organization.setSortable(false);
        organization.setMenuDisabled(true);
        return organization;
    }

    public static ColumnConfig<TopOrganizationAuthority, Set<Role>> roles() {
        ColumnConfig<TopOrganizationAuthority, Set<Role>> roles = new ColumnConfig<TopOrganizationAuthority, Set<Role>>(PropertyUtils.TopOrganizationAuthorityProperty.roles(), 200, "roles");
        roles.setSortable(false);
        roles.setMenuDisabled(true);
        return roles;
    }

    public static ColumnConfig<TopOrganizationAuthority, Set<SubOrganization>> subOrganizations() {
        ColumnConfig<TopOrganizationAuthority, Set<SubOrganization>> subOrganizations = new ColumnConfig<TopOrganizationAuthority, Set<SubOrganization>>(PropertyUtils.TopOrganizationAuthorityProperty.subOrganizations(), 200, "subOrganizations");
        subOrganizations.setSortable(false);
        subOrganizations.setMenuDisabled(true);
        return subOrganizations;
    }

    public static ColumnConfig<TopOrganizationAuthority, Authority> authority() {
        ColumnConfig<TopOrganizationAuthority, Authority> authority = new ColumnConfig<TopOrganizationAuthority, Authority>(PropertyUtils.TopOrganizationAuthorityProperty.authority(), 200, "authority");
        authority.setSortable(false);
        authority.setMenuDisabled(true);
        return authority;
    }

    public static ColumnConfig<TopOrganizationAuthority, Long> id() {
        ColumnConfig<TopOrganizationAuthority, Long> id = new ColumnConfig<TopOrganizationAuthority, Long>(PropertyUtils.TopOrganizationAuthorityProperty.id(), 200, "id");
        id.setSortable(false);
        id.setMenuDisabled(true);
        return id;
    }

}
