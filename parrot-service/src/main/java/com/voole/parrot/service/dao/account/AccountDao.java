package com.voole.parrot.service.dao.account;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Repository;

import com.voole.parrot.service.dao.EntityDao;
import com.voole.parrot.shared.account.Account;
@Repository
public class AccountDao extends EntityDao<Account> implements IAccountDao {

	@Override
	public Account persist(Account t) {
		if (t.getPassword() != null) {
			t.setPassword(DigestUtils.md5Hex(t.getPassword()));
		}
		return super.persist(t);
	}
}
