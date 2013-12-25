
package com.voole.parrot.gwt.common.shared.gridcolumn;

import java.util.List;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.voole.parrot.gwt.common.shared.property.PropertyUtils;
import com.voole.parrot.shared.entity.hbasemeta.HbaseTable;
import com.voole.parrot.shared.entity.hbasemeta.HbaseTableVersion;

public class HbaseTableColumnConfig {


    public static ColumnConfig<HbaseTable, Long> id() {
        ColumnConfig<HbaseTable, Long> id = new ColumnConfig<HbaseTable, Long>(PropertyUtils.HbaseTableProperty.id(), 200, "id");
        return id;
    }

    public static ColumnConfig<HbaseTable, List<HbaseTableVersion>> versions() {
        ColumnConfig<HbaseTable, List<HbaseTableVersion>> versions = new ColumnConfig<HbaseTable, List<HbaseTableVersion>>(PropertyUtils.HbaseTableProperty.versions(), 200, "versions");
        return versions;
    }

    public static ColumnConfig<HbaseTable, String> desc() {
        ColumnConfig<HbaseTable, String> desc = new ColumnConfig<HbaseTable, String>(PropertyUtils.HbaseTableProperty.desc(), 200, "desc");
        return desc;
    }

    public static ColumnConfig<HbaseTable, String> name() {
        ColumnConfig<HbaseTable, String> name = new ColumnConfig<HbaseTable, String>(PropertyUtils.HbaseTableProperty.name(), 200, "name");
        return name;
    }

    public static ColumnConfig<HbaseTable, String> shortname() {
        ColumnConfig<HbaseTable, String> shortname = new ColumnConfig<HbaseTable, String>(PropertyUtils.HbaseTableProperty.shortname(), 200, "shortname");
        return shortname;
    }

}
