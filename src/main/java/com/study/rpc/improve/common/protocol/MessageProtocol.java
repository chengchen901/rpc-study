package com.study.rpc.improve.common.protocol;

/**
 * 通信协议接口
 *
 * @author Hash
 * @since 2021/1/17
 */
public interface MessageProtocol {
    /**
     * 编组请求消息
     *
     * @author Hash
     * @param request 请求对象
     * @return 二进制请求数据
     */
    byte[] marshallingRequest(Request request);

    /**
     * 解编组请求消息
     *
     * @author Hash
     * @param data 二进制请求数据
     * @return 请求对象
     */
    Request unmarshallingRequest(byte[] data);

    /**
     * 编组响应消息
     *
     * @author Hash
     * @param response 响应对象
     * @return 二进制响应数据
     */
    byte[] marshallingResponse(Response response);

    /**
     * 解编组响应消息
     *
     * @author Hash
     * @param data 二进制响应数据
     * @return 响应对象
     */
    Response unmarshallingResponse(byte[] data);
}
