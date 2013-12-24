
package com.voole.parrot.service.service.authority;

import com.voole.parrot.service.service.EntityService;
import com.voole.parrot.shared.entity.authority.Authority;

public interface AuthorityService
    extends EntityService<Authority>
{

	void modifyAuthorityRoles(Authority authority);

	void modifyAuthorityUsers(Authority authority);


}
