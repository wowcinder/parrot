package com.voole.parrot.gwt.ui.shared.authority.tree;

import java.util.List;
import java.util.Set;

import com.voole.parrot.gwt.common.shared.RpcAsyncCallback;
import com.voole.parrot.gwt.common.shared.rpcservice.RpcServiceUtils;
import com.voole.parrot.shared.entity.EntityHasAutoId;
import com.voole.parrot.shared.entity.authority.Authority;
import com.voole.parrot.shared.entity.authority.AuthorityEntrance;

public class AuthorityCheckTree extends AuthorityTree {

	private final Authority authority;

	public AuthorityCheckTree(Authority authority) {
		this.authority = authority;
		this.setCheckable(true);
		this.setCheckStyle(CheckCascade.TRI);
		this.setCheckNodes(CheckNodes.BOTH);
	}

	@Override
	public void initData(List<AuthorityEntrance> entrances) {
		super.initData(entrances);
		this.mask();
		RpcServiceUtils.AuthorityRpcService.getDependencies(authority,
				new RpcAsyncCallback<Set<Authority>>() {
					@Override
					protected void _onSuccess(Set<Authority> result) {
						checkNodes(result);
					}

					@Override
					public void post() {
						super.post();
						AuthorityCheckTree.this.unmask();
					}
				});
	}

	@Override
	protected void initData(AuthorityEntrance parent,
			EntityHasAutoId entraceNode) {
		if (entraceNode instanceof Authority) {
			Authority a = (Authority) entraceNode;
			if (a.getId() == authority.getId()) {
				return;
			}
		}
		super.initData(parent, entraceNode);

	}

	protected void checkNodes(Set<Authority> authorities) {
		for (Authority authority : authorities) {
			this.setChecked(authority, CheckState.CHECKED);
		}
	}

	public Authority getAuthority() {
		return authority;
	}

}
