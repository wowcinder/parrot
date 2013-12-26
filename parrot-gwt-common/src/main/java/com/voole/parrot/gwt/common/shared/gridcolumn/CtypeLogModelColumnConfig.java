
package com.voole.parrot.gwt.common.shared.gridcolumn;

import java.util.List;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.voole.parrot.gwt.common.shared.property.PropertyUtils;
import com.voole.parrot.shared.entity.ctypelogmeta.CtypeLogModel;
import com.voole.parrot.shared.entity.ctypelogmeta.CtypeLogModelVersion;

public class CtypeLogModelColumnConfig {


    public static ColumnConfig<CtypeLogModel, Long> id() {
        ColumnConfig<CtypeLogModel, Long> id = new ColumnConfig<CtypeLogModel, Long>(PropertyUtils.CtypeLogModelProperty.id(), 200, "id");
        return id;
    }

    public static ColumnConfig<CtypeLogModel, List<CtypeLogModelVersion>> versions() {
        ColumnConfig<CtypeLogModel, List<CtypeLogModelVersion>> versions = new ColumnConfig<CtypeLogModel, List<CtypeLogModelVersion>>(PropertyUtils.CtypeLogModelProperty.versions(), 200, "versions");
        return versions;
    }

    public static ColumnConfig<CtypeLogModel, String> desc() {
        ColumnConfig<CtypeLogModel, String> desc = new ColumnConfig<CtypeLogModel, String>(PropertyUtils.CtypeLogModelProperty.desc(), 200, "desc");
        return desc;
    }

    public static ColumnConfig<CtypeLogModel, String> name() {
        ColumnConfig<CtypeLogModel, String> name = new ColumnConfig<CtypeLogModel, String>(PropertyUtils.CtypeLogModelProperty.name(), 200, "name");
        return name;
    }

}
