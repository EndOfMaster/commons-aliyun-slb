package com.endofmaster.commons.slb.dto;

import com.aliyuncs.slb.model.v20140515.CreateLoadBalancerHTTPSListenerRequest;

/**
 * @author ZM.Wang
 */
public class CreateHttpsListenerRequest extends CreateHttpListenerRequest {

    private String certId;

    public CreateHttpsListenerRequest(String slbId, int listenerPort, int serverPort) {
        super(slbId, listenerPort, serverPort);
    }

    public CreateHttpsListenerRequest setCertId(String certId) {
        this.certId = certId;
        return this;
    }

    public CreateLoadBalancerHTTPSListenerRequest getAliyunHttpsReq() {
        CreateLoadBalancerHTTPSListenerRequest request = new CreateLoadBalancerHTTPSListenerRequest();
        request.setLoadBalancerId(slbId);
        request.setListenerPort(listenerPort);
        request.setBackendServerPort(serverPort);
        request.setBandwidth(-1);       //不限制
        request.setStickySession(stickySession ? "on" : "off");
        request.setStickySessionType(stickySessionType);
        request.setCookieTimeout(cookieTimeout);
        request.setHealthCheck(healthCheck ? "on" : "off");
        request.setHealthCheckDomain(healthDomain);
        request.setHealthCheckConnectPort(healthProt);
        request.setHealthCheckURI(healthPath);
        request.setHealthyThreshold(failureTimes);
        request.setUnhealthyThreshold(successTimes);
        request.setHealthCheckTimeout(healthTimeout);
        request.setHealthCheckInterval(healthInterval);
        request.setHealthCheckHttpCode(healthCheckHttpCode);
        request.setServerCertificateId(certId);
        return request;
    }
}
