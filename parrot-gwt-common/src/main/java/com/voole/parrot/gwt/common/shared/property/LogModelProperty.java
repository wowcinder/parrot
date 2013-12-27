
package com.voole.parrot.gwt.common.shared.property;

import java.util.List;

import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.voole.parrot.shared.entity.logmeta.LogModel;
import com.voole.parrot.shared.entity.logmeta.LogModelVersion;

public interface LogModelProperty
    extends PropertyAccess<LogModel>
{


    @com.google.gwt.editor.client.Editor.Path("id")
    public ModelKeyProvider<LogModel> key();

    public ValueProvider<LogModel, Long> id();

    public ValueProvider<LogModel, List<LogModelVersion>> versions();

    public ValueProvider<LogModel, String> desc();

    public ValueProvider<LogModel, String> name();

}
