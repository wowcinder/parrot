
package com.voole.parrot.gwt.common.shared.rpcservice;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.voole.parrot.shared.entity.logmeta.LogModel;
import com.voole.parrot.shared.entity.logmeta.LogModelColumn;
import com.voole.parrot.shared.entity.logmeta.LogModelRootColumn;
import com.voole.parrot.shared.entity.logmeta.LogModelVersion;

public interface LogModelRpcServiceAsync {


    public void createModel(LogModel arg0, AsyncCallback<LogModel> callback);

    public void modifyModel(LogModel arg0, AsyncCallback<LogModel> callback);

    public void deleteModel(LogModel arg0, AsyncCallback<Void> callback);

    public void listModelWithVersions(AsyncCallback<List<LogModel>> callback);

    public void createVersion(LogModelVersion arg0, AsyncCallback<LogModelVersion> callback);

    public void modifyVersion(LogModelVersion arg0, AsyncCallback<LogModelVersion> callback);

    public void deleteVersion(LogModelVersion arg0, AsyncCallback<Void> callback);

    public void duplicateVerion(LogModelVersion arg0, LogModelVersion arg1, AsyncCallback<LogModelVersion> callback);

    public void getVersionRootColumnWithChildren(LogModelVersion arg0, AsyncCallback<LogModelRootColumn> callback);

    public void createColumn(LogModelColumn arg0, AsyncCallback<LogModelColumn> callback);

    public void modifyColumn(LogModelColumn arg0, AsyncCallback<LogModelColumn> callback);

    public void deleteColumn(LogModelColumn arg0, AsyncCallback<Void> callback);

    public void changeColumnsPos(LogModelColumn arg0, AsyncCallback<LogModelColumn> callback);

}
