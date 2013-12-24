
package com.voole.parrot.gwt.common.shared.rpcservice;

import java.util.List;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.voole.parrot.shared.entity.organization.SubOrganization;
import com.voole.parrot.shared.entity.organization.TopOrganization;

public interface OrganizationRpcServiceAsync {


    public void getTopOrganizations(AsyncCallback<List<TopOrganization>> callback);

    public void createTopOrganization(TopOrganization arg0, AsyncCallback<TopOrganization> callback);

    public void changeTopName(TopOrganization arg0, AsyncCallback<TopOrganization> callback);

    public void createSubOrganization(SubOrganization arg0, AsyncCallback<SubOrganization> callback);

    public void changeSubName(SubOrganization arg0, AsyncCallback<SubOrganization> callback);

}
