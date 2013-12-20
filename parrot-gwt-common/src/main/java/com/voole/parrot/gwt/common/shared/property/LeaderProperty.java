
package com.voole.parrot.gwt.common.shared.property;

import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.voole.parrot.shared.entity.account.Account;
import com.voole.parrot.shared.entity.organization.Leader;
import com.voole.parrot.shared.entity.organization.Organization;

public interface LeaderProperty
    extends PropertyAccess<Leader>
{


    @com.google.gwt.editor.client.Editor.Path("id")
    public ModelKeyProvider<Leader> key();

    public ValueProvider<Leader, Long> id();

    public ValueProvider<Leader, Organization> organization();

    public ValueProvider<Leader, Account> account();

}
