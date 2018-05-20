package com.endofmaster.commons.slb.dto;

import com.aliyuncs.slb.model.v20140515.CreateLoadBalancerHTTPListenerRequest;

/**
 * @author ZM.Wang
 */
public class CreateHttpListenerRequest {
    protected final String slbId;
    protected final int listenerPort;
    protected final int serverPort;
    protected boolean stickySession;      //回话保持
    protected String stickySessionType;   //insert默认 / server
    protected Integer cookieTimeout;
    protected boolean healthCheck;
    protected String healthDomain;
    protected String healthPath;
    protected Integer healthProt;         //健康检查端口，-520使用后端服务端口
    protected Integer failureTimes;       //失败次数
    protected Integer successTimes;       //成功次数
    protected Integer healthTimeout;
    protected Integer healthInterval;
    protected String healthCheckHttpCode; //http_2xx,http_3xx

    public CreateHttpListenerRequest(String slbId, int listenerPort, int serverPort) {
        this.slbId = slbId;
        this.listenerPort = listenerPort;
        this.serverPort = serverPort;
    }

    public CreateHttpListenerRequest stickySession(int cookieTimeout) {
        this.stickySession = true;
        this.stickySessionType = "insert";
        this.cookieTimeout = cookieTimeout;
        return this;
    }

    public CreateHttpListenerRequest healthCheck(String healthDomain, String healthPath) {
        this.healthCheck = true;
        this.healthDomain = healthDomain;
        this.healthPath = healthPath;
        this.healthProt = -520;
        this.failureTimes = 2;
        this.successTimes = 2;
        this.healthTimeout = 5;
        this.healthInterval = 20;
        this.healthCheckHttpCode = "http_2xx,http_3xx";
        return this;
    }

    public CreateLoadBalancerHTTPListenerRequest getAliyunReq() {
        CreateLoadBalancerHTTPListenerRequest request = new CreateLoadBalancerHTTPListenerRequest();
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
        return request;
    }
}
