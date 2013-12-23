
package com.voole.parrot.gwt.common.shared.property;

import java.util.Set;

import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.voole.parrot.shared.entity.authority.Authority;
import com.voole.parrot.shared.entity.organization.User;
import com.voole.parrot.shared.entity.organization.Leader;
import com.voole.parrot.shared.entity.organization.Member;

public interface AccountProperty
    extends PropertyAccess<User>
{


    @com.google.gwt.editor.client.Editor.Path("id")
    public ModelKeyProvider<User> key();

    public ValueProvider<User, Long> id();

    public ValueProvider<User, Member> member();

    public ValueProvider<User, Set<Authority>> authorities();

    public ValueProvider<User, String> name();

    public ValueProvider<User, Leader> leader();

    public ValueProvider<User, String> password();

}
