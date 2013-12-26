
package com.voole.parrot.gwt.common.shared.rpcservice;

import java.util.List;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.voole.parrot.shared.entity.ctypelogmeta.CtypeLogModel;
import com.voole.parrot.shared.entity.ctypelogmeta.CtypeLogModelColumn;
import com.voole.parrot.shared.entity.ctypelogmeta.CtypeLogModelRootColumn;
import com.voole.parrot.shared.entity.ctypelogmeta.CtypeLogModelVersion;

public interface CtypeLogModelRpcServiceAsync {


    public void createModel(CtypeLogModel arg0, AsyncCallback<CtypeLogModel> callback);

    public void modifyModel(CtypeLogModel arg0, AsyncCallback<CtypeLogModel> callback);

    public void deleteModel(CtypeLogModel arg0, AsyncCallback<Void> callback);

    public void listModelWithVersions(AsyncCallback<List<CtypeLogModel>> callback);

    public void createVersion(CtypeLogModelVersion arg0, AsyncCallback<CtypeLogModelVersion> callback);

    public void modifyVersion(CtypeLogModelVersion arg0, AsyncCallback<CtypeLogModelVersion> callback);

    public void deleteVersion(CtypeLogModelVersion arg0, AsyncCallback<Void> callback);

    public void duplicateVerion(CtypeLogModelVersion arg0, CtypeLogModelVersion arg1, AsyncCallback<CtypeLogModelVersion> callback);

    public void getVersionRootColumnWithChildren(CtypeLogModelVersion arg0, AsyncCallback<CtypeLogModelRootColumn> callback);

    public void createColumn(CtypeLogModelColumn arg0, AsyncCallback<CtypeLogModelColumn> callback);

    public void modifyColumn(CtypeLogModelColumn arg0, AsyncCallback<CtypeLogModelColumn> callback);

    public void deleteColumn(CtypeLogModelColumn arg0, AsyncCallback<Void> callback);

    public void changeColumnsPos(List<CtypeLogModelColumn> arg0, Integer arg1, AsyncCallback<Void> callback);

}
