package day03.practice;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MyAspect {
    @Before("day03.practice.MyAspect.aa()")
    public void before(){
        System.out.println("前置增强");
    }
    @Pointcut("execution( * day03.practice.*.*(..))")
    public void aa(){

    }
}
