package com.endofmaster.commons.slb.dto;

import com.aliyuncs.slb.model.v20140515.CreateLoadBalancerTCPListenerRequest;
import com.aliyuncs.slb.model.v20140515.SetLoadBalancerTCPListenerAttributeRequest;

/**
 * @author ZM.Wang
 */
public class EditTcpListenerRequest {
    private final String slbId;
    private final int listenerPort;
    private final int serverPort;

    public EditTcpListenerRequest(String slbId, int listenerPort, int serverPort) {
        this.slbId = slbId;
        this.listenerPort = listenerPort;
        this.serverPort = serverPort;
    }

    public CreateLoadBalancerTCPListenerRequest getCreateReq() {
        CreateLoadBalancerTCPListenerRequest request = new CreateLoadBalancerTCPListenerRequest();
        request.setLoadBalancerId(slbId);
        request.setListenerPort(listenerPort);
        request.setBackendServerPort(serverPort);
        request.setBandwidth(-1);       //不限制
        return request;
    }
//
//    public SetLoadBalancerTCPListenerAttributeRequest getUpdateReq() {
//        SetLoadBalancerTCPListenerAttributeRequest request = new SetLoadBalancerTCPListenerAttributeRequest();
//        request.setLoadBalancerId(slbId);
//        request.setListenerPort(listenerPort);
//        request.setBackendServerPort(serverPort + "");
//        request.setBandwidth(-1);       //不限制
//        return request;
//    }

}
