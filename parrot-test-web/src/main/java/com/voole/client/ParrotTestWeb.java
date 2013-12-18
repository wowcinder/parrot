package com.voole.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.user.client.Window;
import com.voole.parrot.gwt.common.shared.RpcAsyncCallback;
import com.voole.parrot.gwt.common.shared.rpcservice.RpcServiceUtils;
import com.voole.parrot.shared.entity.menu.Menu;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class ParrotTestWeb implements EntryPoint {

	public void onModuleLoad() {
		Scheduler.get().scheduleDeferred(new ScheduledCommand() {
			@Override
			public void execute() {
				Menu menu = new Menu();
				menu.setName("test-name");
				menu.setToken("test-token");
				RpcServiceUtils.MenuNodeRpcService.save(menu,
						new RpcAsyncCallback<Menu>() {

							@Override
							protected void _onSuccess(Menu result) {
								Window.alert(result.getId().toString());
							}
						});
			}
		});
		
		Menu menu = new Menu();
		menu.setName("test-name");
		menu.setToken("test-token");
		Window.alert(menu.getName().toString());

	}
}
