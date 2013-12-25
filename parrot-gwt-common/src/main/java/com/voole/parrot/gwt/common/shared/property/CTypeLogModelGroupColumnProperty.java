
package com.voole.parrot.gwt.common.shared.property;

import java.util.List;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.voole.parrot.shared.entity.ctypelogmeta.CTypeLogModelColumn;
import com.voole.parrot.shared.entity.ctypelogmeta.CTypeLogModelGroupColumn;
import com.voole.parrot.shared.entity.hbasemeta.HbaseTableVersion;

public interface CTypeLogModelGroupColumnProperty
    extends PropertyAccess<CTypeLogModelGroupColumn>
{


    @com.google.gwt.editor.client.Editor.Path("id")
    public ModelKeyProvider<CTypeLogModelGroupColumn> key();

    public ValueProvider<CTypeLogModelGroupColumn, Long> id();

    public ValueProvider<CTypeLogModelGroupColumn, HbaseTableVersion> hbaseTableVersion();

    public ValueProvider<CTypeLogModelGroupColumn, String> name();

    public ValueProvider<CTypeLogModelGroupColumn, List<CTypeLogModelColumn>> columns();

    public ValueProvider<CTypeLogModelGroupColumn, CTypeLogModelGroupColumn> parent();

    public ValueProvider<CTypeLogModelGroupColumn, Integer> pos();

}
