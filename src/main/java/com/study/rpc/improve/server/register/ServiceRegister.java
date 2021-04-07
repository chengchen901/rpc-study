package com.study.rpc.improve.server.register;

/**
 * 服务注册器
 *
 * @author Hash
 * @since 2021/1/17
 */
public interface ServiceRegister {

    /**
     * 注册服务
     *
     * @author Hash
     * @param serviceObject 服务对象
     * @param protocolName 协议名称
     * @param port 端口
     */
    void register(ServiceObject serviceObject, String protocolName, int port);

    /**
     * 获取服务对象
     *
     * @author Hash
     * @param name 服务名称
     * @return 服务对象
     */
    ServiceObject getServiceObject(String name);
}
