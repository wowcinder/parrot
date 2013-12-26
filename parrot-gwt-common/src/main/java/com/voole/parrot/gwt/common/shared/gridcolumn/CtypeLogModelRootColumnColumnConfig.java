
package com.voole.parrot.gwt.common.shared.gridcolumn;

import java.util.List;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.voole.parrot.gwt.common.shared.property.PropertyUtils;
import com.voole.parrot.shared.entity.ctypelogmeta.CtypeLogModelColumn;
import com.voole.parrot.shared.entity.ctypelogmeta.CtypeLogModelGroupColumn;
import com.voole.parrot.shared.entity.ctypelogmeta.CtypeLogModelRootColumn;
import com.voole.parrot.shared.entity.ctypelogmeta.CtypeLogModelVersion;
import com.voole.parrot.shared.entity.hbasemeta.HbaseTableVersion;

public class CtypeLogModelRootColumnColumnConfig {


    public static ColumnConfig<CtypeLogModelRootColumn, Long> id() {
        ColumnConfig<CtypeLogModelRootColumn, Long> id = new ColumnConfig<CtypeLogModelRootColumn, Long>(PropertyUtils.CtypeLogModelRootColumnProperty.id(), 200, "id");
        return id;
    }

    public static ColumnConfig<CtypeLogModelRootColumn, String> desc() {
        ColumnConfig<CtypeLogModelRootColumn, String> desc = new ColumnConfig<CtypeLogModelRootColumn, String>(PropertyUtils.CtypeLogModelRootColumnProperty.desc(), 200, "desc");
        return desc;
    }

    public static ColumnConfig<CtypeLogModelRootColumn, HbaseTableVersion> hbaseTableVersion() {
        ColumnConfig<CtypeLogModelRootColumn, HbaseTableVersion> hbaseTableVersion = new ColumnConfig<CtypeLogModelRootColumn, HbaseTableVersion>(PropertyUtils.CtypeLogModelRootColumnProperty.hbaseTableVersion(), 200, "hbaseTableVersion");
        return hbaseTableVersion;
    }

    public static ColumnConfig<CtypeLogModelRootColumn, String> name() {
        ColumnConfig<CtypeLogModelRootColumn, String> name = new ColumnConfig<CtypeLogModelRootColumn, String>(PropertyUtils.CtypeLogModelRootColumnProperty.name(), 200, "name");
        return name;
    }

    public static ColumnConfig<CtypeLogModelRootColumn, List<CtypeLogModelColumn>> columns() {
        ColumnConfig<CtypeLogModelRootColumn, List<CtypeLogModelColumn>> columns = new ColumnConfig<CtypeLogModelRootColumn, List<CtypeLogModelColumn>>(PropertyUtils.CtypeLogModelRootColumnProperty.columns(), 200, "columns");
        return columns;
    }

    public static ColumnConfig<CtypeLogModelRootColumn, CtypeLogModelGroupColumn> parent() {
        ColumnConfig<CtypeLogModelRootColumn, CtypeLogModelGroupColumn> parent = new ColumnConfig<CtypeLogModelRootColumn, CtypeLogModelGroupColumn>(PropertyUtils.CtypeLogModelRootColumnProperty.parent(), 200, "parent");
        return parent;
    }

    public static ColumnConfig<CtypeLogModelRootColumn, Integer> pos() {
        ColumnConfig<CtypeLogModelRootColumn, Integer> pos = new ColumnConfig<CtypeLogModelRootColumn, Integer>(PropertyUtils.CtypeLogModelRootColumnProperty.pos(), 200, "pos");
        return pos;
    }

    public static ColumnConfig<CtypeLogModelRootColumn, CtypeLogModelVersion> version() {
        ColumnConfig<CtypeLogModelRootColumn, CtypeLogModelVersion> version = new ColumnConfig<CtypeLogModelRootColumn, CtypeLogModelVersion>(PropertyUtils.CtypeLogModelRootColumnProperty.version(), 200, "version");
        return version;
    }

}
