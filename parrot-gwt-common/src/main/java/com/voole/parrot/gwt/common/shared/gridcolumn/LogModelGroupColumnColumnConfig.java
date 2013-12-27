
package com.voole.parrot.gwt.common.shared.gridcolumn;

import java.util.List;

import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.voole.parrot.gwt.common.shared.property.PropertyUtils;
import com.voole.parrot.shared.entity.hbasemeta.HbaseTableVersion;
import com.voole.parrot.shared.entity.logmeta.LogModelColumn;
import com.voole.parrot.shared.entity.logmeta.LogModelGroupColumn;

public class LogModelGroupColumnColumnConfig {


    public static ColumnConfig<LogModelGroupColumn, Long> id() {
        ColumnConfig<LogModelGroupColumn, Long> id = new ColumnConfig<LogModelGroupColumn, Long>(PropertyUtils.LogModelGroupColumnProperty.id(), 200, "id");
        return id;
    }

    public static ColumnConfig<LogModelGroupColumn, String> desc() {
        ColumnConfig<LogModelGroupColumn, String> desc = new ColumnConfig<LogModelGroupColumn, String>(PropertyUtils.LogModelGroupColumnProperty.desc(), 200, "desc");
        return desc;
    }

    public static ColumnConfig<LogModelGroupColumn, HbaseTableVersion> hbaseTableVersion() {
        ColumnConfig<LogModelGroupColumn, HbaseTableVersion> hbaseTableVersion = new ColumnConfig<LogModelGroupColumn, HbaseTableVersion>(PropertyUtils.LogModelGroupColumnProperty.hbaseTableVersion(), 200, "hbaseTableVersion");
        return hbaseTableVersion;
    }

    public static ColumnConfig<LogModelGroupColumn, String> name() {
        ColumnConfig<LogModelGroupColumn, String> name = new ColumnConfig<LogModelGroupColumn, String>(PropertyUtils.LogModelGroupColumnProperty.name(), 200, "name");
        return name;
    }

    public static ColumnConfig<LogModelGroupColumn, List<LogModelColumn>> columns() {
        ColumnConfig<LogModelGroupColumn, List<LogModelColumn>> columns = new ColumnConfig<LogModelGroupColumn, List<LogModelColumn>>(PropertyUtils.LogModelGroupColumnProperty.columns(), 200, "columns");
        return columns;
    }

    public static ColumnConfig<LogModelGroupColumn, LogModelGroupColumn> parent() {
        ColumnConfig<LogModelGroupColumn, LogModelGroupColumn> parent = new ColumnConfig<LogModelGroupColumn, LogModelGroupColumn>(PropertyUtils.LogModelGroupColumnProperty.parent(), 200, "parent");
        return parent;
    }

    public static ColumnConfig<LogModelGroupColumn, Integer> pos() {
        ColumnConfig<LogModelGroupColumn, Integer> pos = new ColumnConfig<LogModelGroupColumn, Integer>(PropertyUtils.LogModelGroupColumnProperty.pos(), 200, "pos");
        return pos;
    }

}
