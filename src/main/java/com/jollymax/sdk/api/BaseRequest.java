package com.jollymax.sdk.api;

import java.lang.reflect.ParameterizedType;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.jollymax.sdk.client.DefaultJollyMaxClient;
import com.jollymax.sdk.domain.GatewayResult;

/**
 * @author zhu.q
 */
public abstract class BaseRequest<RESP> {

    public GatewayResult<RESP> send(String merchantNo) {
        String result = DefaultJollyMaxClient.getInstance().send(getApiName(), this, merchantNo);
        return JSON.parseObject(result, new TypeReference<GatewayResult<RESP>>(
            ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0]) {});
    }

    public GatewayResult<RESP> send() {
        return this.send(null);
    }

    /**
     * 接口名称
     *
     * @return api name
     */
    protected abstract String getApiName();
}
