
package com.voole.parrot.gwt.common.shared.property;

import java.util.List;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.voole.parrot.shared.entity.authority.Authority;
import com.voole.parrot.shared.entity.authority.AuthorityEntrance;

public interface AuthorityEntranceProperty
    extends PropertyAccess<AuthorityEntrance>
{


    @com.google.gwt.editor.client.Editor.Path("id")
    public ModelKeyProvider<AuthorityEntrance> key();

    public ValueProvider<AuthorityEntrance, Long> id();

    public ValueProvider<AuthorityEntrance, List<Authority>> authorities();

    public ValueProvider<AuthorityEntrance, String> name();

}
