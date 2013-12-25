
package com.voole.parrot.gwt.common.shared.property;

import java.util.List;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.voole.parrot.shared.entity.ctypelogmeta.CTypeLogModel;
import com.voole.parrot.shared.entity.ctypelogmeta.CTypeLogModelColumn;
import com.voole.parrot.shared.entity.ctypelogmeta.CTypeLogModelGroupColumn;
import com.voole.parrot.shared.entity.ctypelogmeta.CTypeLogModelVersion;
import com.voole.parrot.shared.entity.hbasemeta.HbaseTableVersion;

public interface CTypeLogModelVersionProperty
    extends PropertyAccess<CTypeLogModelVersion>
{


    @com.google.gwt.editor.client.Editor.Path("id")
    public ModelKeyProvider<CTypeLogModelVersion> key();

    public ValueProvider<CTypeLogModelVersion, Long> id();

    public ValueProvider<CTypeLogModelVersion, CTypeLogModel> model();

    public ValueProvider<CTypeLogModelVersion, String> desc();

    public ValueProvider<CTypeLogModelVersion, HbaseTableVersion> hbaseTableVersion();

    public ValueProvider<CTypeLogModelVersion, String> name();

    public ValueProvider<CTypeLogModelVersion, List<CTypeLogModelColumn>> columns();

    public ValueProvider<CTypeLogModelVersion, CTypeLogModelGroupColumn> parent();

    public ValueProvider<CTypeLogModelVersion, Integer> pos();

}
