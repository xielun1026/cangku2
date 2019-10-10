package com.itheima.mapper;

import com.itheima.domain.Student;

import java.util.List;

public interface StudentMap {
     List<Student> findAll();

     void save(Student student);
}
