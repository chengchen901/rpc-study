package com.study.rpc.improve.discovery;

/**
 * 远程服务信息发现接口
 *
 * @author Hash
 * @since 2021/1/17
 */
public interface ServiceInfoDiscoverer {

    /**
     * 根据服务名获得远程服务信息
     *
     * @author Hash
     * @param name 服务名
     * @return 远程服务信息
     */
    ServiceInfo getServiceInfo(String name);
}
