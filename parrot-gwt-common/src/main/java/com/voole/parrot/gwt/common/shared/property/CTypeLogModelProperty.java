
package com.voole.parrot.gwt.common.shared.property;

import java.util.List;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.voole.parrot.shared.entity.ctypelogmeta.CTypeLogModel;
import com.voole.parrot.shared.entity.ctypelogmeta.CTypeLogModelVersion;

public interface CTypeLogModelProperty
    extends PropertyAccess<CTypeLogModel>
{


    @com.google.gwt.editor.client.Editor.Path("id")
    public ModelKeyProvider<CTypeLogModel> key();

    public ValueProvider<CTypeLogModel, Long> id();

    public ValueProvider<CTypeLogModel, List<CTypeLogModelVersion>> versions();

    public ValueProvider<CTypeLogModel, String> desc();

    public ValueProvider<CTypeLogModel, String> name();

}
