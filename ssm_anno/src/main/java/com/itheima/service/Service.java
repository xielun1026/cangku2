package com.itheima.service;

import com.itheima.domain.Student;

import java.util.List;

public interface Service {
    public List<Student> findAll();

    public void save(Student student);
}
