package com.study.rpc.improve.demo.consumer;

import com.study.rpc.improve.client.ClientStubProxyFactory;
import com.study.rpc.improve.client.net.NettyNetClient;
import com.study.rpc.improve.common.protocol.JSONMessageProtocol;
import com.study.rpc.improve.common.protocol.MessageProtocol;
import com.study.rpc.improve.demo.DemoService;
import com.study.rpc.improve.discovery.ZookeeperServiceInfoDiscoverer;

import java.util.HashMap;
import java.util.Map;

/**
 * 消费者端示例代码
 *
 * @author Hash
 * @since 2021/1/17
 */
public class Consumer {

    public static void main(String[] args) throws Exception {
        // 构建客户端stub代理
        ClientStubProxyFactory clientStubFactory = new ClientStubProxyFactory();
        clientStubFactory.setNetClient(new NettyNetClient());
        clientStubFactory.setSid(new ZookeeperServiceInfoDiscoverer());
        Map<String, MessageProtocol> supportMessageProtocols = new HashMap<String, MessageProtocol>();
        supportMessageProtocols.put(JSONMessageProtocol.class.getSimpleName(), new JSONMessageProtocol());
        clientStubFactory.setSupportMessageProtocols(supportMessageProtocols);

        // 通过代理工厂获得客户端接口
        DemoService demoService =  clientStubFactory.getProxy(DemoService.class); 	// 获取远程服务代理

        //System.out.println(demoService);
        System.out.println("获得代理接口");

        // 执行远程方法
        String hello = demoService.sayHello("world");
        System.out.println(hello); // 显示调用结果
    }
}
