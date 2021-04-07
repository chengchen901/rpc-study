package com.study.rpc.improve.demo.provider;

import com.study.rpc.improve.demo.DemoService;
import com.study.rpc.improve.server.Service;

/**
 * @author Hash
 * @since 2021/1/17
 */
@Service(DemoService.class)
public class DemoServiceImpl implements DemoService {
    @Override
    public String sayHello(String name) {
        return "Hello " + name;
    }
}
