
package com.voole.parrot.gwt.common.shared.property;

import java.util.Set;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.voole.parrot.shared.entity.authority.Role;
import com.voole.parrot.shared.entity.organization.Organization;
import com.voole.parrot.shared.entity.organization.SubOrganization;
import com.voole.parrot.shared.entity.organization.User;

public interface OrganizationProperty
    extends PropertyAccess<Organization>
{


    @com.google.gwt.editor.client.Editor.Path("id")
    public ModelKeyProvider<Organization> key();

    public ValueProvider<Organization, Long> id();

    public ValueProvider<Organization, String> name();

    public ValueProvider<Organization, Set<Role>> roles();

    public ValueProvider<Organization, Set<SubOrganization>> subOrganizations();

    public ValueProvider<Organization, Set<User>> members();

}
