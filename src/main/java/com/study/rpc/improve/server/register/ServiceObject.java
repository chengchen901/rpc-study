package com.study.rpc.improve.server.register;

/**
 * 服务对象
 *
 * @author Hash
 * @since 2021/1/17
 */
public class ServiceObject {

    /**
     * 服务名称
     */
    private String name;

    /**
     * 接口
     */
    private Class<?> interf;

    /**
     * 对象实例
     */
    private Object obj;

    public ServiceObject(String name, Class<?> interf, Object obj) {
        this.name = name;
        this.interf = interf;
        this.obj = obj;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class<?> getInterf() {
        return interf;
    }

    public void setInterf(Class<?> interf) {
        this.interf = interf;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    @Override
    public String toString() {
        return "ServiceObject [name=" + name + ", interf=" + interf + ", obj=" + obj + "]";
    }
}
