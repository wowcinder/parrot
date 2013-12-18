
package com.voole.parrot.gwt.common.shared.gridcolumn;

import java.util.Set;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.voole.parrot.gwt.common.shared.property.PropertyUtils;
import com.voole.parrot.shared.entity.account.Account;
import com.voole.parrot.shared.entity.authority.Role;
import com.voole.parrot.shared.entity.organization.Member;
import com.voole.parrot.shared.entity.organization.Organization;

public class MemberColumnConfig {


    public static ColumnConfig<Member, Organization> organization() {
        ColumnConfig<Member, Organization> organization = new ColumnConfig<Member, Organization>(PropertyUtils.MemberProperty.organization(), 200, "organization");
        organization.setSortable(false);
        organization.setMenuDisabled(true);
        return organization;
    }

    public static ColumnConfig<Member, Account> account() {
        ColumnConfig<Member, Account> account = new ColumnConfig<Member, Account>(PropertyUtils.MemberProperty.account(), 200, "account");
        account.setSortable(false);
        account.setMenuDisabled(true);
        return account;
    }

    public static ColumnConfig<Member, Set<Role>> roles() {
        ColumnConfig<Member, Set<Role>> roles = new ColumnConfig<Member, Set<Role>>(PropertyUtils.MemberProperty.roles(), 200, "roles");
        roles.setSortable(false);
        roles.setMenuDisabled(true);
        return roles;
    }

    public static ColumnConfig<Member, Long> id() {
        ColumnConfig<Member, Long> id = new ColumnConfig<Member, Long>(PropertyUtils.MemberProperty.id(), 200, "id");
        id.setSortable(false);
        id.setMenuDisabled(true);
        return id;
    }

}
