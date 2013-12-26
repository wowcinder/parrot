
package com.voole.parrot.gwt.common.shared.property;

import java.util.List;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.voole.parrot.shared.entity.ctypelogmeta.CtypeLogModel;
import com.voole.parrot.shared.entity.ctypelogmeta.CtypeLogModelColumn;
import com.voole.parrot.shared.entity.ctypelogmeta.CtypeLogModelGroupColumn;
import com.voole.parrot.shared.entity.ctypelogmeta.CtypeLogModelVersion;
import com.voole.parrot.shared.entity.hbasemeta.HbaseTableVersion;

public interface CtypeLogModelVersionProperty
    extends PropertyAccess<CtypeLogModelVersion>
{


    @com.google.gwt.editor.client.Editor.Path("id")
    public ModelKeyProvider<CtypeLogModelVersion> key();

    public ValueProvider<CtypeLogModelVersion, Long> id();

    public ValueProvider<CtypeLogModelVersion, CtypeLogModel> model();

    public ValueProvider<CtypeLogModelVersion, String> desc();

    public ValueProvider<CtypeLogModelVersion, HbaseTableVersion> hbaseTableVersion();

    public ValueProvider<CtypeLogModelVersion, String> name();

    public ValueProvider<CtypeLogModelVersion, List<CtypeLogModelColumn>> columns();

    public ValueProvider<CtypeLogModelVersion, CtypeLogModelGroupColumn> parent();

    public ValueProvider<CtypeLogModelVersion, Integer> pos();

}
