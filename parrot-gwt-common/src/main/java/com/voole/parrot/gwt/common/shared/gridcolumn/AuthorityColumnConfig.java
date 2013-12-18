
package com.voole.parrot.gwt.common.shared.gridcolumn;

import java.util.List;
import java.util.Set;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.voole.parrot.gwt.common.shared.property.PropertyUtils;
import com.voole.parrot.shared.entity.account.Account;
import com.voole.parrot.shared.entity.authority.Authority;
import com.voole.parrot.shared.entity.authority.AuthorityEntrance;
import com.voole.parrot.shared.entity.organization.TopOrganizationAuthority;

public class AuthorityColumnConfig {


    public static ColumnConfig<Authority, String> token() {
        ColumnConfig<Authority, String> token = new ColumnConfig<Authority, String>(PropertyUtils.AuthorityProperty.token(), 200, "token");
        return token;
    }

    public static ColumnConfig<Authority, AuthorityEntrance> entrance() {
        ColumnConfig<Authority, AuthorityEntrance> entrance = new ColumnConfig<Authority, AuthorityEntrance>(PropertyUtils.AuthorityProperty.entrance(), 200, "entrance");
        return entrance;
    }

    public static ColumnConfig<Authority, List<TopOrganizationAuthority>> organizationAuthorities() {
        ColumnConfig<Authority, List<TopOrganizationAuthority>> organizationAuthorities = new ColumnConfig<Authority, List<TopOrganizationAuthority>>(PropertyUtils.AuthorityProperty.organizationAuthorities(), 200, "organizationAuthorities");
        return organizationAuthorities;
    }

    public static ColumnConfig<Authority, Set<Authority>> dependencies() {
        ColumnConfig<Authority, Set<Authority>> dependencies = new ColumnConfig<Authority, Set<Authority>>(PropertyUtils.AuthorityProperty.dependencies(), 200, "dependencies");
        return dependencies;
    }

    public static ColumnConfig<Authority, Set<Authority>> reDependencies() {
        ColumnConfig<Authority, Set<Authority>> reDependencies = new ColumnConfig<Authority, Set<Authority>>(PropertyUtils.AuthorityProperty.reDependencies(), 200, "reDependencies");
        return reDependencies;
    }

    public static ColumnConfig<Authority, Set<Account>> accounts() {
        ColumnConfig<Authority, Set<Account>> accounts = new ColumnConfig<Authority, Set<Account>>(PropertyUtils.AuthorityProperty.accounts(), 200, "accounts");
        return accounts;
    }

    public static ColumnConfig<Authority, String> name() {
        ColumnConfig<Authority, String> name = new ColumnConfig<Authority, String>(PropertyUtils.AuthorityProperty.name(), 200, "name");
        return name;
    }

    public static ColumnConfig<Authority, Integer> pos() {
        ColumnConfig<Authority, Integer> pos = new ColumnConfig<Authority, Integer>(PropertyUtils.AuthorityProperty.pos(), 200, "pos");
        return pos;
    }

}
