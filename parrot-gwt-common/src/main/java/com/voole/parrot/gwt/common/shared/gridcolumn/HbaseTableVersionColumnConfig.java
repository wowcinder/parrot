
package com.voole.parrot.gwt.common.shared.gridcolumn;

import java.util.List;

import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.voole.parrot.gwt.common.shared.property.PropertyUtils;
import com.voole.parrot.shared.entity.hbasemeta.HbaseTable;
import com.voole.parrot.shared.entity.hbasemeta.HbaseTableColumn;
import com.voole.parrot.shared.entity.hbasemeta.HbaseTableVersion;
import com.voole.parrot.shared.entity.logmeta.LogModelGroupColumn;

public class HbaseTableVersionColumnConfig {


    public static ColumnConfig<HbaseTableVersion, Long> id() {
        ColumnConfig<HbaseTableVersion, Long> id = new ColumnConfig<HbaseTableVersion, Long>(PropertyUtils.HbaseTableVersionProperty.id(), 200, "id");
        return id;
    }

    public static ColumnConfig<HbaseTableVersion, String> desc() {
        ColumnConfig<HbaseTableVersion, String> desc = new ColumnConfig<HbaseTableVersion, String>(PropertyUtils.HbaseTableVersionProperty.desc(), 200, "desc");
        return desc;
    }

    public static ColumnConfig<HbaseTableVersion, List<LogModelGroupColumn>> logModelGroupColumns() {
        ColumnConfig<HbaseTableVersion, List<LogModelGroupColumn>> ctypeLogModelGroupColumns = new ColumnConfig<HbaseTableVersion, List<LogModelGroupColumn>>(PropertyUtils.HbaseTableVersionProperty.logModelGroupColumns(), 200, "ctypeLogModelGroupColumns");
        return ctypeLogModelGroupColumns;
    }

    public static ColumnConfig<HbaseTableVersion, List<HbaseTableColumn>> columns() {
        ColumnConfig<HbaseTableVersion, List<HbaseTableColumn>> columns = new ColumnConfig<HbaseTableVersion, List<HbaseTableColumn>>(PropertyUtils.HbaseTableVersionProperty.columns(), 200, "columns");
        return columns;
    }

    public static ColumnConfig<HbaseTableVersion, HbaseTable> table() {
        ColumnConfig<HbaseTableVersion, HbaseTable> table = new ColumnConfig<HbaseTableVersion, HbaseTable>(PropertyUtils.HbaseTableVersionProperty.table(), 200, "table");
        return table;
    }

    public static ColumnConfig<HbaseTableVersion, Integer> pos() {
        ColumnConfig<HbaseTableVersion, Integer> pos = new ColumnConfig<HbaseTableVersion, Integer>(PropertyUtils.HbaseTableVersionProperty.pos(), 200, "pos");
        return pos;
    }

    public static ColumnConfig<HbaseTableVersion, String> version() {
        ColumnConfig<HbaseTableVersion, String> version = new ColumnConfig<HbaseTableVersion, String>(PropertyUtils.HbaseTableVersionProperty.version(), 200, "version");
        return version;
    }

}
