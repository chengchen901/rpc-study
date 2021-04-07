package com.study.rpc.improve.common.protocol;

/**
 * http协议编组解组器实现
 *
 * @author Hash
 * @since 2021/1/17
 */
public class HttpMessageProtocol implements MessageProtocol {
    @Override
    public byte[] marshallingRequest(Request request) {
        return new byte[0];
    }

    @Override
    public Request unmarshallingRequest(byte[] data) {
        return null;
    }

    @Override
    public byte[] marshallingResponse(Response response) {
        return new byte[0];
    }

    @Override
    public Response unmarshallingResponse(byte[] data) {
        return null;
    }
}
