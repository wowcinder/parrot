
package com.voole.parrot.gwt.common.shared.gridcolumn;

import java.util.List;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.voole.parrot.gwt.common.shared.property.PropertyUtils;
import com.voole.parrot.shared.entity.logmeta.LogModel;
import com.voole.parrot.shared.entity.logmeta.LogModelVersion;

public class LogModelColumnConfig {


    public static ColumnConfig<LogModel, Long> id() {
        ColumnConfig<LogModel, Long> id = new ColumnConfig<LogModel, Long>(PropertyUtils.LogModelProperty.id(), 200, "id");
        return id;
    }

    public static ColumnConfig<LogModel, List<LogModelVersion>> versions() {
        ColumnConfig<LogModel, List<LogModelVersion>> versions = new ColumnConfig<LogModel, List<LogModelVersion>>(PropertyUtils.LogModelProperty.versions(), 200, "versions");
        return versions;
    }

    public static ColumnConfig<LogModel, String> desc() {
        ColumnConfig<LogModel, String> desc = new ColumnConfig<LogModel, String>(PropertyUtils.LogModelProperty.desc(), 200, "desc");
        return desc;
    }

    public static ColumnConfig<LogModel, String> name() {
        ColumnConfig<LogModel, String> name = new ColumnConfig<LogModel, String>(PropertyUtils.LogModelProperty.name(), 200, "name");
        return name;
    }

}
