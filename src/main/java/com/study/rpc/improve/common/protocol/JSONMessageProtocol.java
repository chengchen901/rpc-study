package com.study.rpc.improve.common.protocol;

import com.alibaba.fastjson.JSON;

/**
 * @author Hash
 * @since 2021/1/17
 */
public class JSONMessageProtocol implements MessageProtocol {
    @Override
    public byte[] marshallingRequest(Request request) {
        Request temp = new Request();
        temp.setServiceName(request.getServiceName());
        temp.setMethod(request.getMethod());
        temp.setHeaders(request.getHeaders());
        temp.setParameterTypes(request.getParameterTypes());

        if (request.getParameters() != null) {
            Object[] params = request.getParameters();
            Object[] serializerParameters = new Object[params.length];
            for (int i = 0; i < params.length; i++) {
                serializerParameters[i] = JSON.toJSONString(params[i]);
            }

            temp.setParameters(serializerParameters);
        }

        return JSON.toJSONBytes(temp);
    }

    @Override
    public Request unmarshallingRequest(byte[] data) {
        Request request = JSON.parseObject(data, Request.class);
        if(request.getParameters() != null) {
            Object[] serializerParameters = request.getParameters();
            Object[] params = new Object[serializerParameters.length];
            for(int i = 0; i < serializerParameters.length; i++) {
                Object param = JSON.parseObject(serializerParameters[i].toString(), Object.class);
                params[i] = param;
            }
            request.setParameters(params);
        }
        return request;
    }

    @Override
    public byte[] marshallingResponse(Response response) {
        Response resp = new Response();
        resp.setHeaders(response.getHeaders());
        resp.setException(response.getException());
        resp.setReturnValue(response.getReturnValue());
        resp.setStatus(response.getStatus());
        return JSON.toJSONBytes(resp);
    }

    @Override
    public Response unmarshallingResponse(byte[] data) {
        return JSON.parseObject(data, Response.class);
    }
}
