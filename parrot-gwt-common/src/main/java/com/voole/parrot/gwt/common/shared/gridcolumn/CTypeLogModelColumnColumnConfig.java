
package com.voole.parrot.gwt.common.shared.gridcolumn;

import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.voole.parrot.gwt.common.shared.property.PropertyUtils;
import com.voole.parrot.shared.entity.ctypelogmeta.CTypeLogModelColumn;
import com.voole.parrot.shared.entity.ctypelogmeta.CTypeLogModelGroupColumn;

public class CTypeLogModelColumnColumnConfig {


    public static ColumnConfig<CTypeLogModelColumn, Long> id() {
        ColumnConfig<CTypeLogModelColumn, Long> id = new ColumnConfig<CTypeLogModelColumn, Long>(PropertyUtils.CTypeLogModelColumnProperty.id(), 200, "id");
        return id;
    }

    public static ColumnConfig<CTypeLogModelColumn, String> name() {
        ColumnConfig<CTypeLogModelColumn, String> name = new ColumnConfig<CTypeLogModelColumn, String>(PropertyUtils.CTypeLogModelColumnProperty.name(), 200, "name");
        return name;
    }

    public static ColumnConfig<CTypeLogModelColumn, CTypeLogModelGroupColumn> parent() {
        ColumnConfig<CTypeLogModelColumn, CTypeLogModelGroupColumn> parent = new ColumnConfig<CTypeLogModelColumn, CTypeLogModelGroupColumn>(PropertyUtils.CTypeLogModelColumnProperty.parent(), 200, "parent");
        return parent;
    }

    public static ColumnConfig<CTypeLogModelColumn, Integer> pos() {
        ColumnConfig<CTypeLogModelColumn, Integer> pos = new ColumnConfig<CTypeLogModelColumn, Integer>(PropertyUtils.CTypeLogModelColumnProperty.pos(), 200, "pos");
        return pos;
    }

}
