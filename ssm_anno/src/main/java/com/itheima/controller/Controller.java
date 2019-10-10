package com.itheima.controller;

import com.itheima.domain.Student;
import com.itheima.service.Service;
import com.itheima.service.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
@org.springframework.stereotype.Controller
public class Controller {
    @Autowired
    private Service service;

    @RequestMapping("/student")
    @ResponseBody
    public ModelAndView findAll(ModelAndView modelAndView){
        List<Student> all = service.findAll();
        modelAndView.addObject("all",all);
        modelAndView.setViewName("good");
        return modelAndView;
    }
    @RequestMapping("/save")
    public void save (Student student){
        System.out.println(student);
        service.save(student);
    }
}
