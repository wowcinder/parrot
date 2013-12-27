
package com.voole.parrot.gwt.common.shared.property;

import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.voole.parrot.shared.entity.logmeta.LogModel;
import com.voole.parrot.shared.entity.logmeta.LogModelRootColumn;
import com.voole.parrot.shared.entity.logmeta.LogModelVersion;

public interface CtypeLogModelVersionProperty
    extends PropertyAccess<LogModelVersion>
{


    @com.google.gwt.editor.client.Editor.Path("id")
    public ModelKeyProvider<LogModelVersion> key();

    public ValueProvider<LogModelVersion, Long> id();

    public ValueProvider<LogModelVersion, LogModel> model();

    public ValueProvider<LogModelVersion, String> desc();

    public ValueProvider<LogModelVersion, LogModelRootColumn> rootColumn();

    public ValueProvider<LogModelVersion, Integer> pos();

    public ValueProvider<LogModelVersion, String> version();

}
