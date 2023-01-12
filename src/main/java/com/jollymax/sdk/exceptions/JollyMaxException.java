package com.jollymax.sdk.exceptions;

import com.jollymax.sdk.enums.ErrorCodeEnum;

/**
 * @author zhu.q
 */
public class JollyMaxException extends RuntimeException {
    private ErrorCodeEnum errorCode;

    public JollyMaxException() {
        super();
    }

    public JollyMaxException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public JollyMaxException(Throwable cause) {
        super(cause);
    }

    public JollyMaxException(ErrorCodeEnum code, String msg) {
        super(code + ":" + msg);
        this.errorCode = code;
    }

    public JollyMaxException(ErrorCodeEnum codeEnum) {
        super(codeEnum.getCode() + ":" + codeEnum.getMsg());
        this.errorCode = codeEnum;
    }

    public ErrorCodeEnum getErrorCode() {
        return errorCode;
    }
}
