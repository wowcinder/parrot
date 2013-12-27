
package com.voole.parrot.gwt.common.shared.gridcolumn;

import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.voole.parrot.gwt.common.shared.property.PropertyUtils;
import com.voole.parrot.shared.entity.logmeta.LogModelColumn;
import com.voole.parrot.shared.entity.logmeta.LogModelGroupColumn;

public class LogModelColumnColumnConfig {


    public static ColumnConfig<LogModelColumn, Long> id() {
        ColumnConfig<LogModelColumn, Long> id = new ColumnConfig<LogModelColumn, Long>(PropertyUtils.LogModelColumnProperty.id(), 200, "id");
        return id;
    }

    public static ColumnConfig<LogModelColumn, String> desc() {
        ColumnConfig<LogModelColumn, String> desc = new ColumnConfig<LogModelColumn, String>(PropertyUtils.LogModelColumnProperty.desc(), 200, "desc");
        return desc;
    }

    public static ColumnConfig<LogModelColumn, String> name() {
        ColumnConfig<LogModelColumn, String> name = new ColumnConfig<LogModelColumn, String>(PropertyUtils.LogModelColumnProperty.name(), 200, "name");
        return name;
    }

    public static ColumnConfig<LogModelColumn, LogModelGroupColumn> parent() {
        ColumnConfig<LogModelColumn, LogModelGroupColumn> parent = new ColumnConfig<LogModelColumn, LogModelGroupColumn>(PropertyUtils.LogModelColumnProperty.parent(), 200, "parent");
        return parent;
    }

    public static ColumnConfig<LogModelColumn, Integer> pos() {
        ColumnConfig<LogModelColumn, Integer> pos = new ColumnConfig<LogModelColumn, Integer>(PropertyUtils.LogModelColumnProperty.pos(), 200, "pos");
        return pos;
    }

}
