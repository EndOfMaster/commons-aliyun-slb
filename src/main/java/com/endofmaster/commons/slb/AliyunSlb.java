package com.endofmaster.commons.slb;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.slb.model.v20140515.*;
import com.endofmaster.commons.slb.dto.CreateHttpListenerRequest;
import com.endofmaster.commons.slb.dto.CreateHttpsListenerRequest;
import com.endofmaster.commons.slb.dto.EditTcpListenerRequest;
import com.endofmaster.commons.slb.dto.ServersOperationRequest;

/**
 * @author ZM.Wang
 */
public class AliyunSlb {

    private final String regionId;
    private final String accessKeyId;
    private final String accessKeySecret;
    private final IAcsClient client;

    public AliyunSlb(String regionId, String accessKeyId, String accessKeySecret) {
        this.regionId = regionId;
        this.accessKeyId = accessKeyId;
        this.accessKeySecret = accessKeySecret;
        this.client = new DefaultAcsClient(DefaultProfile.getProfile(regionId, accessKeyId, accessKeySecret));
    }

    public CreateLoadBalancerHTTPListenerResponse addHttpListener(CreateHttpListenerRequest request) throws ClientException {
        return client.getAcsResponse(request.getAliyunReq());
    }

    public CreateLoadBalancerHTTPSListenerResponse addHttpsListener(CreateHttpsListenerRequest request) throws ClientException {
        return client.getAcsResponse(request.getAliyunHttpsReq());
    }

    public CreateLoadBalancerTCPListenerResponse addTcpListener(EditTcpListenerRequest request) throws ClientException {
        return client.getAcsResponse(request.getCreateReq());
    }

//    public SetLoadBalancerTCPListenerAttributeResponse updateListener(EditTcpListenerRequest request) throws ClientException {
//        return client.getAcsResponse(request.getUpdateReq());
//    }

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

    public AddBackendServersResponse addServer(ServersOperationRequest request) throws ClientException {
        return client.getAcsResponse(request.getAddReq());
    }

    public RemoveBackendServersResponse removeServer(ServersOperationRequest request) throws ClientException {
        return client.getAcsResponse(request.getRemoveReq());
    }

    public String getRegionId() {
        return regionId;
    }

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public String getAccessKeySecret() {
        return accessKeySecret;
    }
}
