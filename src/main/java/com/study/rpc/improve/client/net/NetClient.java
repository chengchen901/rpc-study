package com.study.rpc.improve.client.net;

import com.study.rpc.improve.discovery.ServiceInfo;

/**
 * 网络层
 *
 * @author Hash
 * @since 2021/1/17
 */
public interface NetClient {

    /**
     * 发送请求到服务端
     *
     * @author Hash
     * @param data 请求数据
     * @param serviceInfo 请求的服务信息
     * @return 响应的二进制数据
     */
    byte[] sendRequest(byte[] data, ServiceInfo serviceInfo);
}
