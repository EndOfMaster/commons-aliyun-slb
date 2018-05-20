package com.endofmaster.commons.slb;

import com.aliyuncs.exceptions.ClientException;
import com.endofmaster.commons.slb.dto.CreateHttpListenerRequest;
import com.endofmaster.commons.slb.dto.CreateHttpsListenerRequest;
import com.endofmaster.commons.slb.dto.CreateTcpListenerRequest;
import org.junit.Test;


public class AliyunSlbTest {

    private final static String SLB_ID = "lb-2ze9u6pdh7jbslvp4ba42";
    private final AliyunSlb aliyunSlb = new AliyunSlb("cn-beijing", "sUKKLHUWR4Ayjrks", "IuZif8RNp3DeG0CN0hkzvcCtszQ0Jl");

    @Test
    public void createHttpListener() throws ClientException {
        CreateHttpListenerRequest request = new CreateHttpListenerRequest(SLB_ID, 80, 8080);
        request.healthCheck("wx.lepluspay.com", "/test");
        aliyunSlb.addHttpListener(request);
        aliyunSlb.startListener(SLB_ID, 80);
    }

    @Test
    public void createHttpsListener() throws ClientException {
        CreateHttpsListenerRequest request = new CreateHttpsListenerRequest(SLB_ID, 443, 8080);
        request.healthCheck("wx.lepluspay.com", "/test");
        request.setCertId(Constant.WX_LEPLUSPAY_COM_CART_ID);
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
}