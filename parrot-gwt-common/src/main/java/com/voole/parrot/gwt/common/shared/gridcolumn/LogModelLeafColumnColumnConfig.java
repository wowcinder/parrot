
package com.voole.parrot.gwt.common.shared.gridcolumn;

import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.voole.parrot.gwt.common.shared.property.PropertyUtils;
import com.voole.parrot.shared.entity.hbasemeta.ColumnType;
import com.voole.parrot.shared.entity.hbasemeta.HbaseTableColumn;
import com.voole.parrot.shared.entity.logmeta.LogModelGroupColumn;
import com.voole.parrot.shared.entity.logmeta.LogModelLeafColumn;

public class LogModelLeafColumnColumnConfig {


    public static ColumnConfig<LogModelLeafColumn, Long> id() {
        ColumnConfig<LogModelLeafColumn, Long> id = new ColumnConfig<LogModelLeafColumn, Long>(PropertyUtils.LogModelLeafColumnProperty.id(), 200, "id");
        return id;
    }

    public static ColumnConfig<LogModelLeafColumn, String> desc() {
        ColumnConfig<LogModelLeafColumn, String> desc = new ColumnConfig<LogModelLeafColumn, String>(PropertyUtils.LogModelLeafColumnProperty.desc(), 200, "desc");
        return desc;
    }

    public static ColumnConfig<LogModelLeafColumn, String> name() {
        ColumnConfig<LogModelLeafColumn, String> name = new ColumnConfig<LogModelLeafColumn, String>(PropertyUtils.LogModelLeafColumnProperty.name(), 200, "name");
        return name;
    }

    public static ColumnConfig<LogModelLeafColumn, HbaseTableColumn> hbaseTableColumn() {
        ColumnConfig<LogModelLeafColumn, HbaseTableColumn> hbaseTableColumn = new ColumnConfig<LogModelLeafColumn, HbaseTableColumn>(PropertyUtils.LogModelLeafColumnProperty.hbaseTableColumn(), 200, "hbaseTableColumn");
        return hbaseTableColumn;
    }

    public static ColumnConfig<LogModelLeafColumn, LogModelGroupColumn> parent() {
        ColumnConfig<LogModelLeafColumn, LogModelGroupColumn> parent = new ColumnConfig<LogModelLeafColumn, LogModelGroupColumn>(PropertyUtils.LogModelLeafColumnProperty.parent(), 200, "parent");
        return parent;
    }

    public static ColumnConfig<LogModelLeafColumn, ColumnType> type() {
        ColumnConfig<LogModelLeafColumn, ColumnType> type = new ColumnConfig<LogModelLeafColumn, ColumnType>(PropertyUtils.LogModelLeafColumnProperty.type(), 200, "type");
        return type;
    }

    public static ColumnConfig<LogModelLeafColumn, Integer> pos() {
        ColumnConfig<LogModelLeafColumn, Integer> pos = new ColumnConfig<LogModelLeafColumn, Integer>(PropertyUtils.LogModelLeafColumnProperty.pos(), 200, "pos");
        return pos;
    }

}
