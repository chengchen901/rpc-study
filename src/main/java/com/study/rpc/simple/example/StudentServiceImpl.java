package com.study.rpc.simple.example;

import com.study.rpc.simple.Service;

/**
 * @author Hash
 * @since 2021/1/17
 */
@Service(StudentService.class)
public class StudentServiceImpl implements StudentService {
    @Override
    public Student getInfo() {
        Student student = new Student();
        student.setAge(18);
        student.setName("张三");
        student.setSex("男");
        return student;
    }

    @Override
    public boolean printInfo(Student student) {
        if (student != null) {
            System.out.println("printInfo() = " + student);
            return true;
        }
        return false;
    }
}
