
package com.voole.parrot.gwt.common.shared.property;

import java.util.Set;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.voole.parrot.shared.entity.authority.Authority;
import com.voole.parrot.shared.entity.authority.Role;
import com.voole.parrot.shared.entity.organization.SubOrganization;
import com.voole.parrot.shared.entity.organization.TopOrganization;
import com.voole.parrot.shared.entity.organization.TopOrganizationAuthority;

public interface TopOrganizationAuthorityProperty
    extends PropertyAccess<TopOrganizationAuthority>
{


    public ValueProvider<TopOrganizationAuthority, TopOrganization> organization();

    public ValueProvider<TopOrganizationAuthority, Set<Role>> roles();

    public ValueProvider<TopOrganizationAuthority, Set<SubOrganization>> subOrganizations();

    public ValueProvider<TopOrganizationAuthority, Authority> authority();

    @com.google.gwt.editor.client.Editor.Path("id")
    public ModelKeyProvider<TopOrganizationAuthority> key();

    public ValueProvider<TopOrganizationAuthority, Long> id();

}
