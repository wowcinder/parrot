
package com.voole.parrot.gwt.common.shared.gridcolumn;

import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.voole.parrot.gwt.common.shared.property.PropertyUtils;
import com.voole.parrot.shared.entity.ctypelogmeta.CtypeLogModel;
import com.voole.parrot.shared.entity.ctypelogmeta.CtypeLogModelRootColumn;
import com.voole.parrot.shared.entity.ctypelogmeta.CtypeLogModelVersion;

public class CtypeLogModelVersionColumnConfig {


    public static ColumnConfig<CtypeLogModelVersion, Long> id() {
        ColumnConfig<CtypeLogModelVersion, Long> id = new ColumnConfig<CtypeLogModelVersion, Long>(PropertyUtils.CtypeLogModelVersionProperty.id(), 200, "id");
        return id;
    }

    public static ColumnConfig<CtypeLogModelVersion, CtypeLogModel> model() {
        ColumnConfig<CtypeLogModelVersion, CtypeLogModel> model = new ColumnConfig<CtypeLogModelVersion, CtypeLogModel>(PropertyUtils.CtypeLogModelVersionProperty.model(), 200, "model");
        return model;
    }

    public static ColumnConfig<CtypeLogModelVersion, String> desc() {
        ColumnConfig<CtypeLogModelVersion, String> desc = new ColumnConfig<CtypeLogModelVersion, String>(PropertyUtils.CtypeLogModelVersionProperty.desc(), 200, "desc");
        return desc;
    }

    public static ColumnConfig<CtypeLogModelVersion, CtypeLogModelRootColumn> rootColumn() {
        ColumnConfig<CtypeLogModelVersion, CtypeLogModelRootColumn> rootColumn = new ColumnConfig<CtypeLogModelVersion, CtypeLogModelRootColumn>(PropertyUtils.CtypeLogModelVersionProperty.rootColumn(), 200, "rootColumn");
        return rootColumn;
    }

    public static ColumnConfig<CtypeLogModelVersion, Integer> pos() {
        ColumnConfig<CtypeLogModelVersion, Integer> pos = new ColumnConfig<CtypeLogModelVersion, Integer>(PropertyUtils.CtypeLogModelVersionProperty.pos(), 200, "pos");
        return pos;
    }

    public static ColumnConfig<CtypeLogModelVersion, String> version() {
        ColumnConfig<CtypeLogModelVersion, String> version = new ColumnConfig<CtypeLogModelVersion, String>(PropertyUtils.CtypeLogModelVersionProperty.version(), 200, "version");
        return version;
    }

}
