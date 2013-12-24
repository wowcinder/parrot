package com.voole.parrot.gwt.ui.shared.user.grid;

import java.util.Set;

import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.voole.parrot.gwt.common.shared.core.cell.SimpleSafeHtmlRenderer;
import com.voole.parrot.gwt.common.shared.gridcolumn.UserColumnConfig;
import com.voole.parrot.shared.entity.authority.Role;
import com.voole.parrot.shared.entity.user.User;

public class UserGridBuilderUtil {
	public static ColumnConfig<User, Set<Role>> getRolesColumnConfig() {
		ColumnConfig<User, Set<Role>> config = UserColumnConfig.roles();
		config.setCell(new SimpleSafeHtmlRenderer<Set<Role>>() {
			@Override
			protected String _getLabel(Set<Role> c) {
				String label = "";
				for (Role role : c) {
					if (label.length() > 0) {
						label += ",";
					}
					label += role.getName();
				}
				return label;
			}
		}.getCell());
		config.setSortable(false);
		return config;
	}
}
