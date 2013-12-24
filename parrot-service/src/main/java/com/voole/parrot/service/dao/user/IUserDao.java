
package com.voole.parrot.service.dao.user;

import com.voole.parrot.service.dao.IEntityDao;
import com.voole.parrot.shared.entity.user.User;

public interface IUserDao
    extends IEntityDao<User>
{

	User findUser(String name, String password);

	void changePassword(User user);

	void modifyUserRoles(User user);

	void modifyUserAuthorities(User user);


}
