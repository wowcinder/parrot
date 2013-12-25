
package com.voole.parrot.gwt.common.shared.property;

import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.voole.parrot.shared.entity.ctypelogmeta.CTypeLogModelColumn;
import com.voole.parrot.shared.entity.ctypelogmeta.CTypeLogModelGroupColumn;

public interface CTypeLogModelColumnProperty
    extends PropertyAccess<CTypeLogModelColumn>
{


    @com.google.gwt.editor.client.Editor.Path("id")
    public ModelKeyProvider<CTypeLogModelColumn> key();

    public ValueProvider<CTypeLogModelColumn, Long> id();

    public ValueProvider<CTypeLogModelColumn, String> name();

    public ValueProvider<CTypeLogModelColumn, CTypeLogModelGroupColumn> parent();

    public ValueProvider<CTypeLogModelColumn, Integer> pos();

}
