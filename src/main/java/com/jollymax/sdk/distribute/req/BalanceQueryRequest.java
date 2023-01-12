package com.jollymax.sdk.distribute.req;

import java.io.Serializable;

import com.jollymax.sdk.api.BaseRequest;
import com.jollymax.sdk.api.Constants;
import com.jollymax.sdk.distribute.resp.BalanceQueryResponse;

public class BalanceQueryRequest extends BaseRequest<BalanceQueryResponse> implements Serializable {
    private static final long serialVersionUID = -1L;

    @Override
    protected String getApiName() {
        return Constants.DISTRIBUTE_BALANCE_QUERY;
    }

}
