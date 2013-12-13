package com.voole.parrot.service.dao.account;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.voole.parrot.service.dao.EntityDao;
import com.voole.parrot.service.dao.organization.ILeaderDao;
import com.voole.parrot.service.dao.organization.IMemberDao;
import com.voole.parrot.shared.account.Account;
import com.voole.parrot.shared.organization.Leader;
import com.voole.parrot.shared.organization.Member;

public class AccountDao extends EntityDao<Account> implements IAccountDao {
	@Autowired
	private ILeaderDao leaderDao;
	@Autowired
	private IMemberDao memberDao;

	@Override
	public void delete(Account t) {

		Leader leader = (Leader) getCurrentSession()
				.createCriteria(Leader.class)
				.add(Restrictions.eq("account", t)).uniqueResult();
		if (leader != null) {
			leaderDao.delete(leader);
		}
		Member member = (Member) getCurrentSession()
				.createCriteria(Member.class)
				.add(Restrictions.eq("account", t)).uniqueResult();
		if (member != null) {
			memberDao.delete(member);
		}

		flush();

		super.delete(t);
	}
}
