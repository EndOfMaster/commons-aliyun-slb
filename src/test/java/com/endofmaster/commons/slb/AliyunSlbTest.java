package com.endofmaster.commons.slb;

import com.endofmaster.commons.slb.dto.AddBackendServerRequest;
import com.endofmaster.commons.slb.dto.BackendServer;
import com.endofmaster.commons.slb.dto.ServerOperationResponse;
import org.junit.Test;

import java.util.Collections;

public class AliyunSlbTest {

    private final static String SLB_ID = "lb-2ze22ot1jj79xoz049aph";

    private final SlbClient client = new SlbClient("VAZSGZwX16yoLtbU", "qi5jrXwQ7VYzgMD1BMSjBl0gFG9COo");

    @Test
    public void addServer() {
        AddBackendServerRequest request = new AddBackendServerRequest(Collections.singletonList(
                new BackendServer("i-2zebslw1q067n115dip1", "100", "ecs")), SLB_ID, "cn-beijing");
        ServerOperationResponse response = client.execute(request);
    }
}