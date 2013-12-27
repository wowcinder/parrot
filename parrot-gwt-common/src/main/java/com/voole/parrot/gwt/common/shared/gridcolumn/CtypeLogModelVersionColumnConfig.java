
package com.voole.parrot.gwt.common.shared.gridcolumn;

import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.voole.parrot.gwt.common.shared.property.PropertyUtils;
import com.voole.parrot.shared.entity.logmeta.LogModel;
import com.voole.parrot.shared.entity.logmeta.LogModelRootColumn;
import com.voole.parrot.shared.entity.logmeta.LogModelVersion;

public class CtypeLogModelVersionColumnConfig {


    public static ColumnConfig<LogModelVersion, Long> id() {
        ColumnConfig<LogModelVersion, Long> id = new ColumnConfig<LogModelVersion, Long>(PropertyUtils.CtypeLogModelVersionProperty.id(), 200, "id");
        return id;
    }

    public static ColumnConfig<LogModelVersion, LogModel> model() {
        ColumnConfig<LogModelVersion, LogModel> model = new ColumnConfig<LogModelVersion, LogModel>(PropertyUtils.CtypeLogModelVersionProperty.model(), 200, "model");
        return model;
    }

    public static ColumnConfig<LogModelVersion, String> desc() {
        ColumnConfig<LogModelVersion, String> desc = new ColumnConfig<LogModelVersion, String>(PropertyUtils.CtypeLogModelVersionProperty.desc(), 200, "desc");
        return desc;
    }

    public static ColumnConfig<LogModelVersion, LogModelRootColumn> rootColumn() {
        ColumnConfig<LogModelVersion, LogModelRootColumn> rootColumn = new ColumnConfig<LogModelVersion, LogModelRootColumn>(PropertyUtils.CtypeLogModelVersionProperty.rootColumn(), 200, "rootColumn");
        return rootColumn;
    }

    public static ColumnConfig<LogModelVersion, Integer> pos() {
        ColumnConfig<LogModelVersion, Integer> pos = new ColumnConfig<LogModelVersion, Integer>(PropertyUtils.CtypeLogModelVersionProperty.pos(), 200, "pos");
        return pos;
    }

    public static ColumnConfig<LogModelVersion, String> version() {
        ColumnConfig<LogModelVersion, String> version = new ColumnConfig<LogModelVersion, String>(PropertyUtils.CtypeLogModelVersionProperty.version(), 200, "version");
        return version;
    }

}
