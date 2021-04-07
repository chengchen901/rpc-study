package com.study.rpc.improve.common.protocol;

import java.util.HashMap;
import java.util.Map;

/**
 * 响应消息
 *
 * @author Hash
 * @since 2021/1/17
 */
public class Response {

    /**
     * 响应状态
     */
    private Status status;

    /**
     * 响应头信息
     */
    private Map<String, String> headers = new HashMap<>();

    /**
     * 返回值
     */
    private Object returnValue;

    /**
     * 异常信息
     */
    private Exception exception;

    public Response() {
    };

    public Response(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Response{" +
                "status=" + status +
                ", headers=" + headers +
                ", returnValue=" + returnValue +
                ", exception=" + exception +
                '}';
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public Object getReturnValue() {
        return returnValue;
    }

    public void setReturnValue(Object returnValue) {
        this.returnValue = returnValue;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }
}
