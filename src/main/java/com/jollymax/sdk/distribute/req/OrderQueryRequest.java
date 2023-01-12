package com.jollymax.sdk.distribute.req;

import java.io.Serializable;

import com.jollymax.sdk.api.BaseRequest;
import com.jollymax.sdk.api.Constants;
import com.jollymax.sdk.distribute.resp.OrderQueryResponse;

public class OrderQueryRequest extends BaseRequest<OrderQueryResponse> implements Serializable {
    private static final long serialVersionUID = -1L;

    private String outOrderId;

    private String messageId;

    @Override
    protected String getApiName() {
        return Constants.DISTRIBUTE_ORDER_QUERY;
    }

    public String getOutOrderId() {
        return outOrderId;
    }

    public void setOutOrderId(String outOrderId) {
        this.outOrderId = outOrderId;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }
}
