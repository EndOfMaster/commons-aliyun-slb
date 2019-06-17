package com.endofmaster.commons.slb;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author ZM.Wang
 */
public abstract class SlbResponse {

    @JsonProperty("RequestId")
    private String requestId;
    @JsonProperty("Code")
    private String code;
    @JsonProperty("Message")
    private String message;

    public String getRequestId() {
        return requestId;
    }

    public SlbResponse setRequestId(String requestId) {
        this.requestId = requestId;
        return this;
    }
}
