package itcast;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyException implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        ModelAndView modelAndView = new ModelAndView();
        if(ex instanceof EmptyResultDataAccessException){
            try {
                modelAndView.addObject("log","1");
                modelAndView.setViewName("redirect:/login.jsp");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return modelAndView;
    }
}
