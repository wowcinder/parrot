
package com.voole.parrot.gwt.common.shared.gridcolumn;

import java.util.Set;

import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.voole.parrot.gwt.common.shared.property.PropertyUtils;
import com.voole.parrot.shared.entity.authority.Authority;
import com.voole.parrot.shared.entity.organization.User;
import com.voole.parrot.shared.entity.organization.Leader;
import com.voole.parrot.shared.entity.organization.Member;

public class AccountColumnConfig {


    public static ColumnConfig<User, Long> id() {
        ColumnConfig<User, Long> id = new ColumnConfig<User, Long>(PropertyUtils.AccountProperty.id(), 200, "id");
        return id;
    }

    public static ColumnConfig<User, Member> member() {
        ColumnConfig<User, Member> member = new ColumnConfig<User, Member>(PropertyUtils.AccountProperty.member(), 200, "member");
        return member;
    }

    public static ColumnConfig<User, Set<Authority>> authorities() {
        ColumnConfig<User, Set<Authority>> authorities = new ColumnConfig<User, Set<Authority>>(PropertyUtils.AccountProperty.authorities(), 200, "authorities");
        return authorities;
    }

    public static ColumnConfig<User, String> name() {
        ColumnConfig<User, String> name = new ColumnConfig<User, String>(PropertyUtils.AccountProperty.name(), 200, "name");
        return name;
    }

    public static ColumnConfig<User, Leader> leader() {
        ColumnConfig<User, Leader> leader = new ColumnConfig<User, Leader>(PropertyUtils.AccountProperty.leader(), 200, "leader");
        return leader;
    }

    public static ColumnConfig<User, String> password() {
        ColumnConfig<User, String> password = new ColumnConfig<User, String>(PropertyUtils.AccountProperty.password(), 200, "password");
        return password;
    }

}
