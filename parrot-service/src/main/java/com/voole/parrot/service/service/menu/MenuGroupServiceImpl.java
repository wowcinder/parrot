
package com.voole.parrot.service.service.menu;

import com.voole.parrot.service.dao.menu.IMenuGroupDao;
import com.voole.parrot.service.service.EntityServiceImpl;
import com.voole.parrot.shared.entity.menu.MenuGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MenuGroupServiceImpl
    extends EntityServiceImpl<MenuGroup>
    implements MenuGroupService
{

    @Autowired
    private IMenuGroupDao MenuGroupDao;

    public IMenuGroupDao getEntityDao() {
        return MenuGroupDao;
    }

}
