
package com.voole.parrot.gwt.common.shared.rpcservice;

import java.util.List;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.voole.parrot.shared.entity.hbasemeta.HbaseTable;
import com.voole.parrot.shared.entity.hbasemeta.HbaseTableColumn;
import com.voole.parrot.shared.entity.hbasemeta.HbaseTableVersion;

public interface HbaseTableMetaRpcServiceAsync {


    public void createTable(HbaseTable arg0, AsyncCallback<HbaseTable> callback);

    public void deleteTable(HbaseTable arg0, AsyncCallback<Void> callback);

    public void createHbaseTableVersion(HbaseTableVersion arg0, AsyncCallback<HbaseTableVersion> callback);

    public void deleteHbaseTableVersion(HbaseTableVersion arg0, AsyncCallback<Void> callback);

    public void createHbaseTableColumn(HbaseTableColumn arg0, AsyncCallback<HbaseTableColumn> callback);

    public void modifyHbaseTableColumn(HbaseTableColumn arg0, AsyncCallback<HbaseTableColumn> callback);

    public void deleteHbaseTableColumn(HbaseTableColumn arg0, AsyncCallback<Void> callback);

    public void listHbaseTables(AsyncCallback<List<HbaseTable>> callback);

    public void listHbaseTableColumns(HbaseTableVersion arg0, AsyncCallback<List<HbaseTableColumn>> callback);

    public void modifyTable(HbaseTable arg0, AsyncCallback<HbaseTable> callback);

    public void modifyHbaseTableVersion(HbaseTableVersion arg0, AsyncCallback<HbaseTableVersion> callback);

}
