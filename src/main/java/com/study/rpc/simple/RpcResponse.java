package com.study.rpc.simple;

import java.io.Serializable;

/**
 * Rpc服务端响应结果包装类，在网络上进行传输。
 *
 * @author Hash
 * @since 2021/1/17
 */
public class RpcResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 可能抛出的异常
     */
    private Throwable error;

    /**
     * 响应的内容或结果
     */
    private Object result;

    public Throwable getError() {
        return error;
    }

    public void setError(Throwable error) {
        this.error = error;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
