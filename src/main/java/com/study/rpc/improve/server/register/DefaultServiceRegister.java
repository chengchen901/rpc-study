package com.study.rpc.improve.server.register;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Hash
 * @since 2021/1/17
 */
public class DefaultServiceRegister implements ServiceRegister {

    private Map<String, ServiceObject> serviceMap = new HashMap<>();

    @Override
    public void register(ServiceObject serviceObject, String protocolName, int port) {
        if (serviceObject == null) {
            throw new IllegalArgumentException("参数不能为空");
        }

        this.serviceMap.put(serviceObject.getName(), serviceObject);
    }

    @Override
    public ServiceObject getServiceObject(String name) {
        return serviceMap.get(name);
    }
}
