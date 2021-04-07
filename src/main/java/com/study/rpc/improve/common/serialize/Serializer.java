package com.study.rpc.improve.common.serialize;

/**
 * @author Hash
 * @since 2021/1/17
 */
public interface Serializer {

    /**
     * 序列化
     *
     * @author Hash
     * @param obj 对象
     * @return 序列化后的对象
     */
    Object serialize(Object obj);

    /**
     * 反序列化
     *
     * @author Hash
     * @param obj 对象
     * @param clazz 反序列化的目标对象类
     * @return 反序列化后的结果
     */
    <T> T deserialize(Object obj, Class<T> clazz);
}
