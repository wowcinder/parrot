
package com.voole.parrot.gwt.common.shared.gridcolumn;

import java.util.List;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.voole.parrot.gwt.common.shared.property.PropertyUtils;
import com.voole.parrot.shared.entity.ctypelogmeta.CTypeLogModel;
import com.voole.parrot.shared.entity.ctypelogmeta.CTypeLogModelColumn;
import com.voole.parrot.shared.entity.ctypelogmeta.CTypeLogModelGroupColumn;
import com.voole.parrot.shared.entity.ctypelogmeta.CTypeLogModelVersion;
import com.voole.parrot.shared.entity.hbasemeta.HbaseTableVersion;

public class CTypeLogModelVersionColumnConfig {


    public static ColumnConfig<CTypeLogModelVersion, Long> id() {
        ColumnConfig<CTypeLogModelVersion, Long> id = new ColumnConfig<CTypeLogModelVersion, Long>(PropertyUtils.CTypeLogModelVersionProperty.id(), 200, "id");
        return id;
    }

    public static ColumnConfig<CTypeLogModelVersion, CTypeLogModel> model() {
        ColumnConfig<CTypeLogModelVersion, CTypeLogModel> model = new ColumnConfig<CTypeLogModelVersion, CTypeLogModel>(PropertyUtils.CTypeLogModelVersionProperty.model(), 200, "model");
        return model;
    }

    public static ColumnConfig<CTypeLogModelVersion, String> desc() {
        ColumnConfig<CTypeLogModelVersion, String> desc = new ColumnConfig<CTypeLogModelVersion, String>(PropertyUtils.CTypeLogModelVersionProperty.desc(), 200, "desc");
        return desc;
    }

    public static ColumnConfig<CTypeLogModelVersion, HbaseTableVersion> hbaseTableVersion() {
        ColumnConfig<CTypeLogModelVersion, HbaseTableVersion> hbaseTableVersion = new ColumnConfig<CTypeLogModelVersion, HbaseTableVersion>(PropertyUtils.CTypeLogModelVersionProperty.hbaseTableVersion(), 200, "hbaseTableVersion");
        return hbaseTableVersion;
    }

    public static ColumnConfig<CTypeLogModelVersion, String> name() {
        ColumnConfig<CTypeLogModelVersion, String> name = new ColumnConfig<CTypeLogModelVersion, String>(PropertyUtils.CTypeLogModelVersionProperty.name(), 200, "name");
        return name;
    }

    public static ColumnConfig<CTypeLogModelVersion, List<CTypeLogModelColumn>> columns() {
        ColumnConfig<CTypeLogModelVersion, List<CTypeLogModelColumn>> columns = new ColumnConfig<CTypeLogModelVersion, List<CTypeLogModelColumn>>(PropertyUtils.CTypeLogModelVersionProperty.columns(), 200, "columns");
        return columns;
    }

    public static ColumnConfig<CTypeLogModelVersion, CTypeLogModelGroupColumn> parent() {
        ColumnConfig<CTypeLogModelVersion, CTypeLogModelGroupColumn> parent = new ColumnConfig<CTypeLogModelVersion, CTypeLogModelGroupColumn>(PropertyUtils.CTypeLogModelVersionProperty.parent(), 200, "parent");
        return parent;
    }

    public static ColumnConfig<CTypeLogModelVersion, Integer> pos() {
        ColumnConfig<CTypeLogModelVersion, Integer> pos = new ColumnConfig<CTypeLogModelVersion, Integer>(PropertyUtils.CTypeLogModelVersionProperty.pos(), 200, "pos");
        return pos;
    }

}
