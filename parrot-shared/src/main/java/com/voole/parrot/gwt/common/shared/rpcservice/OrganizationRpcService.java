package com.voole.parrot.gwt.common.shared.rpcservice;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.voole.parrot.shared.entity.organization.SubOrganization;
import com.voole.parrot.shared.entity.organization.TopOrganization;
import com.voole.parrot.shared.exception.SharedException;

@RemoteServiceRelativePath("rpc/organization.rpc")
public interface OrganizationRpcService extends RemoteService {
	List<TopOrganization> getTopOrganizations() throws SharedException;

	TopOrganization createTopOrganization(TopOrganization top)
			throws SharedException;

	TopOrganization changeTopName(TopOrganization top) throws SharedException;

	SubOrganization createSubOrganization(SubOrganization sub)
			throws SharedException;

	SubOrganization changeSubName(SubOrganization sub) throws SharedException;
}
