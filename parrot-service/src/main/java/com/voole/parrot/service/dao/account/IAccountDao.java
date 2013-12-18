package com.voole.parrot.service.dao.account;

import com.voole.parrot.service.dao.IEntityDao;
import com.voole.parrot.shared.entity.account.Account;

public interface IAccountDao extends IEntityDao<Account> {

	Account findAccount(String name, String password);

	public Account update(Account t);

}
