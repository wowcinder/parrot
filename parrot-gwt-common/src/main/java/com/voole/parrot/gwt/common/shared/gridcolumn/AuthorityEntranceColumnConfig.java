
package com.voole.parrot.gwt.common.shared.gridcolumn;

import java.util.List;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.voole.parrot.gwt.common.shared.property.PropertyUtils;
import com.voole.parrot.shared.entity.authority.Authority;
import com.voole.parrot.shared.entity.authority.AuthorityEntrance;

public class AuthorityEntranceColumnConfig {


    public static ColumnConfig<AuthorityEntrance, Long> id() {
        ColumnConfig<AuthorityEntrance, Long> id = new ColumnConfig<AuthorityEntrance, Long>(PropertyUtils.AuthorityEntranceProperty.id(), 200, "id");
        return id;
    }

    public static ColumnConfig<AuthorityEntrance, List<Authority>> authorities() {
        ColumnConfig<AuthorityEntrance, List<Authority>> authorities = new ColumnConfig<AuthorityEntrance, List<Authority>>(PropertyUtils.AuthorityEntranceProperty.authorities(), 200, "authorities");
        return authorities;
    }

    public static ColumnConfig<AuthorityEntrance, String> name() {
        ColumnConfig<AuthorityEntrance, String> name = new ColumnConfig<AuthorityEntrance, String>(PropertyUtils.AuthorityEntranceProperty.name(), 200, "name");
        return name;
    }

}
