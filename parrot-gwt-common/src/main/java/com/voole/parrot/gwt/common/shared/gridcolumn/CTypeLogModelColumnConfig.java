
package com.voole.parrot.gwt.common.shared.gridcolumn;

import java.util.List;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.voole.parrot.gwt.common.shared.property.PropertyUtils;
import com.voole.parrot.shared.entity.ctypelogmeta.CTypeLogModel;
import com.voole.parrot.shared.entity.ctypelogmeta.CTypeLogModelVersion;

public class CTypeLogModelColumnConfig {


    public static ColumnConfig<CTypeLogModel, Long> id() {
        ColumnConfig<CTypeLogModel, Long> id = new ColumnConfig<CTypeLogModel, Long>(PropertyUtils.CTypeLogModelProperty.id(), 200, "id");
        return id;
    }

    public static ColumnConfig<CTypeLogModel, List<CTypeLogModelVersion>> versions() {
        ColumnConfig<CTypeLogModel, List<CTypeLogModelVersion>> versions = new ColumnConfig<CTypeLogModel, List<CTypeLogModelVersion>>(PropertyUtils.CTypeLogModelProperty.versions(), 200, "versions");
        return versions;
    }

    public static ColumnConfig<CTypeLogModel, String> desc() {
        ColumnConfig<CTypeLogModel, String> desc = new ColumnConfig<CTypeLogModel, String>(PropertyUtils.CTypeLogModelProperty.desc(), 200, "desc");
        return desc;
    }

    public static ColumnConfig<CTypeLogModel, String> name() {
        ColumnConfig<CTypeLogModel, String> name = new ColumnConfig<CTypeLogModel, String>(PropertyUtils.CTypeLogModelProperty.name(), 200, "name");
        return name;
    }

}
