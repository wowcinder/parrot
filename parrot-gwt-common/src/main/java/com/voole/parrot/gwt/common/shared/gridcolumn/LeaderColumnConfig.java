
package com.voole.parrot.gwt.common.shared.gridcolumn;

import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.voole.parrot.gwt.common.shared.property.PropertyUtils;
import com.voole.parrot.shared.entity.organization.User;
import com.voole.parrot.shared.entity.organization.Leader;
import com.voole.parrot.shared.entity.organization.Organization;

public class LeaderColumnConfig {


    public static ColumnConfig<Leader, Long> id() {
        ColumnConfig<Leader, Long> id = new ColumnConfig<Leader, Long>(PropertyUtils.LeaderProperty.id(), 200, "id");
        return id;
    }

    public static ColumnConfig<Leader, Organization> organization() {
        ColumnConfig<Leader, Organization> organization = new ColumnConfig<Leader, Organization>(PropertyUtils.LeaderProperty.organization(), 200, "organization");
        return organization;
    }

    public static ColumnConfig<Leader, User> account() {
        ColumnConfig<Leader, User> account = new ColumnConfig<Leader, User>(PropertyUtils.LeaderProperty.account(), 200, "account");
        return account;
    }

}
