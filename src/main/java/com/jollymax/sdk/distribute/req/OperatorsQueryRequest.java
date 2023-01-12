package com.jollymax.sdk.distribute.req;

import java.io.Serializable;

import com.jollymax.sdk.api.BaseRequest;
import com.jollymax.sdk.api.Constants;
import com.jollymax.sdk.distribute.resp.OrderQueryResponse;

public class OperatorsQueryRequest extends BaseRequest<OrderQueryResponse> implements Serializable {
    private static final long serialVersionUID = -1L;

    @Override
    protected String getApiName() {
        return Constants.DISTRIBUTE_OPERATORS_QUERY;
    }
}
