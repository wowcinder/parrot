
package com.voole.parrot.service.service.menu;

import java.util.List;

import com.voole.parrot.service.service.EntityService;
import com.voole.parrot.shared.entity.menu.Menu;
import com.voole.parrot.shared.entity.menu.MenuNode;

public interface MenuService
    extends EntityService<Menu>
{

	List<MenuNode> move(MenuNode p, List<MenuNode> items, int index);


}
