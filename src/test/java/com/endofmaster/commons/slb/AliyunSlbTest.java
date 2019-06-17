package com.endofmaster.commons.slb;

import com.aliyuncs.exceptions.ClientException;
import com.endofmaster.commons.slb.dto.BackendServer;
import com.endofmaster.commons.slb.dto.CreateHttpListenerRequest;
import com.endofmaster.commons.slb.dto.CreateHttpsListenerRequest;
import com.endofmaster.commons.slb.dto.CreateTcpListenerRequest;
import com.endofmaster.commons.slb.dto.ServersOperationRequest;
import org.junit.Test;


public class AliyunSlbTest {

    private final static String SLB_ID = "lb-2ze22ot1jj79xoz049aph";
    private final AliyunSlb aliyunSlb = new AliyunSlb("cn-beijing", "VAZSGZwX16yoLtbU", "**************");

    @Test
    public void createHttpListener() throws ClientException {
        CreateHttpListenerRequest request = new CreateHttpListenerRequest(SLB_ID, 80, 8080);
        aliyunSlb.addHttpListener(request.healthCheck("/test"));
        aliyunSlb.startListener(SLB_ID, 80);
    }

    @Test
    public void createHttpsListener() throws ClientException {
        CreateHttpsListenerRequest request = new CreateHttpsListenerRequest(SLB_ID, 443, 8080);
        request.healthCheck("/test");
        request.setCertId(Constant.WEJUAI_SLB_CERT_ID);
        aliyunSlb.addHttpsListener(request);
        aliyunSlb.startListener(SLB_ID, 443);
    }

    @Test
    public void createTcpListener() throws ClientException {
        CreateTcpListenerRequest request = new CreateTcpListenerRequest(SLB_ID, 80, 8080);
        aliyunSlb.addTcpListener(request);
        aliyunSlb.startListener(SLB_ID, 80);
    }

    @Test
    public void delListener() throws ClientException {
        aliyunSlb.delListener(SLB_ID, 80);
    }

    @Test
    public void addServer() throws ClientException {
        ServersOperationRequest request = new ServersOperationRequest(SLB_ID);
        request.addServer(new BackendServer("i-2zebslw1q067n115dip1", "100", "ecs"));
        aliyunSlb.addServer(request);
    }
}