
package com.voole.parrot.gwt.common.shared.property;

import java.util.Set;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.voole.parrot.shared.entity.account.Account;
import com.voole.parrot.shared.entity.authority.Role;
import com.voole.parrot.shared.entity.organization.Member;
import com.voole.parrot.shared.entity.organization.Organization;

public interface MemberProperty
    extends PropertyAccess<Member>
{


    public ValueProvider<Member, Organization> organization();

    public ValueProvider<Member, Account> account();

    public ValueProvider<Member, Set<Role>> roles();

    @com.google.gwt.editor.client.Editor.Path("id")
    public ModelKeyProvider<Member> key();

    public ValueProvider<Member, Long> id();

}
