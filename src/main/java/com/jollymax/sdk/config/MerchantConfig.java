package com.jollymax.sdk.config;

/**
 * @author zhu.q
 */
public class MerchantConfig {

    private String merchantPrivateKey;

    private String jollymaxPublicKey;

    private String appId;

    private String merchantNo;

    private String spMerchantNo;

    private String merchantAuthToken;

    private boolean needCheckSign = true;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getMerchantNo() {
        return merchantNo;
    }

    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo;
    }

    public boolean isNeedCheckSign() {
        return needCheckSign;
    }

    public void setNeedCheckSign(boolean needCheckSign) {
        this.needCheckSign = needCheckSign;
    }

    public String getMerchantPrivateKey() {
        return merchantPrivateKey;
    }

    public void setMerchantPrivateKey(String merchantPrivateKey) {
        this.merchantPrivateKey = merchantPrivateKey;
    }

    public String getJollymaxPublicKey() {
        return jollymaxPublicKey;
    }

    public void setJollymaxPublicKey(String jollymaxPublicKey) {
        this.jollymaxPublicKey = jollymaxPublicKey;
    }

    public String getSpMerchantNo() {
        return spMerchantNo;
    }

    public void setSpMerchantNo(String spMerchantNo) {
        this.spMerchantNo = spMerchantNo;
    }

    public String getMerchantAuthToken() {
        return merchantAuthToken;
    }

    public void setMerchantAuthToken(String merchantAuthToken) {
        this.merchantAuthToken = merchantAuthToken;
    }

    public static final class Builder {
        private String merchantPrivateKey;
        private String jollymaxPublicKey;
        private String appId;
        private String merchantNo;
        private String spMerchantNo;
        private String merchantAuthToken;
        private boolean checkSign = true;

        private Builder() {}

        public static Builder builder() {
            return new Builder();
        }

        public Builder merchantPrivateKey(String merchantPrivateKey) {
            this.merchantPrivateKey = merchantPrivateKey;
            return this;
        }

        public Builder jollymaxPublicKey(String jollymaxPublicKey) {
            this.jollymaxPublicKey = jollymaxPublicKey;
            return this;
        }

        public Builder appId(String appId) {
            this.appId = appId;
            return this;
        }

        public Builder merchantNo(String merchantNo) {
            this.merchantNo = merchantNo;
            return this;
        }

        public Builder checkSign(boolean checkSign) {
            this.checkSign = checkSign;
            return this;
        }

        public Builder spMerchantNo(String spMerchantNo) {
            this.spMerchantNo = spMerchantNo;
            return this;
        }

        public Builder merchantAuthToken(String merchantAuthToken) {
            this.merchantAuthToken = merchantAuthToken;
            return this;
        }

        public MerchantConfig build() {
            MerchantConfig config = new MerchantConfig();
            config.setMerchantPrivateKey(merchantPrivateKey);
            config.setJollymaxPublicKey(jollymaxPublicKey);
            config.setAppId(appId);
            config.setMerchantNo(merchantNo);
            config.setSpMerchantNo(spMerchantNo);
            config.setMerchantAuthToken(merchantAuthToken);
            config.setNeedCheckSign(checkSign);
            return config;
        }
    }
}
