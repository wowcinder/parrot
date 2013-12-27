
package com.voole.parrot.gwt.common.shared.property;

import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.voole.parrot.shared.entity.logmeta.LogModelColumn;
import com.voole.parrot.shared.entity.logmeta.LogModelGroupColumn;

public interface CtypeLogModelColumnProperty
    extends PropertyAccess<LogModelColumn>
{


    @com.google.gwt.editor.client.Editor.Path("id")
    public ModelKeyProvider<LogModelColumn> key();

    public ValueProvider<LogModelColumn, Long> id();

    public ValueProvider<LogModelColumn, String> desc();

    public ValueProvider<LogModelColumn, String> name();

    public ValueProvider<LogModelColumn, LogModelGroupColumn> parent();

    public ValueProvider<LogModelColumn, Integer> pos();

}
