
package com.voole.parrot.gwt.common.shared.gridcolumn;

import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.voole.parrot.gwt.common.shared.property.PropertyUtils;
import com.voole.parrot.shared.entity.ctypelogmeta.CtypeLogModelColumn;
import com.voole.parrot.shared.entity.ctypelogmeta.CtypeLogModelGroupColumn;

public class CtypeLogModelColumnColumnConfig {


    public static ColumnConfig<CtypeLogModelColumn, Long> id() {
        ColumnConfig<CtypeLogModelColumn, Long> id = new ColumnConfig<CtypeLogModelColumn, Long>(PropertyUtils.CtypeLogModelColumnProperty.id(), 200, "id");
        return id;
    }

    public static ColumnConfig<CtypeLogModelColumn, String> desc() {
        ColumnConfig<CtypeLogModelColumn, String> desc = new ColumnConfig<CtypeLogModelColumn, String>(PropertyUtils.CtypeLogModelColumnProperty.desc(), 200, "desc");
        return desc;
    }

    public static ColumnConfig<CtypeLogModelColumn, String> name() {
        ColumnConfig<CtypeLogModelColumn, String> name = new ColumnConfig<CtypeLogModelColumn, String>(PropertyUtils.CtypeLogModelColumnProperty.name(), 200, "name");
        return name;
    }

    public static ColumnConfig<CtypeLogModelColumn, CtypeLogModelGroupColumn> parent() {
        ColumnConfig<CtypeLogModelColumn, CtypeLogModelGroupColumn> parent = new ColumnConfig<CtypeLogModelColumn, CtypeLogModelGroupColumn>(PropertyUtils.CtypeLogModelColumnProperty.parent(), 200, "parent");
        return parent;
    }

    public static ColumnConfig<CtypeLogModelColumn, Integer> pos() {
        ColumnConfig<CtypeLogModelColumn, Integer> pos = new ColumnConfig<CtypeLogModelColumn, Integer>(PropertyUtils.CtypeLogModelColumnProperty.pos(), 200, "pos");
        return pos;
    }

}
