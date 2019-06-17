package com.endofmaster.commons.slb.dto;

import com.endofmaster.commons.slb.SlbException;
import com.endofmaster.commons.slb.SlbRequest;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import static com.endofmaster.commons.slb.SlbClient.MAPPER;

/**
 * @author ZM.Wang
 * 后端服务器操作
 */
public abstract class ServerOperationRequest<T extends ServerOperationResponse> extends SlbRequest<ServerOperationResponse> {

    private final List<BackendServer> backendServers;
    private final String LoadBalancerId;
    private final String regionId;

    protected ServerOperationRequest(List<BackendServer> backendServers, String loadBalancerId, String regionId) {
        this.backendServers = backendServers;
        this.LoadBalancerId = loadBalancerId;
        this.regionId = regionId;
    }

    @Override
    protected Map<String, String> buildParams() {
        try {
            Map<String, String> params = super.buildParams();
            params.put("RegionId", regionId);
            params.put("LoadBalancerId", LoadBalancerId);
            params.put("BackendServers", MAPPER.writeValueAsString(backendServers));
            return params;
        } catch (JsonProcessingException | UnsupportedEncodingException e) {
            throw new SlbException("构建请求数据错误", e);
        }
    }
}
