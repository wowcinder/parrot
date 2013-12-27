
package com.voole.parrot.gwt.common.shared.property;

import java.util.List;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.voole.parrot.shared.entity.hbasemeta.HbaseTableVersion;
import com.voole.parrot.shared.entity.logmeta.LogModelColumn;
import com.voole.parrot.shared.entity.logmeta.LogModelGroupColumn;
import com.voole.parrot.shared.entity.logmeta.LogModelRootColumn;
import com.voole.parrot.shared.entity.logmeta.LogModelVersion;

public interface LogModelRootColumnProperty
    extends PropertyAccess<LogModelRootColumn>
{


    @com.google.gwt.editor.client.Editor.Path("id")
    public ModelKeyProvider<LogModelRootColumn> key();

    public ValueProvider<LogModelRootColumn, Long> id();

    public ValueProvider<LogModelRootColumn, String> desc();

    public ValueProvider<LogModelRootColumn, HbaseTableVersion> hbaseTableVersion();

    public ValueProvider<LogModelRootColumn, String> name();

    public ValueProvider<LogModelRootColumn, List<LogModelColumn>> columns();

    public ValueProvider<LogModelRootColumn, LogModelGroupColumn> parent();

    public ValueProvider<LogModelRootColumn, Integer> pos();

    public ValueProvider<LogModelRootColumn, LogModelVersion> version();

}
