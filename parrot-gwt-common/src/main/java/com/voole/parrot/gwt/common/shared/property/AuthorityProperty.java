
package com.voole.parrot.gwt.common.shared.property;

import java.util.Set;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.voole.parrot.shared.entity.authority.Authority;
import com.voole.parrot.shared.entity.authority.AuthorityEntrance;
import com.voole.parrot.shared.entity.authority.Role;
import com.voole.parrot.shared.entity.user.User;

public interface AuthorityProperty
    extends PropertyAccess<Authority>
{


    @com.google.gwt.editor.client.Editor.Path("id")
    public ModelKeyProvider<Authority> key();

    public ValueProvider<Authority, Long> id();

    public ValueProvider<Authority, Set<User>> users();

    public ValueProvider<Authority, Set<Authority>> dependencies();

    public ValueProvider<Authority, String> token();

    public ValueProvider<Authority, Set<Role>> roles();

    public ValueProvider<Authority, String> name();

    public ValueProvider<Authority, Set<Authority>> reDependencies();

    public ValueProvider<Authority, AuthorityEntrance> entrance();

    public ValueProvider<Authority, Integer> pos();

    @com.google.gwt.editor.client.Editor.Path("entrance.name")
    public ValueProvider<Authority, String> entrance_name();

}
