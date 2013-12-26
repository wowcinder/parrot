
package com.voole.parrot.gwt.common.shared.property;

import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.voole.parrot.shared.entity.ctypelogmeta.CtypeLogModel;
import com.voole.parrot.shared.entity.ctypelogmeta.CtypeLogModelRootColumn;
import com.voole.parrot.shared.entity.ctypelogmeta.CtypeLogModelVersion;

public interface CtypeLogModelVersionProperty
    extends PropertyAccess<CtypeLogModelVersion>
{


    @com.google.gwt.editor.client.Editor.Path("id")
    public ModelKeyProvider<CtypeLogModelVersion> key();

    public ValueProvider<CtypeLogModelVersion, Long> id();

    public ValueProvider<CtypeLogModelVersion, CtypeLogModel> model();

    public ValueProvider<CtypeLogModelVersion, String> desc();

    public ValueProvider<CtypeLogModelVersion, CtypeLogModelRootColumn> rootColumn();

    public ValueProvider<CtypeLogModelVersion, Integer> pos();

    public ValueProvider<CtypeLogModelVersion, String> version();

}
