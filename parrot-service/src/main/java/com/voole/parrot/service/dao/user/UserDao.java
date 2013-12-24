package com.voole.parrot.service.dao.user;

import com.voole.parrot.service.dao.EntityDao;
import com.voole.parrot.service.util.UserPasswordMd5;
import com.voole.parrot.shared.entity.user.User;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao extends EntityDao<User> implements IUserDao {

	@Override
	public User create(User e) {
		e.setPassword(UserPasswordMd5.md5(e.getPassword()));
		return super.create(e);
	}

	@Override
	public User findUser(String name, String password) {
		password = UserPasswordMd5.md5(password);
		return (User) getCurrSession().createCriteria(User.class)
				.add(Restrictions.eq("name", name))
				.add(Restrictions.eq("password", password)).uniqueResult();
	}

}
