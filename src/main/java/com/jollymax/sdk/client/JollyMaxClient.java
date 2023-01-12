package com.jollymax.sdk.client;

import com.jollymax.sdk.enums.Env;

/**
 * @author zhu.q
 */
public interface JollyMaxClient {

    /**
     * set env
     *
     * @param env env
     */
    void setEnv(Env env);

    /**
     * Send the request with the default merchantNo
     * use default merchant config
     *
     * @param apiName apiName
     * @param request requestObject
     * @return json content
     */
    String send(String apiName, Object request);

    /**
     * Use the specified merchantNO to send the request, when you have multiple merchantNo, please use this method to
     * send request.
     *
     * @param apiName    apiName
     * @param request    requestObject
     * @param merchantNo merchantNo
     * @return json content
     */
    String send(String apiName, Object request, String merchantNo);

    /**
     * used to verify the notification come from jollymax
     *
     * @param body notification's http body content
     * @param sign the signature in http header
     * @return verify result
     */
    boolean verifyNotification(String body, String sign);

}
