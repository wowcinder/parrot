
package com.voole.parrot.gwt.common.shared.property;

import java.util.List;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.voole.parrot.shared.entity.ctypelogmeta.CtypeLogModelColumn;
import com.voole.parrot.shared.entity.ctypelogmeta.CtypeLogModelGroupColumn;
import com.voole.parrot.shared.entity.ctypelogmeta.CtypeLogModelRootColumn;
import com.voole.parrot.shared.entity.ctypelogmeta.CtypeLogModelVersion;
import com.voole.parrot.shared.entity.hbasemeta.HbaseTableVersion;

public interface CtypeLogModelRootColumnProperty
    extends PropertyAccess<CtypeLogModelRootColumn>
{


    @com.google.gwt.editor.client.Editor.Path("id")
    public ModelKeyProvider<CtypeLogModelRootColumn> key();

    public ValueProvider<CtypeLogModelRootColumn, Long> id();

    public ValueProvider<CtypeLogModelRootColumn, String> desc();

    public ValueProvider<CtypeLogModelRootColumn, HbaseTableVersion> hbaseTableVersion();

    public ValueProvider<CtypeLogModelRootColumn, String> name();

    public ValueProvider<CtypeLogModelRootColumn, List<CtypeLogModelColumn>> columns();

    public ValueProvider<CtypeLogModelRootColumn, CtypeLogModelGroupColumn> parent();

    public ValueProvider<CtypeLogModelRootColumn, Integer> pos();

    public ValueProvider<CtypeLogModelRootColumn, CtypeLogModelVersion> version();

}
