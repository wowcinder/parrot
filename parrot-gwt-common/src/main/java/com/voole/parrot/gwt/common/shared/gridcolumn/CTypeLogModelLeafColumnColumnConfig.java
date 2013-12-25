
package com.voole.parrot.gwt.common.shared.gridcolumn;

import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.voole.parrot.gwt.common.shared.property.PropertyUtils;
import com.voole.parrot.shared.entity.ctypelogmeta.CTypeLogModelGroupColumn;
import com.voole.parrot.shared.entity.ctypelogmeta.CTypeLogModelLeafColumn;
import com.voole.parrot.shared.entity.hbasemeta.ColumnType;
import com.voole.parrot.shared.entity.hbasemeta.HbaseTableColumn;

public class CTypeLogModelLeafColumnColumnConfig {


    public static ColumnConfig<CTypeLogModelLeafColumn, Long> id() {
        ColumnConfig<CTypeLogModelLeafColumn, Long> id = new ColumnConfig<CTypeLogModelLeafColumn, Long>(PropertyUtils.CTypeLogModelLeafColumnProperty.id(), 200, "id");
        return id;
    }

    public static ColumnConfig<CTypeLogModelLeafColumn, String> name() {
        ColumnConfig<CTypeLogModelLeafColumn, String> name = new ColumnConfig<CTypeLogModelLeafColumn, String>(PropertyUtils.CTypeLogModelLeafColumnProperty.name(), 200, "name");
        return name;
    }

    public static ColumnConfig<CTypeLogModelLeafColumn, HbaseTableColumn> hbaseTableColumn() {
        ColumnConfig<CTypeLogModelLeafColumn, HbaseTableColumn> hbaseTableColumn = new ColumnConfig<CTypeLogModelLeafColumn, HbaseTableColumn>(PropertyUtils.CTypeLogModelLeafColumnProperty.hbaseTableColumn(), 200, "hbaseTableColumn");
        return hbaseTableColumn;
    }

    public static ColumnConfig<CTypeLogModelLeafColumn, CTypeLogModelGroupColumn> parent() {
        ColumnConfig<CTypeLogModelLeafColumn, CTypeLogModelGroupColumn> parent = new ColumnConfig<CTypeLogModelLeafColumn, CTypeLogModelGroupColumn>(PropertyUtils.CTypeLogModelLeafColumnProperty.parent(), 200, "parent");
        return parent;
    }

    public static ColumnConfig<CTypeLogModelLeafColumn, ColumnType> type() {
        ColumnConfig<CTypeLogModelLeafColumn, ColumnType> type = new ColumnConfig<CTypeLogModelLeafColumn, ColumnType>(PropertyUtils.CTypeLogModelLeafColumnProperty.type(), 200, "type");
        return type;
    }

    public static ColumnConfig<CTypeLogModelLeafColumn, Integer> pos() {
        ColumnConfig<CTypeLogModelLeafColumn, Integer> pos = new ColumnConfig<CTypeLogModelLeafColumn, Integer>(PropertyUtils.CTypeLogModelLeafColumnProperty.pos(), 200, "pos");
        return pos;
    }

}
