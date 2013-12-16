package com.voole.parrot.service.dao.organization;

import org.springframework.stereotype.Repository;

import com.voole.parrot.service.dao.EntityDao;
import com.voole.parrot.shared.authority.Role;
@Repository
public class RoleDao extends EntityDao<Role> implements IRoleDao {

}
