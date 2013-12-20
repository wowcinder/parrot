
package com.voole.parrot.gwt.common.shared.property;

import java.util.Set;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.voole.parrot.shared.entity.authority.Role;
import com.voole.parrot.shared.entity.organization.Leader;
import com.voole.parrot.shared.entity.organization.Member;
import com.voole.parrot.shared.entity.organization.Organization;
import com.voole.parrot.shared.entity.organization.SubOrganization;
import com.voole.parrot.shared.entity.organization.TopOrganizationAuthority;

public interface SubOrganizationProperty
    extends PropertyAccess<SubOrganization>
{


    @com.google.gwt.editor.client.Editor.Path("id")
    public ModelKeyProvider<SubOrganization> key();

    public ValueProvider<SubOrganization, Long> id();

    public ValueProvider<SubOrganization, Set<TopOrganizationAuthority>> authorities();

    public ValueProvider<SubOrganization, String> name();

    public ValueProvider<SubOrganization, Set<Role>> roles();

    public ValueProvider<SubOrganization, Set<SubOrganization>> subOrganizations();

    public ValueProvider<SubOrganization, Set<Leader>> leaders();

    public ValueProvider<SubOrganization, Organization> parentOrganization();

    public ValueProvider<SubOrganization, Set<Member>> members();

}
