package com.wyx.blog.aspectj;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
  //定义切面类
@Component
@Slf4j
public class LogAspectj {

    @Pointcut(value = "execution(* com.wyx.blog..*.*(..))")   //blog包及其子包下的所有类的素有方法
    public void  log(){};

    @Before("log()")
    public void doBefore(){
        log.info("--------doBefore--------");
    }

    @AfterReturning(value = "log()",returning = "res")
    public void doAfterReturning(Object res){
        log.info("--------doAfterReturning--------");
        log.info("--------返回结果是 "+res+" --------");

    }
}
