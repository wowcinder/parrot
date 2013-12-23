package com.voole.parrot.service.dao.organization;

import com.voole.parrot.service.dao.IEntityDao;
import com.voole.parrot.shared.entity.organization.User;

public interface IUserDao extends IEntityDao<User> {

	User findUser(String name, String password);
}
