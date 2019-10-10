package itcast.controller;

import itcast.daomian.Role;
import itcast.daomian.User;
import itcast.daomian.UserStatus;
import itcast.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpSession;
import java.util.List;

@org.springframework.stereotype.Controller
@RequestMapping("/user")
public class Controller {
    @Autowired
    private Service service;
    @RequestMapping("/login")
    public ModelAndView login(String username, String password, HttpSession session) throws Exception {
        User login = service.login(username, password);
        session.setAttribute("user",login);
        ModelAndView modelAndView = new ModelAndView();
        if(login!=null){
            modelAndView.setViewName("redirect:/pages/main.jsp");
        }
        return modelAndView;
    }
    @RequestMapping("/list")
    @ResponseBody
    public ModelAndView findUser(){
        List<UserStatus> userList = service.findUser();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userList",userList);
        modelAndView.setViewName("/user-list");
        return modelAndView;
    }
    @RequestMapping("/saveUI")
    @ResponseBody
    public ModelAndView saveUI(ModelAndView modelAndView){
        List<Role> roleList = service.saveUI();
        modelAndView.setViewName("user-add");
        modelAndView.addObject("roleList",roleList);
        return modelAndView;
    }
    @RequestMapping("/save")

    public String save(User user,long[] roleIds){
        service.save(user,roleIds);
        return "redirect:list";
    }
}
