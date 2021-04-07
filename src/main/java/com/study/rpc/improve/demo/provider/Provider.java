package com.study.rpc.improve.demo.provider;

import com.study.rpc.improve.server.RpcBootstrap;

/**
 * @author Hash
 * @since 2021/1/17
 */
public class Provider {
    public static void main(String[] args) throws Exception {
        RpcBootstrap bootstrap = new RpcBootstrap();
        bootstrap.start("com.study.rpc.improve.demo");
        System.in.read(); // 按任意键退出
    }
}
