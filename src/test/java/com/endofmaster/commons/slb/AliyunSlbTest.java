package com.endofmaster.commons.slb;

import com.aliyuncs.exceptions.ClientException;
import com.endofmaster.commons.slb.dto.BackendServer;
import com.endofmaster.commons.slb.dto.CreateHttpListenerRequest;
import com.endofmaster.commons.slb.dto.CreateHttpsListenerRequest;
import com.endofmaster.commons.slb.dto.EditTcpListenerRequest;
import com.endofmaster.commons.slb.dto.ServersOperationRequest;
import org.junit.Test;


public class AliyunSlbTest {

    private final static String SLB_ID = "lb-2zeco9iqas0r0c65bnnnf";
    private final AliyunSlb aliyunSlb;

    public AliyunSlbTest() throws ClientException {
        this.aliyunSlb = new AliyunSlb("cn-beijing", "VAZSGZwX16yoLtbU", "");
    }

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
        EditTcpListenerRequest request = new EditTcpListenerRequest(SLB_ID, 80, 8080);
        aliyunSlb.addTcpListener(request);
        aliyunSlb.startListener(SLB_ID, 80);
    }

//    @Test
//    public void updateTcpListener() throws ClientException {
//        EditTcpListenerRequest request = new EditTcpListenerRequest(SLB_ID, 80, 8081);
//        aliyunSlb.updateListener(request);
//    }

    @Test
    public void delListener() throws ClientException {
        aliyunSlb.delListener(SLB_ID, 80);
    }

    @Test
    public void addServer() throws ClientException {
        ServersOperationRequest request = new ServersOperationRequest(SLB_ID);
        request.addServer(new BackendServer("eni-2zef59y11bosbx77kboa", "100", "eni"));
        aliyunSlb.addServer(request);
    }

    @Test
    public void removeServer() throws ClientException {
        ServersOperationRequest request = new ServersOperationRequest(SLB_ID);
        request.addServer(new BackendServer("i-2zebslw1q067n115dip1", "100", "ecs"));
        aliyunSlb.removeServer(request);
    }

}