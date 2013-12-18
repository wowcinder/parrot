package com.voole.parrot.service.dao.organization;

import org.springframework.stereotype.Repository;

import com.voole.parrot.service.dao.EntityDao;
import com.voole.parrot.shared.entity.organization.Member;
@Repository
public class MemberDao extends EntityDao<Member> implements IMemberDao {

}
