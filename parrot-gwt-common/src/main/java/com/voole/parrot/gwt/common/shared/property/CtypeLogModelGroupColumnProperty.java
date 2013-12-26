
package com.voole.parrot.gwt.common.shared.property;

import java.util.List;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.voole.parrot.shared.entity.ctypelogmeta.CtypeLogModelColumn;
import com.voole.parrot.shared.entity.ctypelogmeta.CtypeLogModelGroupColumn;
import com.voole.parrot.shared.entity.hbasemeta.HbaseTableVersion;

public interface CtypeLogModelGroupColumnProperty
    extends PropertyAccess<CtypeLogModelGroupColumn>
{


    @com.google.gwt.editor.client.Editor.Path("id")
    public ModelKeyProvider<CtypeLogModelGroupColumn> key();

    public ValueProvider<CtypeLogModelGroupColumn, Long> id();

    public ValueProvider<CtypeLogModelGroupColumn, HbaseTableVersion> hbaseTableVersion();

    public ValueProvider<CtypeLogModelGroupColumn, String> name();

    public ValueProvider<CtypeLogModelGroupColumn, List<CtypeLogModelColumn>> columns();

    public ValueProvider<CtypeLogModelGroupColumn, CtypeLogModelGroupColumn> parent();

    public ValueProvider<CtypeLogModelGroupColumn, Integer> pos();

}
