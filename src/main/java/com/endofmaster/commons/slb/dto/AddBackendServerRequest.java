package com.endofmaster.commons.slb.dto;

import java.util.List;
import java.util.Map;

/**
 * @author ZM.Wang
 */
public class AddBackendServerRequest extends ServerOperationRequest<ServerOperationResponse> {


    public AddBackendServerRequest(List<BackendServer> backendServers, String loadBalancerId, String regionId) {
        super(backendServers, loadBalancerId, regionId);
    }

    @Override
    protected Map<String, String> buildParams() {
        Map<String, String> params = super.buildParams();
        params.put("Action", "AddBackendServers");
        return params;
    }

    @Override
    protected Class<ServerOperationResponse> responseClass() {
        return ServerOperationResponse.class;
    }
}
