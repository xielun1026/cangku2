package com.itheima.service;

import com.itheima.domain.Student;
import com.itheima.mapper.StudentMap;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Service
public class ServiceImpl implements Service{
    @Autowired
    private StudentMap studentMap;

    @Override
    public List<Student> findAll() {
        List<Student> all = studentMap.findAll();
        return all;
    }

    @Override
    public void save(Student student) {
        studentMap.save(student);
    }
}
