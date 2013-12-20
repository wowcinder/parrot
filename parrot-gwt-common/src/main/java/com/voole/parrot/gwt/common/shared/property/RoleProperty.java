
package com.voole.parrot.gwt.common.shared.property;

import java.util.Set;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.voole.parrot.shared.entity.authority.Role;
import com.voole.parrot.shared.entity.organization.Member;
import com.voole.parrot.shared.entity.organization.Organization;
import com.voole.parrot.shared.entity.organization.TopOrganizationAuthority;

public interface RoleProperty
    extends PropertyAccess<Role>
{


    @com.google.gwt.editor.client.Editor.Path("id")
    public ModelKeyProvider<Role> key();

    public ValueProvider<Role, Long> id();

    public ValueProvider<Role, Organization> organization();

    public ValueProvider<Role, Set<TopOrganizationAuthority>> authorities();

    public ValueProvider<Role, String> name();

    public ValueProvider<Role, Set<Member>> members();

}
