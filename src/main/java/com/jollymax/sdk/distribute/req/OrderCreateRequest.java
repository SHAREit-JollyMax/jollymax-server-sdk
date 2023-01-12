package com.jollymax.sdk.distribute.req;

import java.io.Serializable;
import java.util.Map;

import com.jollymax.sdk.api.BaseRequest;
import com.jollymax.sdk.api.Constants;
import com.jollymax.sdk.distribute.resp.BalanceQueryResponse;

public class OrderCreateRequest extends BaseRequest<BalanceQueryResponse> implements Serializable {
    private static final long serialVersionUID = -1L;

    /**
     * 必填<br>
     * 外部订单号（唯一标识），最大支持64位<br>
     * 示例：TM003400202203170000034831
     * 
     */
    private String outOrderId;

    /**
     * 必填<br>
     * 分销商品请求码（详情请遵照分销 服务协议附件 1，最大支持64位<br>
     * 示例：ff15
     */
    private String code;

    /**
     * 非必填<br>
     * 请求数量，本版本只支持单件下单<br>
     */
    private String quantity;

    /**
     * 条件选填<br>
     * 直充商品：需传入待充值的账号信息，不同应用的充值账号规则请咨询JollyMax对接小组获取；<br>
     * 非直充商品（电子卡券 CDKey）：无需传入; <br>
     * 示例：<code>{"userId": "123456", "serverId": "1001"}</code>
     *
     */
    private Map<String, Object> tradeInfo;

    /**
     * 非必填<br>
     * 消息ID，建议全局唯一，用来标识具体的某一次请求，最大长度64位<br>
     */
    private String messageId;

    @Override
    protected String getApiName() {
        return Constants.DISTRIBUTE_ORDER_CREATE;
    }

    public String getOutOrderId() {
        return outOrderId;
    }

    public void setOutOrderId(String outOrderId) {
        this.outOrderId = outOrderId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public Map<String, Object> getTradeInfo() {
        return tradeInfo;
    }

    public void setTradeInfo(Map<String, Object> tradeInfo) {
        this.tradeInfo = tradeInfo;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }
}
