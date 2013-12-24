package com.voole.parrot.service.dao.user;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.voole.parrot.service.dao.EntityDao;
import com.voole.parrot.service.dao.EntityUpdater;
import com.voole.parrot.service.util.UserPasswordMd5;
import com.voole.parrot.shared.entity.user.User;

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

	@Override
	public void changePassword(User user) {
		user.setPassword(UserPasswordMd5.md5(user.getPassword()));
		update(user, new EntityUpdater<User>() {
			@Override
			public void invoke(User old, User e) {
				old.setPassword(e.getPassword());
			}
		});
	}

	@Override
	public void modifyUserRoles(User user) {
		update(user, new EntityUpdater<User>() {
			@Override
			public void invoke(User old, User e) {
				old.setRoles(e.getRoles());
			}
		});
	}

	@Override
	public void modifyUserAuthorities(User user) {
		update(user, new EntityUpdater<User>() {
			@Override
			public void invoke(User old, User e) {
				old.setAuthorities(e.getAuthorities());
			}
		});

	}
}
