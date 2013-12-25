
package com.voole.parrot.gwt.common.shared.gridcolumn;

import java.util.List;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.voole.parrot.gwt.common.shared.property.PropertyUtils;
import com.voole.parrot.shared.entity.ctypelogmeta.CTypeLogModelColumn;
import com.voole.parrot.shared.entity.ctypelogmeta.CTypeLogModelGroupColumn;
import com.voole.parrot.shared.entity.hbasemeta.HbaseTableVersion;

public class CTypeLogModelGroupColumnColumnConfig {


    public static ColumnConfig<CTypeLogModelGroupColumn, Long> id() {
        ColumnConfig<CTypeLogModelGroupColumn, Long> id = new ColumnConfig<CTypeLogModelGroupColumn, Long>(PropertyUtils.CTypeLogModelGroupColumnProperty.id(), 200, "id");
        return id;
    }

    public static ColumnConfig<CTypeLogModelGroupColumn, HbaseTableVersion> hbaseTableVersion() {
        ColumnConfig<CTypeLogModelGroupColumn, HbaseTableVersion> hbaseTableVersion = new ColumnConfig<CTypeLogModelGroupColumn, HbaseTableVersion>(PropertyUtils.CTypeLogModelGroupColumnProperty.hbaseTableVersion(), 200, "hbaseTableVersion");
        return hbaseTableVersion;
    }

    public static ColumnConfig<CTypeLogModelGroupColumn, String> name() {
        ColumnConfig<CTypeLogModelGroupColumn, String> name = new ColumnConfig<CTypeLogModelGroupColumn, String>(PropertyUtils.CTypeLogModelGroupColumnProperty.name(), 200, "name");
        return name;
    }

    public static ColumnConfig<CTypeLogModelGroupColumn, List<CTypeLogModelColumn>> columns() {
        ColumnConfig<CTypeLogModelGroupColumn, List<CTypeLogModelColumn>> columns = new ColumnConfig<CTypeLogModelGroupColumn, List<CTypeLogModelColumn>>(PropertyUtils.CTypeLogModelGroupColumnProperty.columns(), 200, "columns");
        return columns;
    }

    public static ColumnConfig<CTypeLogModelGroupColumn, CTypeLogModelGroupColumn> parent() {
        ColumnConfig<CTypeLogModelGroupColumn, CTypeLogModelGroupColumn> parent = new ColumnConfig<CTypeLogModelGroupColumn, CTypeLogModelGroupColumn>(PropertyUtils.CTypeLogModelGroupColumnProperty.parent(), 200, "parent");
        return parent;
    }

    public static ColumnConfig<CTypeLogModelGroupColumn, Integer> pos() {
        ColumnConfig<CTypeLogModelGroupColumn, Integer> pos = new ColumnConfig<CTypeLogModelGroupColumn, Integer>(PropertyUtils.CTypeLogModelGroupColumnProperty.pos(), 200, "pos");
        return pos;
    }

}
