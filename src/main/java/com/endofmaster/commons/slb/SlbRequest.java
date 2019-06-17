package com.endofmaster.commons.slb;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author ZM.Wang
 */
public abstract class SlbRequest<T extends SlbResponse> {

    protected Map<String, String> buildParams() throws UnsupportedEncodingException {
        Map<String, String> params = new HashMap<>();
        params.put("Format", "JSON");
        params.put("Version", "2014-05-15");
        params.put("SignatureMethod", "1.0");
        params.put("SignatureNonce", UUID.randomUUID().toString());
        params.put("Timestamp", LocalDate.now().toString() + "T"
                + DateFormatUtils.format(new Date(), "HH:mm:ss") + "Z");
        return params;
    }

    protected abstract Class<T> responseClass();

}
