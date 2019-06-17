package com.endofmaster.commons.slb;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author ZM.Wang
 */
public abstract class SlbResponse {

    @JsonProperty("RequestId")
    private String requestId;

    public String getRequestId() {
        return requestId;
    }

    public SlbResponse setRequestId(String requestId) {
        this.requestId = requestId;
        return this;
    }
}
