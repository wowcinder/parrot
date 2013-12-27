
package com.voole.parrot.gwt.common.shared.property;

import java.util.List;

import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.voole.parrot.shared.entity.hbasemeta.HbaseTable;
import com.voole.parrot.shared.entity.hbasemeta.HbaseTableColumn;
import com.voole.parrot.shared.entity.hbasemeta.HbaseTableVersion;
import com.voole.parrot.shared.entity.logmeta.LogModelGroupColumn;

public interface HbaseTableVersionProperty
    extends PropertyAccess<HbaseTableVersion>
{


    @com.google.gwt.editor.client.Editor.Path("id")
    public ModelKeyProvider<HbaseTableVersion> key();

    public ValueProvider<HbaseTableVersion, Long> id();

    public ValueProvider<HbaseTableVersion, String> desc();

    public ValueProvider<HbaseTableVersion, List<LogModelGroupColumn>> ctypeLogModelGroupColumns();

    public ValueProvider<HbaseTableVersion, List<HbaseTableColumn>> columns();

    public ValueProvider<HbaseTableVersion, HbaseTable> table();

    public ValueProvider<HbaseTableVersion, Integer> pos();

    public ValueProvider<HbaseTableVersion, String> version();

}
