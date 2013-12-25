
package com.voole.parrot.gwt.common.shared.property;

import java.util.List;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.voole.parrot.shared.entity.hbasemeta.HbaseTable;
import com.voole.parrot.shared.entity.hbasemeta.HbaseTableVersion;

public interface HbaseTableProperty
    extends PropertyAccess<HbaseTable>
{


    @com.google.gwt.editor.client.Editor.Path("id")
    public ModelKeyProvider<HbaseTable> key();

    public ValueProvider<HbaseTable, Long> id();

    public ValueProvider<HbaseTable, List<HbaseTableVersion>> versions();

    public ValueProvider<HbaseTable, String> desc();

    public ValueProvider<HbaseTable, String> name();

    public ValueProvider<HbaseTable, String> shortname();

}
