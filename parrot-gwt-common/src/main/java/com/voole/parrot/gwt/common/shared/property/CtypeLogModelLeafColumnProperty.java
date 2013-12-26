
package com.voole.parrot.gwt.common.shared.property;

import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.voole.parrot.shared.entity.ctypelogmeta.CtypeLogModelGroupColumn;
import com.voole.parrot.shared.entity.ctypelogmeta.CtypeLogModelLeafColumn;
import com.voole.parrot.shared.entity.hbasemeta.ColumnType;
import com.voole.parrot.shared.entity.hbasemeta.HbaseTableColumn;

public interface CtypeLogModelLeafColumnProperty
    extends PropertyAccess<CtypeLogModelLeafColumn>
{


    @com.google.gwt.editor.client.Editor.Path("id")
    public ModelKeyProvider<CtypeLogModelLeafColumn> key();

    public ValueProvider<CtypeLogModelLeafColumn, Long> id();

    public ValueProvider<CtypeLogModelLeafColumn, String> name();

    public ValueProvider<CtypeLogModelLeafColumn, HbaseTableColumn> hbaseTableColumn();

    public ValueProvider<CtypeLogModelLeafColumn, CtypeLogModelGroupColumn> parent();

    public ValueProvider<CtypeLogModelLeafColumn, ColumnType> type();

    public ValueProvider<CtypeLogModelLeafColumn, Integer> pos();

}
