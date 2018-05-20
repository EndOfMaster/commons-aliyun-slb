package com.endofmaster.commons.slb;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.slb.model.v20140515.*;
import com.endofmaster.commons.slb.dto.CreateHttpListenerRequest;
import com.endofmaster.commons.slb.dto.CreateHttpsListenerRequest;
import com.endofmaster.commons.slb.dto.CreateTcpListenerRequest;

/**
 * @author ZM.Wang
 */
public class AliyunSlb {

    private final String regionId;
    private final String accessKeyId;
    private final String accessKeyecret;
    private final IAcsClient client;

    public AliyunSlb(String regionId, String accessKeyId, String accessKeyecret) {
        this.regionId = regionId;
        this.accessKeyId = accessKeyId;
        this.accessKeyecret = accessKeyecret;
        this.client = new DefaultAcsClient(DefaultProfile.getProfile(regionId, accessKeyId, accessKeyecret));
    }

    public CreateLoadBalancerHTTPListenerResponse addHttpListener(CreateHttpListenerRequest request) throws ClientException {
        return client.getAcsResponse(request.getAliyunReq());
    }

    public CreateLoadBalancerHTTPSListenerResponse addHttpsListener(CreateHttpsListenerRequest request) throws ClientException {
        return client.getAcsResponse(request.getAliyunHttpsReq());
    }

    public CreateLoadBalancerTCPListenerResponse addTcpListener(CreateTcpListenerRequest request) throws ClientException {
        return client.getAcsResponse(request.getAliyunReq());
    }

    public StartLoadBalancerListenerResponse startListener(String slbId, int serverPort) throws ClientException {
        StartLoadBalancerListenerRequest request = new StartLoadBalancerListenerRequest();
        request.setLoadBalancerId(slbId);
        request.setListenerPort(serverPort);
        return client.getAcsResponse(request);
    }

    public DeleteLoadBalancerListenerResponse delListener(String slbId, int serverPort) throws ClientException {
        DeleteLoadBalancerListenerRequest request = new DeleteLoadBalancerListenerRequest();
        request.setLoadBalancerId(slbId);
        request.setListenerPort(serverPort);
        return client.getAcsResponse(request);
    }

    public String getRegionId() {
        return regionId;
    }

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public String getAccessKeyecret() {
        return accessKeyecret;
    }
}
