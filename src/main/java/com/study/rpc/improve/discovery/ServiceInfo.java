package com.study.rpc.improve.discovery;

import java.util.ArrayList;
import java.util.List;

/**
 * 远程服务信息类
 *
 * @author Hash
 * @since 2021/1/17
 */
public class ServiceInfo {
    /**
     * 服务名称
     */
    private String name;

    /**
     * 服务提供的协议
     */
    private String protocol;

    /**
     * 服务地址信息
     */
    private List<String> address;

    @Override
    public String toString() {
        return "ServiceInfo{" +
                "name='" + name + '\'' +
                ", protocol='" + protocol + '\'' +
                ", address=" + address +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public List<String> getAddress() {
        return address;
    }

    public void setAddress(List<String> address) {
        this.address = address;
    }

    public void addAddress(String address) {
        if(this.address == null) {
            this.address = new ArrayList<String>();
        }
        this.address.add(address);
    }
}
