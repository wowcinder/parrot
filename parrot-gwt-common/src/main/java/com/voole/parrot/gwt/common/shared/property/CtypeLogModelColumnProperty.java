
package com.voole.parrot.gwt.common.shared.property;

import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.voole.parrot.shared.entity.ctypelogmeta.CtypeLogModelColumn;
import com.voole.parrot.shared.entity.ctypelogmeta.CtypeLogModelGroupColumn;

public interface CtypeLogModelColumnProperty
    extends PropertyAccess<CtypeLogModelColumn>
{


    @com.google.gwt.editor.client.Editor.Path("id")
    public ModelKeyProvider<CtypeLogModelColumn> key();

    public ValueProvider<CtypeLogModelColumn, Long> id();

    public ValueProvider<CtypeLogModelColumn, String> name();

    public ValueProvider<CtypeLogModelColumn, CtypeLogModelGroupColumn> parent();

    public ValueProvider<CtypeLogModelColumn, Integer> pos();

}
