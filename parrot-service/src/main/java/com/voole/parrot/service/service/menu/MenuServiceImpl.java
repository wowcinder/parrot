
package com.voole.parrot.service.service.menu;

import com.voole.parrot.service.dao.menu.IMenuDao;
import com.voole.parrot.service.service.EntityServiceImpl;
import com.voole.parrot.shared.entity.menu.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MenuServiceImpl
    extends EntityServiceImpl<Menu>
    implements MenuService
{

    @Autowired
    private IMenuDao MenuDao;

    public IMenuDao getEntityDao() {
        return MenuDao;
    }

}
