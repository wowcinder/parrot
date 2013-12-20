
package com.voole.parrot.gwt.common.shared.property;

import java.util.Set;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.voole.parrot.shared.entity.authority.Role;
import com.voole.parrot.shared.entity.organization.Leader;
import com.voole.parrot.shared.entity.organization.Member;
import com.voole.parrot.shared.entity.organization.SubOrganization;
import com.voole.parrot.shared.entity.organization.TopOrganization;
import com.voole.parrot.shared.entity.organization.TopOrganizationAuthority;

public interface TopOrganizationProperty
    extends PropertyAccess<TopOrganization>
{


    @com.google.gwt.editor.client.Editor.Path("id")
    public ModelKeyProvider<TopOrganization> key();

    public ValueProvider<TopOrganization, Long> id();

    public ValueProvider<TopOrganization, Set<TopOrganizationAuthority>> authorities();

    public ValueProvider<TopOrganization, String> name();

    public ValueProvider<TopOrganization, Set<Role>> roles();

    public ValueProvider<TopOrganization, Set<SubOrganization>> subOrganizations();

    public ValueProvider<TopOrganization, Set<Leader>> leaders();

    public ValueProvider<TopOrganization, Set<Member>> members();

}
