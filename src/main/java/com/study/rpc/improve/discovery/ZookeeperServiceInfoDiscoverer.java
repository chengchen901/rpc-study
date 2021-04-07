package com.study.rpc.improve.discovery;

import com.alibaba.fastjson.JSON;
import com.study.rpc.improve.common.serialize.MyZkSerializer;
import org.I0Itec.zkclient.ZkClient;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Zookeeper方式获取远程服务信息类。
 *
 * @author Hash
 * @since 2021/1/17
 */
public class ZookeeperServiceInfoDiscoverer implements ServiceInfoDiscoverer {

    private ZkClient client = new ZkClient("localhost:2181");

    private String centerRootPath = "/studyRpc-framework";

    public ZookeeperServiceInfoDiscoverer() {
        client.setZkSerializer(new MyZkSerializer());
    }

    public void regist(ServiceInfo serviceResource) {
        String serviceName = serviceResource.getName();
        String uri = JSON.toJSONString(serviceResource);
        try {
            uri = URLEncoder.encode(uri, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String servicePath = centerRootPath + "/" + serviceName + "/service";
        if (!client.exists(servicePath)) {
            client.createPersistent(servicePath, true);
        }
        String uriPath = servicePath + "/" + uri;
        client.createEphemeral(uriPath);
    }

    /**
     * 加载配置中心中服务资源信息
     *
     * @author Hash
     * @param serviceName 服务名
     * @return 服务信息
     */
    public List<ServiceInfo> loadServiceResources(String serviceName) {
        String servicePath = centerRootPath + "/" + serviceName + "/service";
        List<String> children = client.getChildren(servicePath);
        List<ServiceInfo> resources = new ArrayList<ServiceInfo>();
        for (String ch : children) {
            try {
                String deCh = URLDecoder.decode(ch, "UTF-8");
                ServiceInfo r = JSON.parseObject(deCh, ServiceInfo.class);
                resources.add(r);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return resources;
    }

    @Override
    public ServiceInfo getServiceInfo(String name) {
        List<ServiceInfo> list = loadServiceResources(name);
        ServiceInfo info = list.get(0);
        list.forEach((e) -> {
            if (e != info) {
                info.addAddress(e.getAddress().get(0));
            }
        });
        return info;
    }
}
