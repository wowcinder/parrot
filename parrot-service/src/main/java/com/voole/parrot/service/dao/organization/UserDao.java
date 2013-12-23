package com.voole.parrot.service.dao.organization;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.voole.parrot.service.dao.EntityDao;
import com.voole.parrot.service.util.UserPasswordMd5;
import com.voole.parrot.shared.entity.organization.User;

@Repository
public class UserDao extends EntityDao<User> implements IUserDao {

	@Override
	public User create(User t) {
		t.setPassword(UserPasswordMd5.md5(t.getPassword()));
		return super.create(t);
	}

	@Override
	public User findUser(String name, String password) {
		password = UserPasswordMd5.md5(password);
		return (User) getCurrSession().createCriteria(User.class)
				.add(Restrictions.eq("name", name))
				.add(Restrictions.eq("password", password)).uniqueResult();
	}

	@Override
	public User update(User t) {
		return super.create(t);
	}
}
