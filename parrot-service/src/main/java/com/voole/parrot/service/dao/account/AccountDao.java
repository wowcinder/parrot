package com.voole.parrot.service.dao.account;

import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.voole.parrot.service.dao.EntityDao;
import com.voole.parrot.shared.entity.account.Account;

@Repository
public class AccountDao extends EntityDao<Account> implements IAccountDao {

	@Override
	public Account persist(Account t) {
		if (t.getPassword() != null) {
			t.setPassword(DigestUtils.md5Hex(t.getPassword()));
		}
		return super.persist(t);
	}

	@Override
	public Account findAccount(String name, String password) {
		password = DigestUtils.md5Hex(password);
		return (Account) getCurrSession().createCriteria(Account.class)
				.add(Restrictions.eq("name", name))
				.add(Restrictions.eq("password", password)).uniqueResult();
	}

	public static void main(String[] args) {
		System.out.println(DigestUtils.md5Hex(""));
	}

	@Override
	public Account update(Account t) {
		return super.persist(t);
	}
}
