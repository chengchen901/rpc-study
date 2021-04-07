package com.study.rpc.simple.test;

import com.study.rpc.simple.RpcClientProxy;
import com.study.rpc.simple.example.Student;
import com.study.rpc.simple.example.StudentService;
import org.junit.Test;

/**
 * @author Hash
 * @since 2021/1/17
 */
public class ClientTest {

    @Test
    public void clientTest() {
        // 本地没有接口实现，通过代理获得接口实现实例
        RpcClientProxy proxy = new RpcClientProxy("127.0.0.1", 9998);
        StudentService service = proxy.getProxy(StudentService.class);

        System.out.println(service.getInfo());

        Student student = new Student();
        student.setAge(23);
        student.setName("hashmap");
        student.setSex("男");
        System.out.println(service.printInfo(student));
    }
}
