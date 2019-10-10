package practice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import practice.domain.User;
import practice.domain.VO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@org.springframework.stereotype.Controller
public class Controller {
    @Autowired
    User user;
    @RequestMapping(value = "/quick1",method = RequestMethod.GET,params = {"username"})
    public String save(){
        System.out.println("quik study");
        return "success";
    }
    @RequestMapping("/quick2")
    public String save2(String username){
        System.out.println(username);
        return "success";
    }
    @RequestMapping("/quick3")
    public ModelAndView save3(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("name","zhangsan");
        modelAndView.setViewName("success");
        return modelAndView;
    }
    @RequestMapping("/quick4")
    public ModelAndView save4(ModelAndView modelAndView){
        modelAndView.addObject("name","lisi");
        modelAndView.setViewName("success");
        return modelAndView;
    }
    @RequestMapping("/quick5")
    public String save5(Model model){
        model.addAttribute("name","wangwu");
        return "success";
    }
    @RequestMapping("/quick6")
    public String save6(HttpServletRequest rep ){
        rep.setAttribute("user","suwukong");
        return "success";
    }
    @RequestMapping("/quick7")
    public void save7(HttpServletResponse resp) throws IOException {
        resp.getWriter().write("大红");
    }
    @RequestMapping("/quick8")
    @ResponseBody
    public String save8(){
        return "haha";
    }
    @RequestMapping("/quick9")
    @ResponseBody
    public String save9(){
        return "{'username':'zhangsan','username':'lisi'}";
    }
    @RequestMapping("/quick10")
    @ResponseBody
    public String save10() throws JsonProcessingException {
        User user = new User();
        user.setName("chunzhu");
        user.setAge("12");
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(user);
        return json;
    }
    @RequestMapping("/quick11")
    @ResponseBody
    public User save11(){
        user.setName("傻瓜");
        user.setAge("23");
        return user;
    }
    @RequestMapping(value = "/quick12")
    @ResponseBody
    public void save12(String name , String age){
        System.out.println(name);
        System.out.println(age);
    }
    @RequestMapping("/quick13")
    @ResponseBody
    public void save13(User user){
        System.out.println(user);
    }
    @RequestMapping("/quick14")
    @ResponseBody
    public void save14(String[] strings){
        List<String> list = Arrays.asList(strings);
        System.out.println(list);
    }
    @RequestMapping("/quick15")
    @ResponseBody
    public void save15(VO vo){
        System.out.println(vo);
    }
    @RequestMapping("/quick16")
    @ResponseBody
    public void save16(@RequestBody List<User> list){
        System.out.println(list);
    }
    @RequestMapping("/quick17")
    @ResponseBody
    public void save17(@RequestParam(name = "q",required = false,defaultValue = "qq") String name){
        user.setName(name);
        System.out.println(user);
    }
    @RequestMapping("/quick18/{age}")
    @ResponseBody
    public void save18(@PathVariable(value ="age") String dd ){
       /* String substring = dd.substring(dd.lastIndexOf("="));
        System.out.println(substring);*/
        System.out.println(dd);
    }
    @RequestMapping("/quick19")
    @ResponseBody
    public void save19(Date date){
        System.out.println(date);
    }

    @RequestMapping("/quick20")
    @ResponseBody
    public void save20(String username, MultipartFile upload) throws IOException {
        System.out.println(username);
        String originalFilename = upload.getOriginalFilename();
        upload.transferTo(new File("E:\\develop\\spring_study\\spring_mvc\\src"+originalFilename));
    }

    @RequestMapping("/quick21")
    @ResponseBody
    public void save21(String username,MultipartFile[]upload) throws IOException {
        System.out.println(username);
        for (MultipartFile multipartFile : upload) {
            String originalFilename = multipartFile.getOriginalFilename();
            multipartFile.transferTo(new File("E:\\develop\\spring_study\\spring_mvc\\src"+originalFilename));
        }
    }
    @RequestMapping("/quick22")
    public ModelAndView save22(){
        String s = "拦截器";
        System.out.println(s);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("name","zhangsan");
        modelAndView.setViewName("success");
        return modelAndView;
    }

    @RequestMapping("/qq")
    public void save23() throws ArithmeticException{
        int i = 1/0 ;
    }
}
