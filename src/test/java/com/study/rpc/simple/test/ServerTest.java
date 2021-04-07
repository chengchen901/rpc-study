package com.study.rpc.simple.test;

import com.study.rpc.simple.RpcServer;
import org.junit.Test;

/**
 * @author Hash
 * @since 2021/1/17
 */
public class ServerTest {

    @Test
    public void startServer() {
        RpcServer server = new RpcServer();
        server.start(9998, "com.study.rpc.simple.example");
    }
}
