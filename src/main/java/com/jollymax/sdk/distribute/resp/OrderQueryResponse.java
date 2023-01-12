package com.jollymax.sdk.distribute.resp;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderQueryResponse implements Serializable {
    private static final long serialVersionUID = -1L;

    /**
     *
     */
    private String orderId;

    /**
     *
     */
    private String tradeAmount;

    /**
     *
     */
    private String currency;

    /**
     *
     */
    private String status;

    /**
     *
     */
    private List<GoodsVO> goodsList;

    /**
     * 
     */
    private Long createTime;

    public static class GoodsVO implements Serializable {
        private static final long serialVersionUID = -1L;
        private GoodsInfo goodsInfo;
        private GoodsDetail goodsDetail;
        private Map<String, Object> tradeInfo;

        public GoodsInfo getGoodsInfo() {
            return goodsInfo;
        }

        public void setGoodsInfo(GoodsInfo goodsInfo) {
            this.goodsInfo = goodsInfo;
        }

        public GoodsDetail getGoodsDetail() {
            return goodsDetail;
        }

        public void setGoodsDetail(GoodsDetail goodsDetail) {
            this.goodsDetail = goodsDetail;
        }

        public Map<String, Object> getTradeInfo() {
            return tradeInfo;
        }

        public void setTradeInfo(Map<String, Object> tradeInfo) {
            this.tradeInfo = tradeInfo;
        }
    }

    public static class GoodsInfo implements Serializable {
        private static final long serialVersionUID = -1L;

        private String code;

        private String goodsName;

        private String goodsDesc;

        private String quantity;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getGoodsName() {
            return goodsName;
        }

        public void setGoodsName(String goodsName) {
            this.goodsName = goodsName;
        }

        public String getGoodsDesc() {
            return goodsDesc;
        }

        public void setGoodsDesc(String goodsDesc) {
            this.goodsDesc = goodsDesc;
        }

        public String getQuantity() {
            return quantity;
        }

        public void setQuantity(String quantity) {
            this.quantity = quantity;
        }
    }

    public static class GoodsDetail implements Serializable {
        private static final long serialVersionUID = -1L;

        private String type;
        private List<GoodsDetailData> data;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public List<GoodsDetailData> getData() {
            return data;
        }

        public void setData(List<GoodsDetailData> data) {
            this.data = data;
        }
    }

    public static class GoodsDetailData extends HashMap<String, Object> {

        private String cardNo;

        private String pin;

        private Long expireTime;

        private String redeemUrl;

        public String getCardNo() {
            return cardNo;
        }

        public void setCardNo(String cardNo) {
            this.cardNo = cardNo;
        }

        public String getPin() {
            return pin;
        }

        public void setPin(String pin) {
            this.pin = pin;
        }

        public Long getExpireTime() {
            return expireTime;
        }

        public void setExpireTime(Long expireTime) {
            this.expireTime = expireTime;
        }

        public String getRedeemUrl() {
            return redeemUrl;
        }

        public void setRedeemUrl(String redeemUrl) {
            this.redeemUrl = redeemUrl;
        }
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getTradeAmount() {
        return tradeAmount;
    }

    public void setTradeAmount(String tradeAmount) {
        this.tradeAmount = tradeAmount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<GoodsVO> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<GoodsVO> goodsList) {
        this.goodsList = goodsList;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
}
