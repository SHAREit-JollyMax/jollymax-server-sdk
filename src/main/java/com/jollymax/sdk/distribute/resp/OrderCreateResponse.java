package com.jollymax.sdk.distribute.resp;

import java.io.Serializable;

public class OrderCreateResponse implements Serializable {
    private static final long serialVersionUID = -1L;

    private String orderId;

    private String status;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
