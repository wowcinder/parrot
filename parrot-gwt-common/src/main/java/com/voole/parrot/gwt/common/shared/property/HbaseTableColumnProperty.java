
package com.voole.parrot.gwt.common.shared.property;

import java.util.List;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.voole.parrot.shared.entity.ctypelogmeta.CTypeLogModelLeafColumn;
import com.voole.parrot.shared.entity.hbasemeta.ColumnType;
import com.voole.parrot.shared.entity.hbasemeta.HbaseTableColumn;
import com.voole.parrot.shared.entity.hbasemeta.HbaseTableVersion;

public interface HbaseTableColumnProperty
    extends PropertyAccess<HbaseTableColumn>
{


    @com.google.gwt.editor.client.Editor.Path("id")
    public ModelKeyProvider<HbaseTableColumn> key();

    public ValueProvider<HbaseTableColumn, Long> id();

    public ValueProvider<HbaseTableColumn, String> desc();

    public ValueProvider<HbaseTableColumn, List<CTypeLogModelLeafColumn>> ctypeLogModelLeafColumns();

    public ValueProvider<HbaseTableColumn, String> name();

    public ValueProvider<HbaseTableColumn, String> shortname();

    public ValueProvider<HbaseTableColumn, ColumnType> type();

    public ValueProvider<HbaseTableColumn, Integer> pos();

    public ValueProvider<HbaseTableColumn, HbaseTableVersion> version();

}
