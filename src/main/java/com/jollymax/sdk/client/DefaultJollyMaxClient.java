package com.jollymax.sdk.client;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.jetbrains.annotations.NotNull;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jollymax.sdk.config.GlobalMerchantConfig;
import com.jollymax.sdk.config.MerchantConfig;
import com.jollymax.sdk.domain.GatewayRequest;
import com.jollymax.sdk.domain.GatewayResult;
import com.jollymax.sdk.enums.Env;
import com.jollymax.sdk.enums.ErrorCodeEnum;
import com.jollymax.sdk.exceptions.JollyMaxException;
import com.jollymax.sdk.utils.RsaUtils;

import okhttp3.ConnectionPool;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * @author zhu.q
 */
public class DefaultJollyMaxClient implements JollyMaxClient {

    public static final MediaType JSON_TYPE = MediaType.get("application/json; charset=utf-8");
    public static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";
    public static final String HEADER_SIGN = "sign";
    public static final String HEADER_SDK_VER = "sdk-ver";
    public static final String HEADER_MERCHANT_AUTH_TOKEN = "merchant_auth_token";
    public static final String SDK_VER = "1.0.0";

    private String baseUrl;

    private Env env;

    private OkHttpClient httpClient = new OkHttpClient().newBuilder().retryOnConnectionFailure(false)
        .connectionPool(new ConnectionPool(200, 5, TimeUnit.MINUTES)).callTimeout(15L, TimeUnit.SECONDS).build();

    private static final DefaultJollyMaxClient INSTANCE = new DefaultJollyMaxClient();

    public static DefaultJollyMaxClient getInstance() {
        return INSTANCE;
    }

    /**
     * custom httpclient
     *
     * @param httpClient http client
     */
    public void setHttpClient(OkHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    @Override
    public void setEnv(Env env) {
        this.env = env;
        this.baseUrl = env.getUrl();
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Override
    public String send(String apiName, Object busData) {
        return this.send(apiName, busData, (String)null);
    }

    @Override
    public String send(String apiName, Object busData, String merchantNo) {
        MerchantConfig config;
        if (StringUtils.isNotBlank(merchantNo)) {
            config = GlobalMerchantConfig.getConfig(merchantNo);
        } else {
            config = GlobalMerchantConfig.getDefaultConfig();
        }
        return send(apiName, busData, config);
    }

    @NotNull
    private String send(String apiName, Object busData, MerchantConfig config) {
        try {
            String reqString = buildReqString(config, busData);
            if (GlobalMerchantConfig.isEnableDebug()) {
                System.out.println("request body: " + reqString);
            }

            RequestBody requestBody = RequestBody.create(reqString, JSON_TYPE);

            Request.Builder builder = new Request.Builder();
            String sign = calcSign(config, reqString);
            if (GlobalMerchantConfig.isEnableDebug()) {
                System.out.println("request sign: " + sign);
            }
            builder.header(HEADER_SIGN, sign) // set sign
                .header(HEADER_SDK_VER, SDK_VER) // set sdk version
                .url(this.baseUrl.concat("/").concat(apiName)) // set url
                .post(requestBody);
            if (StringUtils.isNotBlank(config.getMerchantAuthToken())) {
                builder.header(HEADER_MERCHANT_AUTH_TOKEN, config.getMerchantAuthToken());
            }
            Request request = builder.build();

            Response response = httpClient.newCall(request).execute();
            ResponseBody responseBody = response.body();

            if (responseBody == null) {
                throw new JollyMaxException(ErrorCodeEnum.INVOKE_ERROR);
            }
            String respBody = responseBody.string();
            if (GlobalMerchantConfig.isEnableDebug()) {
                System.out.println("response body: " + respBody);

            }
            GatewayResult<?> gatewayResult = JSON.parseObject(respBody, GatewayResult.class);
            if (gatewayResult.isSuccess()) {
                if (GlobalMerchantConfig.isEnableDebug()) {
                    System.out.println("response sign: " + response.header(HEADER_SIGN));
                }
                checkSign(config, respBody, response.header(HEADER_SIGN));
            }
            return respBody;
        } catch (JollyMaxException e) {
            throw e;
        } catch (Exception e) {
            throw new JollyMaxException(e);
        }
    }

    @Override
    public boolean verifyNotification(String body, String sign) {
        JSONObject jsonObject = JSON.parseObject(body);
        String merchantNo = jsonObject.getString("merchantNo");
        MerchantConfig config = GlobalMerchantConfig.getConfig(merchantNo);
        if (!config.isNeedCheckSign()) {
            return true;
        }
        return RsaUtils.verify(body, sign, config.getJollymaxPublicKey(), RsaUtils.CHAR_SET);
    }

    private String buildReqString(MerchantConfig config, Object busData) {
        GatewayRequest busReq = new GatewayRequest();
        busReq.setData(busData);
        busReq.setMerchantNo(config.getMerchantNo());
        busReq.setMerchantAppId(config.getAppId());
        busReq.setRequestTime(DateFormatUtils.format(new Date(), DATE_FORMAT));
        if (StringUtils.isNotBlank(config.getSpMerchantNo())) {
            busReq.setSpMerchantNo(config.getSpMerchantNo());
        }

        return JSON.toJSONString(busReq);
    }

    private String calcSign(MerchantConfig config, String str) {
        return RsaUtils.signForRSA(str, config.getMerchantPrivateKey(), RsaUtils.CHAR_SET);
    }

    private void checkSign(MerchantConfig config, String str, String sign) {
        if (config.isNeedCheckSign() && !RsaUtils.verify(str, sign, config.getJollymaxPublicKey(), RsaUtils.CHAR_SET)) {
            throw new JollyMaxException(ErrorCodeEnum.CHECK_SIGN_ERROR);
        }
    }

    public Env getEnv() {
        return env;
    }

    public OkHttpClient getHttpClient() {
        return httpClient;
    }
}
