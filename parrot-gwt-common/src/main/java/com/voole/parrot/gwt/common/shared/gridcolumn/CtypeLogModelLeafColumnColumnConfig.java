
package com.voole.parrot.gwt.common.shared.gridcolumn;

import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.voole.parrot.gwt.common.shared.property.PropertyUtils;
import com.voole.parrot.shared.entity.ctypelogmeta.CtypeLogModelGroupColumn;
import com.voole.parrot.shared.entity.ctypelogmeta.CtypeLogModelLeafColumn;
import com.voole.parrot.shared.entity.hbasemeta.ColumnType;
import com.voole.parrot.shared.entity.hbasemeta.HbaseTableColumn;

public class CtypeLogModelLeafColumnColumnConfig {


    public static ColumnConfig<CtypeLogModelLeafColumn, Long> id() {
        ColumnConfig<CtypeLogModelLeafColumn, Long> id = new ColumnConfig<CtypeLogModelLeafColumn, Long>(PropertyUtils.CtypeLogModelLeafColumnProperty.id(), 200, "id");
        return id;
    }

    public static ColumnConfig<CtypeLogModelLeafColumn, String> desc() {
        ColumnConfig<CtypeLogModelLeafColumn, String> desc = new ColumnConfig<CtypeLogModelLeafColumn, String>(PropertyUtils.CtypeLogModelLeafColumnProperty.desc(), 200, "desc");
        return desc;
    }

    public static ColumnConfig<CtypeLogModelLeafColumn, String> name() {
        ColumnConfig<CtypeLogModelLeafColumn, String> name = new ColumnConfig<CtypeLogModelLeafColumn, String>(PropertyUtils.CtypeLogModelLeafColumnProperty.name(), 200, "name");
        return name;
    }

    public static ColumnConfig<CtypeLogModelLeafColumn, HbaseTableColumn> hbaseTableColumn() {
        ColumnConfig<CtypeLogModelLeafColumn, HbaseTableColumn> hbaseTableColumn = new ColumnConfig<CtypeLogModelLeafColumn, HbaseTableColumn>(PropertyUtils.CtypeLogModelLeafColumnProperty.hbaseTableColumn(), 200, "hbaseTableColumn");
        return hbaseTableColumn;
    }

    public static ColumnConfig<CtypeLogModelLeafColumn, CtypeLogModelGroupColumn> parent() {
        ColumnConfig<CtypeLogModelLeafColumn, CtypeLogModelGroupColumn> parent = new ColumnConfig<CtypeLogModelLeafColumn, CtypeLogModelGroupColumn>(PropertyUtils.CtypeLogModelLeafColumnProperty.parent(), 200, "parent");
        return parent;
    }

    public static ColumnConfig<CtypeLogModelLeafColumn, ColumnType> type() {
        ColumnConfig<CtypeLogModelLeafColumn, ColumnType> type = new ColumnConfig<CtypeLogModelLeafColumn, ColumnType>(PropertyUtils.CtypeLogModelLeafColumnProperty.type(), 200, "type");
        return type;
    }

    public static ColumnConfig<CtypeLogModelLeafColumn, Integer> pos() {
        ColumnConfig<CtypeLogModelLeafColumn, Integer> pos = new ColumnConfig<CtypeLogModelLeafColumn, Integer>(PropertyUtils.CtypeLogModelLeafColumnProperty.pos(), 200, "pos");
        return pos;
    }

}
