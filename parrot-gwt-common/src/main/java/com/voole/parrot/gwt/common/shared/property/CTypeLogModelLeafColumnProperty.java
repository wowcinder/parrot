
package com.voole.parrot.gwt.common.shared.property;

import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.voole.parrot.shared.entity.ctypelogmeta.CTypeLogModelGroupColumn;
import com.voole.parrot.shared.entity.ctypelogmeta.CTypeLogModelLeafColumn;
import com.voole.parrot.shared.entity.hbasemeta.ColumnType;
import com.voole.parrot.shared.entity.hbasemeta.HbaseTableColumn;

public interface CTypeLogModelLeafColumnProperty
    extends PropertyAccess<CTypeLogModelLeafColumn>
{


    @com.google.gwt.editor.client.Editor.Path("id")
    public ModelKeyProvider<CTypeLogModelLeafColumn> key();

    public ValueProvider<CTypeLogModelLeafColumn, Long> id();

    public ValueProvider<CTypeLogModelLeafColumn, String> name();

    public ValueProvider<CTypeLogModelLeafColumn, HbaseTableColumn> hbaseTableColumn();

    public ValueProvider<CTypeLogModelLeafColumn, CTypeLogModelGroupColumn> parent();

    public ValueProvider<CTypeLogModelLeafColumn, ColumnType> type();

    public ValueProvider<CTypeLogModelLeafColumn, Integer> pos();

}
