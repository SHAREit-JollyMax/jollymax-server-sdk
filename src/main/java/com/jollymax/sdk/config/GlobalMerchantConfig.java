package com.jollymax.sdk.config;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang3.StringUtils;

import com.jollymax.sdk.enums.ErrorCodeEnum;
import com.jollymax.sdk.exceptions.JollyMaxException;

/**
 * @author zhu.q
 */
public class GlobalMerchantConfig {

    private static MerchantConfig DEFAULT_MERCHANT_CONFIG = null;
    private static Boolean DEBUG_FLAG = false;

    private static final Map<String, MerchantConfig> MERCHANT_CONFIG_MAP = new ConcurrentHashMap<>();

    public static void setDefaultConfig(MerchantConfig defaultMerchantConfig) {
        DEFAULT_MERCHANT_CONFIG = defaultMerchantConfig;
        MERCHANT_CONFIG_MAP.put(defaultMerchantConfig.getMerchantNo(), defaultMerchantConfig);
    }

    public static boolean isEnableDebug() {
        return Boolean.TRUE.equals(DEBUG_FLAG);
    }

    public static void enableDebug() {
        DEBUG_FLAG = true;
    }

    public static void disableDebug() {
        DEBUG_FLAG = false;
    }

    public static void addConfig(MerchantConfig config) {
        if (StringUtils.isEmpty(config.getMerchantNo())) {
            throw new JollyMaxException(ErrorCodeEnum.CONFIG_INVALID, "merchantNo is empty");
        }
        if (StringUtils.isEmpty(config.getAppId())) {
            throw new JollyMaxException(ErrorCodeEnum.CONFIG_INVALID, "appId is empty");
        }
        if (StringUtils.isEmpty(config.getMerchantPrivateKey())) {
            throw new JollyMaxException(ErrorCodeEnum.CONFIG_INVALID, "merchantPrivateKey is empty");
        }
        if (StringUtils.isEmpty(config.getJollymaxPublicKey())) {
            throw new JollyMaxException(ErrorCodeEnum.CONFIG_INVALID, "jollymaxPublicKey is empty");
        }
        MERCHANT_CONFIG_MAP.put(config.getMerchantNo(), config);
    }

    public static MerchantConfig getDefaultConfig() {
        if (DEFAULT_MERCHANT_CONFIG == null) {
            throw new JollyMaxException(ErrorCodeEnum.CONFIG_INVALID, "default merchant config is null");
        }
        return DEFAULT_MERCHANT_CONFIG;
    }

    public static MerchantConfig getConfig(String merchantNo) {

        if (StringUtils.isEmpty(merchantNo)) {
            throw new JollyMaxException(ErrorCodeEnum.PARAMS_INVALID, "merchantNo is empty");
        }

        MerchantConfig merchantConfig = MERCHANT_CONFIG_MAP.get(merchantNo);
        if (merchantConfig == null) {
            throw new JollyMaxException(ErrorCodeEnum.PARAMS_INVALID,
                "can not find merchant config by merchantNo:" + merchantNo);
        }
        return merchantConfig;
    }

}
