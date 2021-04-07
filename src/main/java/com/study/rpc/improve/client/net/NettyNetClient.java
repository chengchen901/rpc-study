package com.study.rpc.improve.client.net;

import com.study.rpc.improve.discovery.ServiceInfo;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Hash
 * @since 2021/1/17
 */
public class NettyNetClient implements NetClient {

    private SendHandler sendHandler;
    private final Map<String, SendHandler> sendHandlerMap = new ConcurrentHashMap<String, SendHandler>();

    @Override
    public byte[] sendRequest(byte[] data, ServiceInfo serviceInfo) {
        try {
            List<String> addressList = serviceInfo.getAddress();
            int randNum = new Random().nextInt(addressList.size());
            String address = addressList.get(randNum);
            String[] addInfoArray = address.split(":");
            SendHandler handler = sendHandlerMap.get(address);
            if(handler == null) {
                sendHandler = new SendHandler(data);
                new Thread(()->{
                    try {
                        connect(addInfoArray[0], Integer.valueOf(addInfoArray[1]));
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }).start();
            }
            byte[] respData = (byte[]) sendHandler.rspData();
            return respData;
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void connect(String host, int port) throws Exception {
        // 配置客户端
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            //EchoClientHandler handler = new EchoClientHandler();

            b.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline p = ch.pipeline();
                            p.addLast(sendHandler);
                        }
                    });

            // 启动客户端连接
            ChannelFuture f = b.connect(host, port).sync();
            // 等待客户端连接关闭
            f.channel().closeFuture().sync();
        } finally {
            // 释放线程组资源
            group.shutdownGracefully();
        }
    }
}
