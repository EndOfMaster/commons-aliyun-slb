package com.endofmaster.commons.slb.dto;

import com.aliyuncs.slb.model.v20140515.AddBackendServersRequest;
import com.aliyuncs.slb.model.v20140515.RemoveBackendServersRequest;

import java.util.ArrayList;
import java.util.List;

import static com.endofmaster.commons.slb.Constant.GSON;

/**
 * @author ZM.Wang
 * slb服务器操作
 */
public class ServersOperationRequest {

    private List<BackendServer> backendServers;
    private String loadBalancerId;  //slbId

    public AddBackendServersRequest getAddReq() {
        AddBackendServersRequest request = new AddBackendServersRequest();
        request.setLoadBalancerId(loadBalancerId);
        request.setBackendServers(GSON.toJson(backendServers));
        return request;
    }

    public RemoveBackendServersRequest getRemoveReq() {
        RemoveBackendServersRequest request = new RemoveBackendServersRequest();
        request.setLoadBalancerId(loadBalancerId);
        request.setBackendServers(GSON.toJson(backendServers));
        return request;
    }

    public ServersOperationRequest(String slbId) {
        this.loadBalancerId = slbId;
    }

    public ServersOperationRequest addServer(BackendServer server) {
        if (backendServers == null) {
            backendServers = new ArrayList<>();
        }
        backendServers.add(server);
        return this;
    }

    public List<BackendServer> getBackendServers() {
        return backendServers;
    }

    public String getLoadBalancerId() {
        return loadBalancerId;
    }
}
