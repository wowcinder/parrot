
package com.voole.parrot.gwt.common.shared.gridcolumn;

import java.util.List;

import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.voole.parrot.gwt.common.shared.property.PropertyUtils;
import com.voole.parrot.shared.entity.hbasemeta.ColumnType;
import com.voole.parrot.shared.entity.hbasemeta.HbaseTableColumn;
import com.voole.parrot.shared.entity.hbasemeta.HbaseTableVersion;
import com.voole.parrot.shared.entity.logmeta.LogModelLeafColumn;

public class HbaseTableColumnColumnConfig {


    public static ColumnConfig<HbaseTableColumn, Long> id() {
        ColumnConfig<HbaseTableColumn, Long> id = new ColumnConfig<HbaseTableColumn, Long>(PropertyUtils.HbaseTableColumnProperty.id(), 200, "id");
        return id;
    }

    public static ColumnConfig<HbaseTableColumn, String> desc() {
        ColumnConfig<HbaseTableColumn, String> desc = new ColumnConfig<HbaseTableColumn, String>(PropertyUtils.HbaseTableColumnProperty.desc(), 200, "desc");
        return desc;
    }

    public static ColumnConfig<HbaseTableColumn, List<LogModelLeafColumn>> logModelLeafColumns() {
        ColumnConfig<HbaseTableColumn, List<LogModelLeafColumn>> ctypeLogModelLeafColumns = new ColumnConfig<HbaseTableColumn, List<LogModelLeafColumn>>(PropertyUtils.HbaseTableColumnProperty.logModelLeafColumns(), 200, "ctypeLogModelLeafColumns");
        return ctypeLogModelLeafColumns;
    }

    public static ColumnConfig<HbaseTableColumn, String> name() {
        ColumnConfig<HbaseTableColumn, String> name = new ColumnConfig<HbaseTableColumn, String>(PropertyUtils.HbaseTableColumnProperty.name(), 200, "name");
        return name;
    }

    public static ColumnConfig<HbaseTableColumn, String> shortname() {
        ColumnConfig<HbaseTableColumn, String> shortname = new ColumnConfig<HbaseTableColumn, String>(PropertyUtils.HbaseTableColumnProperty.shortname(), 200, "shortname");
        return shortname;
    }

    public static ColumnConfig<HbaseTableColumn, ColumnType> type() {
        ColumnConfig<HbaseTableColumn, ColumnType> type = new ColumnConfig<HbaseTableColumn, ColumnType>(PropertyUtils.HbaseTableColumnProperty.type(), 200, "type");
        return type;
    }

    public static ColumnConfig<HbaseTableColumn, Integer> pos() {
        ColumnConfig<HbaseTableColumn, Integer> pos = new ColumnConfig<HbaseTableColumn, Integer>(PropertyUtils.HbaseTableColumnProperty.pos(), 200, "pos");
        return pos;
    }

    public static ColumnConfig<HbaseTableColumn, HbaseTableVersion> version() {
        ColumnConfig<HbaseTableColumn, HbaseTableVersion> version = new ColumnConfig<HbaseTableColumn, HbaseTableVersion>(PropertyUtils.HbaseTableColumnProperty.version(), 200, "version");
        return version;
    }

}
