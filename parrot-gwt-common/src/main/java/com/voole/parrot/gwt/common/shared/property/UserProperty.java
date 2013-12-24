
package com.voole.parrot.gwt.common.shared.property;

import java.util.Set;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.voole.parrot.shared.entity.authority.Authority;
import com.voole.parrot.shared.entity.authority.Role;
import com.voole.parrot.shared.entity.user.User;

public interface UserProperty
    extends PropertyAccess<User>
{


    @com.google.gwt.editor.client.Editor.Path("id")
    public ModelKeyProvider<User> key();

    public ValueProvider<User, Long> id();

    public ValueProvider<User, Set<Authority>> authorities();

    public ValueProvider<User, String> name();

    public ValueProvider<User, Set<Role>> roles();

    public ValueProvider<User, String> password();

}
