
package com.voole.parrot.gwt.common.shared.gridcolumn;

import java.util.Set;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.voole.parrot.gwt.common.shared.property.PropertyUtils;
import com.voole.parrot.shared.entity.account.Account;
import com.voole.parrot.shared.entity.authority.Authority;
import com.voole.parrot.shared.entity.organization.Leader;
import com.voole.parrot.shared.entity.organization.Member;

public class AccountColumnConfig {


    public static ColumnConfig<Account, Leader> leader() {
        ColumnConfig<Account, Leader> leader = new ColumnConfig<Account, Leader>(PropertyUtils.AccountProperty.leader(), 200, "leader");
        leader.setSortable(false);
        leader.setMenuDisabled(true);
        return leader;
    }

    public static ColumnConfig<Account, Member> member() {
        ColumnConfig<Account, Member> member = new ColumnConfig<Account, Member>(PropertyUtils.AccountProperty.member(), 200, "member");
        member.setSortable(false);
        member.setMenuDisabled(true);
        return member;
    }

    public static ColumnConfig<Account, Set<Authority>> authorities() {
        ColumnConfig<Account, Set<Authority>> authorities = new ColumnConfig<Account, Set<Authority>>(PropertyUtils.AccountProperty.authorities(), 200, "authorities");
        authorities.setSortable(false);
        authorities.setMenuDisabled(true);
        return authorities;
    }

    public static ColumnConfig<Account, String> name() {
        ColumnConfig<Account, String> name = new ColumnConfig<Account, String>(PropertyUtils.AccountProperty.name(), 200, "name");
        name.setSortable(false);
        name.setMenuDisabled(true);
        return name;
    }

    public static ColumnConfig<Account, String> password() {
        ColumnConfig<Account, String> password = new ColumnConfig<Account, String>(PropertyUtils.AccountProperty.password(), 200, "password");
        password.setSortable(false);
        password.setMenuDisabled(true);
        return password;
    }

    public static ColumnConfig<Account, Long> id() {
        ColumnConfig<Account, Long> id = new ColumnConfig<Account, Long>(PropertyUtils.AccountProperty.id(), 200, "id");
        id.setSortable(false);
        id.setMenuDisabled(true);
        return id;
    }

}
