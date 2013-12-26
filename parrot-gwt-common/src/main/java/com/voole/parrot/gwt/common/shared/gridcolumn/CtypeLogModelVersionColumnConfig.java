
package com.voole.parrot.gwt.common.shared.gridcolumn;

import java.util.List;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.voole.parrot.gwt.common.shared.property.PropertyUtils;
import com.voole.parrot.shared.entity.ctypelogmeta.CtypeLogModel;
import com.voole.parrot.shared.entity.ctypelogmeta.CtypeLogModelColumn;
import com.voole.parrot.shared.entity.ctypelogmeta.CtypeLogModelGroupColumn;
import com.voole.parrot.shared.entity.ctypelogmeta.CtypeLogModelVersion;
import com.voole.parrot.shared.entity.hbasemeta.HbaseTableVersion;

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

    public static ColumnConfig<CtypeLogModelVersion, HbaseTableVersion> hbaseTableVersion() {
        ColumnConfig<CtypeLogModelVersion, HbaseTableVersion> hbaseTableVersion = new ColumnConfig<CtypeLogModelVersion, HbaseTableVersion>(PropertyUtils.CtypeLogModelVersionProperty.hbaseTableVersion(), 200, "hbaseTableVersion");
        return hbaseTableVersion;
    }

    public static ColumnConfig<CtypeLogModelVersion, String> name() {
        ColumnConfig<CtypeLogModelVersion, String> name = new ColumnConfig<CtypeLogModelVersion, String>(PropertyUtils.CtypeLogModelVersionProperty.name(), 200, "name");
        return name;
    }

    public static ColumnConfig<CtypeLogModelVersion, List<CtypeLogModelColumn>> columns() {
        ColumnConfig<CtypeLogModelVersion, List<CtypeLogModelColumn>> columns = new ColumnConfig<CtypeLogModelVersion, List<CtypeLogModelColumn>>(PropertyUtils.CtypeLogModelVersionProperty.columns(), 200, "columns");
        return columns;
    }

    public static ColumnConfig<CtypeLogModelVersion, CtypeLogModelGroupColumn> parent() {
        ColumnConfig<CtypeLogModelVersion, CtypeLogModelGroupColumn> parent = new ColumnConfig<CtypeLogModelVersion, CtypeLogModelGroupColumn>(PropertyUtils.CtypeLogModelVersionProperty.parent(), 200, "parent");
        return parent;
    }

    public static ColumnConfig<CtypeLogModelVersion, Integer> pos() {
        ColumnConfig<CtypeLogModelVersion, Integer> pos = new ColumnConfig<CtypeLogModelVersion, Integer>(PropertyUtils.CtypeLogModelVersionProperty.pos(), 200, "pos");
        return pos;
    }

}
