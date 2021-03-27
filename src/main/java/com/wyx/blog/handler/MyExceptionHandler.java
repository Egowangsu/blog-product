package com.wyx.blog.handler;

import com.wyx.blog.exception.TypeNameEmptyException;
import com.wyx.blog.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/*异常处理器*/
@Slf4j
@ControllerAdvice
public class MyExceptionHandler {
    @ExceptionHandler(value= NotFoundException.class)   //只要是异常级别的都能进处理器
    public ModelAndView error(HttpServletRequest request,Exception ex){
        log.error("出错了");  //打印到日志
        ModelAndView mv=new ModelAndView();
        mv.addObject("url", request.getRequestURL());  //获取出错的url
        mv.addObject("ex",ex);
        mv.setViewName("error/error");
        return mv;
    }
    @ExceptionHandler(value = TypeNameEmptyException.class)
    public ModelAndView nameEmpty(HttpServletRequest request,Exception ex){
        log.error("分类为空,请正确执行操作");  //打印到日志
        ModelAndView mv=new ModelAndView();
        mv.addObject("ex",ex.getMessage());
        mv.setViewName("error/nameEmpty");
        return mv;
    }
}
