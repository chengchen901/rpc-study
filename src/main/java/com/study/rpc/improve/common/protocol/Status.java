package com.study.rpc.improve.common.protocol;

/**
 * 通信状态
 *
 * @author Hash
 * @since 2021/1/17
 */
public enum Status {
    SUCCESS(200, "SUCCESS"),
    ERROR(500, "ERROR"),
    NOT_FOUND(404, "NOT FOUND");

    /** 状态码*/
    private final int code;

    /** 状态信息*/
    private final String message;

    private Status(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
