
package com.voole.parrot.gwt.common.shared.gridcolumn;

import java.util.List;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.voole.parrot.gwt.common.shared.property.PropertyUtils;
import com.voole.parrot.shared.entity.ctypelogmeta.CtypeLogModelColumn;
import com.voole.parrot.shared.entity.ctypelogmeta.CtypeLogModelGroupColumn;
import com.voole.parrot.shared.entity.hbasemeta.HbaseTableVersion;

public class CtypeLogModelGroupColumnColumnConfig {


    public static ColumnConfig<CtypeLogModelGroupColumn, Long> id() {
        ColumnConfig<CtypeLogModelGroupColumn, Long> id = new ColumnConfig<CtypeLogModelGroupColumn, Long>(PropertyUtils.CtypeLogModelGroupColumnProperty.id(), 200, "id");
        return id;
    }

    public static ColumnConfig<CtypeLogModelGroupColumn, HbaseTableVersion> hbaseTableVersion() {
        ColumnConfig<CtypeLogModelGroupColumn, HbaseTableVersion> hbaseTableVersion = new ColumnConfig<CtypeLogModelGroupColumn, HbaseTableVersion>(PropertyUtils.CtypeLogModelGroupColumnProperty.hbaseTableVersion(), 200, "hbaseTableVersion");
        return hbaseTableVersion;
    }

    public static ColumnConfig<CtypeLogModelGroupColumn, String> name() {
        ColumnConfig<CtypeLogModelGroupColumn, String> name = new ColumnConfig<CtypeLogModelGroupColumn, String>(PropertyUtils.CtypeLogModelGroupColumnProperty.name(), 200, "name");
        return name;
    }

    public static ColumnConfig<CtypeLogModelGroupColumn, List<CtypeLogModelColumn>> columns() {
        ColumnConfig<CtypeLogModelGroupColumn, List<CtypeLogModelColumn>> columns = new ColumnConfig<CtypeLogModelGroupColumn, List<CtypeLogModelColumn>>(PropertyUtils.CtypeLogModelGroupColumnProperty.columns(), 200, "columns");
        return columns;
    }

    public static ColumnConfig<CtypeLogModelGroupColumn, CtypeLogModelGroupColumn> parent() {
        ColumnConfig<CtypeLogModelGroupColumn, CtypeLogModelGroupColumn> parent = new ColumnConfig<CtypeLogModelGroupColumn, CtypeLogModelGroupColumn>(PropertyUtils.CtypeLogModelGroupColumnProperty.parent(), 200, "parent");
        return parent;
    }

    public static ColumnConfig<CtypeLogModelGroupColumn, Integer> pos() {
        ColumnConfig<CtypeLogModelGroupColumn, Integer> pos = new ColumnConfig<CtypeLogModelGroupColumn, Integer>(PropertyUtils.CtypeLogModelGroupColumnProperty.pos(), 200, "pos");
        return pos;
    }

}
