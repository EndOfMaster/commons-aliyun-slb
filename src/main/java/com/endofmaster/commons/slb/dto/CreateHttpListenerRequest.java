package com.endofmaster.commons.slb.dto;

import com.aliyuncs.slb.model.v20140515.CreateLoadBalancerHTTPListenerRequest;

/**
 * @author ZM.Wang
 */
public class CreateHttpListenerRequest {
    final String slbId;
    final int listenerPort;       //slb外放端口
    final int serverPort;         //后端服务端口
    boolean healthCheck;
    String healthPath;
    Integer healthPort;           //健康检查端口，-520使用后端服务端口
    Integer failureTimes;         //失败次数
    Integer successTimes;         //成功次数
    Integer healthTimeout;        //请求超时时间
    Integer healthInterval;       //健康检查间隔
    String healthCheckHttpCode; //http_2xx,http_3xx

    public CreateHttpListenerRequest(String slbId, int listenerPort, int serverPort) {
        this.slbId = slbId;
        this.listenerPort = listenerPort;
        this.serverPort = serverPort;
    }

    public CreateHttpListenerRequest healthCheck(String healthPath) {
        this.healthCheck = true;
        this.healthPath = healthPath;
        this.healthPort = -520;
        this.failureTimes = 2;
        this.successTimes = 2;
        this.healthTimeout = 5;
        this.healthInterval = 30;
        this.healthCheckHttpCode = "http_2xx,http_3xx";
        return this;
    }

    public CreateLoadBalancerHTTPListenerRequest getAliyunReq() {
        CreateLoadBalancerHTTPListenerRequest request = new CreateLoadBalancerHTTPListenerRequest();
        request.setLoadBalancerId(slbId);
        request.setListenerPort(listenerPort);
        request.setBackendServerPort(serverPort);
        request.setHealthCheck(healthCheck ? "on" : "off");
        request.setHealthCheckConnectPort(healthPort);
        request.setHealthCheckURI(healthPath);
        request.setHealthyThreshold(failureTimes);
        request.setUnhealthyThreshold(successTimes);
        request.setHealthCheckTimeout(healthTimeout);
        request.setHealthCheckInterval(healthInterval);
        request.setHealthCheckHttpCode(healthCheckHttpCode);
        return request;
    }
}
