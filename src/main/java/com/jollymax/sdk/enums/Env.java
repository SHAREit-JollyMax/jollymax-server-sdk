package com.jollymax.sdk.enums;

/**
 * @author zhanglong at 2022/4/4 11:05
 **/
public enum Env {

    /**
     * uat test env
     */
    UAT("https://api-uat.jollymax.com/aggregate-pay/api/gateway"),

    /**
     * live env
     */
    PROD("https://api.jollymax.com/aggregate-pay/api/gateway");

    private String url;

    Env(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
