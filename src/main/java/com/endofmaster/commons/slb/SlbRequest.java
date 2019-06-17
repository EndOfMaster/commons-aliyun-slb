package com.endofmaster.commons.slb;

import com.endofmaster.commons.id.IdGenerator;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.endofmaster.commons.slb.SlbClient.percentEncode;

/**
 * @author ZM.Wang
 */
public abstract class SlbRequest<T extends SlbResponse> {

    protected Map<String, String> buildParams() throws UnsupportedEncodingException {
        Map<String, String> params = new HashMap<>();
        params.put("Version", "2014-05-26");
        params.put("SignatureMethod", "1.0");
        params.put("SignatureNonce", IdGenerator.uuid());
        params.put("Timestamp", percentEncode(DateFormatUtils.ISO_8601_EXTENDED_DATETIME_FORMAT.format(new Date())));
        return params;
    }

    protected abstract Class<T> responseClass();

}
