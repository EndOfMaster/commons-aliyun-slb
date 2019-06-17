package com.endofmaster.commons.slb.dto;

import com.aliyuncs.slb.model.v20140515.CreateLoadBalancerTCPListenerRequest;

/**
 * @author ZM.Wang
 */
public class CreateTcpListenerRequest {
    private final String slbId;
    private final int listenerPort;
    private final int serverPort;

    public CreateTcpListenerRequest(String slbId, int listenerPort, int serverPort) {
        this.slbId = slbId;
        this.listenerPort = listenerPort;
        this.serverPort = serverPort;
    }

    public CreateLoadBalancerTCPListenerRequest getAliyunReq() {
        CreateLoadBalancerTCPListenerRequest request = new CreateLoadBalancerTCPListenerRequest();
        request.setLoadBalancerId(slbId);
        request.setListenerPort(listenerPort);
        request.setBackendServerPort(serverPort);
        request.setBandwidth(-1);       //不限制
        return request;
    }
}
