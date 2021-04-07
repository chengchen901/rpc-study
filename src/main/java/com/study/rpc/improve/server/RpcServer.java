package com.study.rpc.improve.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import java.io.Closeable;
import java.io.IOException;

/**
 * RPC服务提供者
 *
 * @author Hash
 * @since 2021/1/17
 */
public class RpcServer implements Closeable {

    private int port;

    private String protocol;

    private Channel channel;

    private RequestHandler handler;

    public RpcServer(String protocol, int port) {
        this.protocol = protocol;
        this.port = port;
    }

    public void start() {
        // 开启网络服务
        try {
            setup(port);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setup(int port) throws Exception {
        // 配置服务器
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 100)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline p = ch.pipeline();
                            p.addLast(handler);
                        }
                    });

            // 启动服务
            ChannelFuture f = b.bind(port).sync();
            System.out.println("完成服务端端口绑定与启动");
            channel = f.channel();
            // 等待服务通道关闭
            f.channel().closeFuture().sync();
        } finally {
            // 释放线程组资源
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    public void stop() {
        channel.close();
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public RequestHandler getHandler() {
        return handler;
    }

    public void setHandler(RequestHandler handler) {
        this.handler = handler;
    }

    @Override
    public void close() throws IOException {
        stop();
    }
}
