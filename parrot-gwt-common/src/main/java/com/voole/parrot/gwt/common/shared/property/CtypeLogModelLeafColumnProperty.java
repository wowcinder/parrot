
package com.voole.parrot.gwt.common.shared.property;

import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.voole.parrot.shared.entity.hbasemeta.ColumnType;
import com.voole.parrot.shared.entity.hbasemeta.HbaseTableColumn;
import com.voole.parrot.shared.entity.logmeta.LogModelGroupColumn;
import com.voole.parrot.shared.entity.logmeta.LogModelLeafColumn;

public interface CtypeLogModelLeafColumnProperty
    extends PropertyAccess<LogModelLeafColumn>
{


    @com.google.gwt.editor.client.Editor.Path("id")
    public ModelKeyProvider<LogModelLeafColumn> key();

    public ValueProvider<LogModelLeafColumn, Long> id();

    public ValueProvider<LogModelLeafColumn, String> desc();

    public ValueProvider<LogModelLeafColumn, String> name();

    public ValueProvider<LogModelLeafColumn, HbaseTableColumn> hbaseTableColumn();

    public ValueProvider<LogModelLeafColumn, LogModelGroupColumn> parent();

    public ValueProvider<LogModelLeafColumn, ColumnType> type();

    public ValueProvider<LogModelLeafColumn, Integer> pos();

}
