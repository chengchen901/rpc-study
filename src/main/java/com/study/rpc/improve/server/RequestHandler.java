package com.study.rpc.improve.server;

import com.study.rpc.improve.common.protocol.MessageProtocol;
import com.study.rpc.improve.common.protocol.Request;
import com.study.rpc.improve.common.protocol.Response;
import com.study.rpc.improve.common.protocol.Status;
import com.study.rpc.improve.server.register.ServiceObject;
import com.study.rpc.improve.server.register.ServiceRegister;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Hash
 * @since 2021/1/17
 */
public class RequestHandler extends ChannelInboundHandlerAdapter {

    private MessageProtocol protocol;

    private ServiceRegister serviceRegister;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("激活");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("服务端收到消息："+msg);
        ByteBuf msgBuf = (ByteBuf) msg;
        byte[] req = new byte[msgBuf.readableBytes()];
        msgBuf.readBytes(req);
        byte[] res = handleRequest(req);
        System.out.println("发送响应："+msg);
        ByteBuf respBuf = Unpooled.buffer(res.length);
        respBuf.writeBytes(res);
        ctx.write(respBuf);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }

    public byte[] handleRequest(byte[] data) {
        // 1、解组消息
        Request req = this.protocol.unmarshallingRequest(data);

        // 2、查找服务对象
        ServiceObject so = this.serviceRegister.getServiceObject(req.getServiceName());

        Response rsp = null;

        if (so == null) {
            rsp = new Response(Status.NOT_FOUND);
        } else {
            // 3、反射调用对应的过程方法
            try {
                Method m = so.getInterf().getMethod(req.getMethod(), req.getParameterTypes());
                Object returnValue = m.invoke(so.getObj(), req.getParameters());
                rsp = new Response(Status.SUCCESS);
                rsp.setReturnValue(returnValue);
            } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
                    | InvocationTargetException e) {
                rsp = new Response(Status.ERROR);
                rsp.setException(e);
            }
        }

        // 4、编组响应消息
        return this.protocol.marshallingResponse(rsp);
    }

    public MessageProtocol getProtocol() {
        return protocol;
    }

    public void setProtocol(MessageProtocol protocol) {
        this.protocol = protocol;
    }

    public ServiceRegister getServiceRegister() {
        return serviceRegister;
    }

    public void setServiceRegister(ServiceRegister serviceRegister) {
        this.serviceRegister = serviceRegister;
    }
}
