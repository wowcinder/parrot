
package com.voole.parrot.service.service.user;

import com.voole.parrot.service.service.EntityService;
import com.voole.parrot.shared.entity.user.User;

public interface UserService
    extends EntityService<User>
{

	void changePassword(User user);

	void modifyUserRoles(User user);

	void modifyUserAuthorities(User user);


}
