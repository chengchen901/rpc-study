package com.study.rpc.simple.example;

/**
 * @author Hash
 * @since 2021/1/17
 */
public interface StudentService {

    /**
     * 获取学生信息
     *
     * @author Hash
     * @return 学生信息
     */
    Student getInfo();

    /**
     * 打印学生信息
     *
     * @author Hash
     * @param student 学生信息
     * @return true 成功 false 失败
     */
    boolean printInfo(Student student);
}
