package com.study.rpc.improve.server;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 一个提供了RPC服务的实现类。
 *
 * @author Hash
 * @since 2021/1/17
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Service {

    /**
     * 所属接口类型
     */
    Class<?> value();
}
