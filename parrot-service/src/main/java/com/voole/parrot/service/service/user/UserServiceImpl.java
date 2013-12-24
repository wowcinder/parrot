package com.voole.parrot.service.service.user;

import com.voole.parrot.service.dao.user.IUserDao;
import com.voole.parrot.service.service.EntityServiceImpl;
import com.voole.parrot.shared.entity.user.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl extends EntityServiceImpl<User> implements
		UserService {

	@Autowired
	private IUserDao UserDao;

	public IUserDao getEntityDao() {
		return UserDao;
	}

	@Override
	public void changePassword(User user) {
		UserDao.changePassword(user);
	}

	@Override
	public void modifyUserRoles(User user) {
		UserDao.modifyUserRoles(user);
	}

	@Override
	public void modifyUserAuthorities(User user) {
		UserDao.modifyUserAuthorities(user);
	}

}
