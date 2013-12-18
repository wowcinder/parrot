
package com.voole.parrot.gwt.common.shared.property;

import java.util.Set;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.voole.parrot.shared.entity.account.Account;
import com.voole.parrot.shared.entity.authority.Authority;
import com.voole.parrot.shared.entity.organization.Leader;
import com.voole.parrot.shared.entity.organization.Member;

public interface AccountProperty
    extends PropertyAccess<Account>
{


    public ValueProvider<Account, Leader> leader();

    public ValueProvider<Account, Member> member();

    public ValueProvider<Account, Set<Authority>> authorities();

    public ValueProvider<Account, String> name();

    public ValueProvider<Account, String> password();

    @com.google.gwt.editor.client.Editor.Path("id")
    public ModelKeyProvider<Account> key();

    public ValueProvider<Account, Long> id();

}
