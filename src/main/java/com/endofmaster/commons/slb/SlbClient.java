package com.endofmaster.commons.slb;

import com.endofmaster.commons.util.StreamUtils;
import com.endofmaster.commons.util.sign.PresignUtils;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.codec.digest.HmacAlgorithms;
import org.apache.commons.codec.digest.HmacUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.endofmaster.commons.slb.Constant.CHARSET;

/**
 * @author ZM.Wang
 */
public class SlbClient {

    private final Logger logger = LoggerFactory.getLogger(SlbClient.class);

    private final static String URL = "https://slb.aliyuncs.com";
    public final static ObjectMapper MAPPER = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    private final String key;
    private final String secret;
    private final HttpClient httpClient;

    public SlbClient(String key, String secret) {
        this.key = key;
        this.secret = secret;
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(3000).setConnectTimeout(3000).build();
        this.httpClient = HttpClientBuilder.create().setDefaultRequestConfig(requestConfig).build();
    }

    public <T extends SlbResponse> T execute(SlbRequest<T> request) {
        try {
            Map<String, String> params = request.buildParams();
            String url = URL + "?" + PresignUtils.createLinkString(params, false) + "&Signature=" + sign(params);
            logger.info("请求参数串：" + url);
            HttpGet get = new HttpGet(url);
//            RequestBuilder requestBuilder = RequestBuilder.create("GET").setUri(URL);
//            List<NameValuePair> nameValuePairs = params.keySet().stream()
//                    .map(key -> new BasicNameValuePair(key, params.get(key))).collect(Collectors.toList());
//            HttpEntity httpEntity = new UrlEncodedFormEntity(nameValuePairs, Charset.forName(CHARSET));
//            requestBuilder.setEntity(httpEntity);
            HttpResponse response = httpClient.execute(get);
            String json = StreamUtils.copyToString(response.getEntity().getContent(), Charset.forName(CHARSET));
            logger.info("阿里云SLB请求结果json：" + json);
            return MAPPER.readValue(json, request.responseClass());
        } catch (IOException e) {
            throw new SlbException(e);
        }
    }

    private String sign(Map<String, String> params) throws UnsupportedEncodingException {
        params.put("AccessKeyId", key);
        String signStr = PresignUtils.createLinkString(params, true);
        signStr = "GET&" + percentEncode("/") + "&" + percentEncode(signStr);
        return percentEncode(Base64.getEncoder().encodeToString(new HmacUtils(HmacAlgorithms.HMAC_SHA_1, secret).hmac(signStr)));
    }

    public static String percentEncode(String value) throws UnsupportedEncodingException {
        return value != null ? URLEncoder.encode(value, CHARSET).replace("+", "%20").replace("*", "%2A").replace("%7E", "~") : "";
    }


}
