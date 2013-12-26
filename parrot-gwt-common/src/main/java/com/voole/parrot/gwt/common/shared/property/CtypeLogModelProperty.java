
package com.voole.parrot.gwt.common.shared.property;

import java.util.List;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.voole.parrot.shared.entity.ctypelogmeta.CtypeLogModel;
import com.voole.parrot.shared.entity.ctypelogmeta.CtypeLogModelVersion;

public interface CtypeLogModelProperty
    extends PropertyAccess<CtypeLogModel>
{


    @com.google.gwt.editor.client.Editor.Path("id")
    public ModelKeyProvider<CtypeLogModel> key();

    public ValueProvider<CtypeLogModel, Long> id();

    public ValueProvider<CtypeLogModel, List<CtypeLogModelVersion>> versions();

    public ValueProvider<CtypeLogModel, String> desc();

    public ValueProvider<CtypeLogModel, String> name();

}
