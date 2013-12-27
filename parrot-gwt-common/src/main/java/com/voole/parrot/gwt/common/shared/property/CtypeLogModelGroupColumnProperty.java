
package com.voole.parrot.gwt.common.shared.property;

import java.util.List;

import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.voole.parrot.shared.entity.hbasemeta.HbaseTableVersion;
import com.voole.parrot.shared.entity.logmeta.LogModelColumn;
import com.voole.parrot.shared.entity.logmeta.LogModelGroupColumn;

public interface CtypeLogModelGroupColumnProperty
    extends PropertyAccess<LogModelGroupColumn>
{


    @com.google.gwt.editor.client.Editor.Path("id")
    public ModelKeyProvider<LogModelGroupColumn> key();

    public ValueProvider<LogModelGroupColumn, Long> id();

    public ValueProvider<LogModelGroupColumn, String> desc();

    public ValueProvider<LogModelGroupColumn, HbaseTableVersion> hbaseTableVersion();

    public ValueProvider<LogModelGroupColumn, String> name();

    public ValueProvider<LogModelGroupColumn, List<LogModelColumn>> columns();

    public ValueProvider<LogModelGroupColumn, LogModelGroupColumn> parent();

    public ValueProvider<LogModelGroupColumn, Integer> pos();

}
