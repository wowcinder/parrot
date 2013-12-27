
package com.voole.parrot.gwt.common.shared.gridcolumn;

import java.util.List;

import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.voole.parrot.gwt.common.shared.property.PropertyUtils;
import com.voole.parrot.shared.entity.hbasemeta.HbaseTableVersion;
import com.voole.parrot.shared.entity.logmeta.LogModelColumn;
import com.voole.parrot.shared.entity.logmeta.LogModelGroupColumn;
import com.voole.parrot.shared.entity.logmeta.LogModelRootColumn;
import com.voole.parrot.shared.entity.logmeta.LogModelVersion;

public class CtypeLogModelRootColumnColumnConfig {


    public static ColumnConfig<LogModelRootColumn, Long> id() {
        ColumnConfig<LogModelRootColumn, Long> id = new ColumnConfig<LogModelRootColumn, Long>(PropertyUtils.CtypeLogModelRootColumnProperty.id(), 200, "id");
        return id;
    }

    public static ColumnConfig<LogModelRootColumn, String> desc() {
        ColumnConfig<LogModelRootColumn, String> desc = new ColumnConfig<LogModelRootColumn, String>(PropertyUtils.CtypeLogModelRootColumnProperty.desc(), 200, "desc");
        return desc;
    }

    public static ColumnConfig<LogModelRootColumn, HbaseTableVersion> hbaseTableVersion() {
        ColumnConfig<LogModelRootColumn, HbaseTableVersion> hbaseTableVersion = new ColumnConfig<LogModelRootColumn, HbaseTableVersion>(PropertyUtils.CtypeLogModelRootColumnProperty.hbaseTableVersion(), 200, "hbaseTableVersion");
        return hbaseTableVersion;
    }

    public static ColumnConfig<LogModelRootColumn, String> name() {
        ColumnConfig<LogModelRootColumn, String> name = new ColumnConfig<LogModelRootColumn, String>(PropertyUtils.CtypeLogModelRootColumnProperty.name(), 200, "name");
        return name;
    }

    public static ColumnConfig<LogModelRootColumn, List<LogModelColumn>> columns() {
        ColumnConfig<LogModelRootColumn, List<LogModelColumn>> columns = new ColumnConfig<LogModelRootColumn, List<LogModelColumn>>(PropertyUtils.CtypeLogModelRootColumnProperty.columns(), 200, "columns");
        return columns;
    }

    public static ColumnConfig<LogModelRootColumn, LogModelGroupColumn> parent() {
        ColumnConfig<LogModelRootColumn, LogModelGroupColumn> parent = new ColumnConfig<LogModelRootColumn, LogModelGroupColumn>(PropertyUtils.CtypeLogModelRootColumnProperty.parent(), 200, "parent");
        return parent;
    }

    public static ColumnConfig<LogModelRootColumn, Integer> pos() {
        ColumnConfig<LogModelRootColumn, Integer> pos = new ColumnConfig<LogModelRootColumn, Integer>(PropertyUtils.CtypeLogModelRootColumnProperty.pos(), 200, "pos");
        return pos;
    }

    public static ColumnConfig<LogModelRootColumn, LogModelVersion> version() {
        ColumnConfig<LogModelRootColumn, LogModelVersion> version = new ColumnConfig<LogModelRootColumn, LogModelVersion>(PropertyUtils.CtypeLogModelRootColumnProperty.version(), 200, "version");
        return version;
    }

}
